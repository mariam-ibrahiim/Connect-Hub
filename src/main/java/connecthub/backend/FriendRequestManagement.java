
package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class FriendRequestManagement {
//    private ArrayList<FriendRequest> friendRequests;
    private String status;
    private final FriendRequestDatabase friendRequestDatabase = FriendRequestDatabase.getInstance();
    private static FriendRequestManagement instance;

    private FriendRequestManagement() {
    }

    public static FriendRequestManagement getInstance() {
        FriendRequestManagement result = instance;
        if (result == null) {
            synchronized (FriendRequestManagement.class) {
                result = instance;
                if (result == null) {
                    instance = result = new FriendRequestManagement();
                }
            }
        }
        return result;
    }

    public FriendRequest findRequest(String senderId, String receiverId) {
        friendRequestDatabase.loadFriendRequests();
        ArrayList<FriendRequest> friendRequests = friendRequestDatabase.getFriendRequests();
        for (FriendRequest request : friendRequests) {
            System.out.println("Checking request: SenderId = " + request.getSenderId() + ", ReceiverId = " + request.getReceiverId());
            if (request.getSenderId().equals(senderId) && request.getReceiverId().equals(receiverId)) {
                System.out.println("Request found: " + request);
                return request;
            }
        }
        return null;
    }

    public void load(){
        friendRequestDatabase.loadFriendRequests();
    }

    public void sendRequest(String senderId, String recieverId)
   {

       if(senderId.isEmpty() || recieverId.isEmpty()) //checks if sender or reciever is not found
       {
           System.out.println("cant find users");
           return;
       }
       if(senderId.equals(recieverId)) //checks for duplicate requests
       {
           System.out.println("cant send request to yourself");
           return;
       }
       if(findRequest(recieverId,senderId)!=null) //checks for reversal requests
       {
           System.out.println("cant reverse request");
           return;
       }
       if(findRequest(senderId,recieverId)!=null) //checks if request already exists
       {
           System.out.println("request already sent");
           return;
       }
       
       friendRequestDatabase.getFriendRequests().add(new FriendRequest(senderId,recieverId));
       System.out.println("request sent");
       this.status="pending";
       friendRequestDatabase.saveFriendRequests();

       Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(senderId,recieverId,"friend request"));

   }

    public void acceptRequest(String senderId, String receiverId) {
        System.out.println("Friend requests loaded: " + friendRequestDatabase.getFriendRequests());
        FriendRequest request = findRequest(senderId, receiverId);
        if (request == null) {
            System.out.println("No request found");
            return;
        }
        friendRequestDatabase.getFriendRequests().remove(request);
        System.out.println("After removing request: " + friendRequestDatabase.getFriendRequests());
        this.status = "accepted";
        friendRequestDatabase.saveFriendRequests();
    }

    public void declineRequest(String senderId, String recieverId)
   {
      if(findRequest(senderId,recieverId)==null)
       {
           System.out.println("no request found");
           return;
       } 
     friendRequestDatabase.getFriendRequests().remove(findRequest(senderId,recieverId));
      this.status="declined";
      friendRequestDatabase.saveFriendRequests();

   }
   


    public void cancelRequest(String senderId, String receiverId) {
        // Find the friend request in the database
        FriendRequest request = findRequest(senderId, receiverId);

        if (request != null) {
            // Remove the request from the list
            friendRequestDatabase.getFriendRequests().remove(request);

            // Save the updated list back to the file
            this.status = "canceled";
            friendRequestDatabase.saveFriendRequests();
            System.out.println("Friend request canceled.");
        } else {
            System.out.println("No request found to cancel.");
        }
    }




    public ArrayList<String> getSentRequests(String senderId) {
        ArrayList<String> sentRequests = new ArrayList<>();

        // Validate sender ID
        if (senderId == null || senderId.isEmpty()) {
            System.err.println("Error: Sender ID is null or empty.");
            return sentRequests;
        }

        // Loop through all friend requests and find those sent by the given sender
        for (FriendRequest request : friendRequestDatabase.getFriendRequests()) {
            if (senderId.equals(request.getSenderId())) {
                if (request.getReceiverId() != null && !request.getReceiverId().isEmpty()) {
                    sentRequests.add(request.getReceiverId());
                } else {
                    System.err.println("Error: Found a request with an empty or null receiver ID.");
                }
            }
        }

        System.out.println("Filtered sent requests for user ID " + senderId + ": " + sentRequests);
        return sentRequests;
    }


    public ArrayList<String> getRecivedRequests(String receiverId) {
        ArrayList<String> receivedRequests = new ArrayList<>();

        // Validate receiver ID
        if (receiverId == null || receiverId.isEmpty()) {
            System.err.println("Error: Receiver ID is null or empty.");
            return receivedRequests;
        }

        // Loop through all friend requests and find those sent to the given receiver
        for (FriendRequest request : friendRequestDatabase.getFriendRequests()) {
            if (receiverId.equals(request.getReceiverId())) {
                if (request.getSenderId() != null && !request.getSenderId().isEmpty()) {
                    receivedRequests.add(request.getSenderId());
                } else {
                    System.err.println("Error: Found a request with an empty or null sender ID.");
                }
            }
        }

        System.out.println("Filtered received requests for user ID " + receiverId + ": " + receivedRequests);
        return receivedRequests;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/*     public void reloadDatabase(){
        friendRequestDatabase.loadFriendRequests();
    }
 */

}
