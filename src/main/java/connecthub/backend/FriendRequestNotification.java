package connecthub.backend;

import connecthub.frontend.App;

public class FriendRequestNotification extends Notification {
    private String senderId;

    public FriendRequestNotification(String senderId, String receiverId) {
        super(receiverId);
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "You received a request from " + App.userAccountManager.searchById(senderId).toString();
    }


/*
    public void notifyUser(String senderId,String receiverId) {
        Newsfeed.notficationSystem.addNotification(new FriendRequestNotification(receiverId));
    }*/

}
