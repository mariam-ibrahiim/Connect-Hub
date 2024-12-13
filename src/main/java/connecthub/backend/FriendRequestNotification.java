package connecthub.backend;

import connecthub.frontend.App;

public class FriendRequestNotification extends Notification {

    public FriendRequestNotification(String senderId, String receiverId) {
        super(receiverId,senderId);
    }
    public FriendRequestNotification(){

    }

    @Override
    public String toString() {
        return "You received a request from " + App.userAccountManager.searchById(getId2()).toString();
    }


/*
    public void notifyUser(String senderId,String receiverId) {
        Newsfeed.notficationSystem.addNotification(new FriendRequestNotification(receiverId));
    }*/

}
