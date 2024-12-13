package connecthub.backend;
import connecthub.backend.Constants;

public class FriendManagement {

    private String userId;
    private Friends friends;
    private Suggested suggested;
    private Blocked blocked;
    private FriendRequestManagement friendRequestManagement;
    private FriendsDataBase friendsDataBase;
    private static volatile FriendManagement instance;
    


    public String getUserId() {
        return userId;
    }

    public FriendRequestManagement getFriendRequestManagement() {
        return friendRequestManagement;
    }

    public Friends getFriends() {
        return friends;
    }


    public Suggested getSuggested() {
        return suggested;
    }

    public void populateSuggested(UserDatabase userDatabase) {
        friendsDataBase.populateSuggestions(userDatabase , friendRequestManagement.getRecivedRequests(userId) , friendRequestManagement.getSentRequests(userId));
        update();
    }



    public Blocked getBlocked() {
        return blocked;
    }


    private FriendManagement(String userId , FriendRequestManagement friendRequestManagement) {
        this.userId = userId;
        this.friendsDataBase = new FriendsDataBase();
        this.friends = new Friends();
        this.suggested = new Suggested();
        this.blocked = new Blocked();
        this.friendRequestManagement = friendRequestManagement;
        friendsDataBase.addUser(userId);
    }

    public static FriendManagement getInstance(String userId , FriendRequestManagement friendRequestManagement) {
        FriendManagement result = instance;
        if (result == null) {
            synchronized (FriendManagement.class) {
                result = instance;
                if (result == null) {
                    instance = result =  new FriendManagement(userId , friendRequestManagement);
                }
            }
        }
        return result;
    }
    public void addFriend(String reciverId) {
        friendRequestManagement.sendRequest(userId, reciverId);
        suggested.removeSuggestedId(reciverId);
        friendsDataBase.removeSuggested(userId, reciverId);
        friendsDataBase.removeSuggested(reciverId, userId);
        update();

    }

    public void removeFriend(String friendId) {
        this.friends.removeFriendsId(friendId);
        this.suggested.addSuggestedId(friendId);
        friendsDataBase.removeFriend(userId, friendId);
        friendsDataBase.removeFriend(friendId, userId);
        friendsDataBase.suggestFriend(userId, friendId);
        friendsDataBase.suggestFriend(friendId, userId);
        update();
    }
    public void block(String friendId) {
        this.friends.removeFriendsId(friendId);
        this.suggested.removeSuggestedId(friendId);
        this.blocked.addBlockedId(friendId);
        friendsDataBase.blockUser(userId, friendId);
        friendsDataBase.removeFriend(friendId, userId);
        update();
    }
    public void unblock(String friendId) {
        this.suggested.addSuggestedId(friendId);
        this.blocked.removeBlockedId(friendId);
        friendsDataBase.suggestFriend(userId, friendId);
        friendsDataBase.suggestFriend(friendId, userId);
        friendsDataBase.removeBlocked(userId,friendId);
        update();
    }
    public void acceptFriend(String senderId) {
        // Ensure the latest friend requests are loaded
        friends.addFriendsId(senderId);
        suggested.removeSuggestedId(senderId);
        friendsDataBase.addFriend(userId, senderId);
        friendsDataBase.addFriend(senderId,userId);
        friendsDataBase.removeSuggested(userId, senderId);
        friendsDataBase.removeSuggested(senderId,userId);
 
        friendRequestManagement.load();
        FriendRequest request = friendRequestManagement.findRequest(senderId, userId);
        if (request != null) {
            friendRequestManagement.acceptRequest(senderId, userId);
            System.out.println("Request accepted.");
            Notification notification = Newsfeed.notficationSystem.searchForFriendRequestNotification(userId, senderId);
            if(notification!=null)
            Newsfeed.notficationSystem.removeNotification(notification);
        } else {
            System.out.println("No request found.");
        }
        update();
    }


    public void declineFriend(String senderId) {
        // Ensure the latest friend requests are loaded
        friendRequestManagement.load();
        suggested.addSuggestedId(senderId);
        friendsDataBase.suggestFriend(userId, senderId);
        friendsDataBase.suggestFriend(senderId,userId);

        // Find the friend request in the database
        FriendRequest request = friendRequestManagement.findRequest(senderId, userId);

        if (request != null) {
            // Decline the friend request (remove it from the database)
            friendRequestManagement.declineRequest(senderId, userId);
            System.out.println("Request declined.");
            Notification notification = Newsfeed.notficationSystem.searchForFriendRequestNotification(userId, senderId);
            if(notification!=null)
            Newsfeed.notficationSystem.removeNotification(notification);
        } else {
            // If no request is found, print an error message
            System.out.println("No request found.");
        }
            update();
    }
    public void cancelRequest(String receiverId) {
        // Ensure the latest friend requests are loaded
        friendRequestManagement.load();

        // Find the sent friend request (senderId is the current user)
        FriendRequest request = friendRequestManagement.findRequest(userId, receiverId);

        if (request != null) {
            // Cancel the friend request (remove it from the database)
            friendRequestManagement.cancelRequest(userId, receiverId);
            System.out.println("Request canceled.");
        } else {
            // If no request is found, print an error message
            System.out.println("No request found to cancel.");
        }
        update();
    }

    public void load(){
        this.friendsDataBase.loadFromFile();
        this.friends.setFriendsIds(friendsDataBase.getFriends(userId));
        this.suggested.setSuggestedIds(friendsDataBase.getSuggested(userId));
        this.blocked.setBlockedIds(friendsDataBase.getBlocked(userId));
    }


    public void update(){
        friendsDataBase.saveToFile();
    }

    public void reloadDatabase(){
        friendsDataBase.loadFromFile();
    }


}
