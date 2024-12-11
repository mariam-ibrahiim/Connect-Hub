package connecthub.backend;

public abstract class GroupActivitiesNotification extends Notification{
    private String groupId;

    public GroupActivitiesNotification(String userId, String groupId) {
        super(userId);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
