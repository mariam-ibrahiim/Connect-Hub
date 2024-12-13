package connecthub.backend;

import java.util.List;
import java.util.UUID;

public class GroupRequest {
    private String groupRequestId;
    private String groupId;
    private String userId;

    public GroupRequest(){

    }

    public GroupRequest(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupRequestId = UUID.randomUUID().toString();
        List<Notification> notifications = NotificationFactory.createGroupRequestNotification(userId, groupId);
        System.out.println(userId);
        for(Notification notification : notifications){
            Newsfeed.notficationSystem.addNotification(notification);
        }
    }
    

    public String getGroupRequestId() {
        return groupRequestId;
    }

    public void setGroupRequestId(String groupRequestId) {
        this.groupRequestId = groupRequestId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
