package connecthub.backend;

import connecthub.frontend.App;

public class GroupRequestNotification extends Notification{
    private String senderId;
    public GroupRequestNotification(String userId,String groupId,String senderId) {
        super(userId, groupId);
        this.senderId = senderId;
    }
    
    public GroupRequestNotification() {
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        Group group = Newsfeed.groupManager.searchGroupById(getId2());
        return App.userAccountManager.searchById(senderId).getName() +" has requested to join " + group.getGroupName();
    }
}
