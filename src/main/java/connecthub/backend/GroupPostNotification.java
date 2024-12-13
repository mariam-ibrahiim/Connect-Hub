package connecthub.backend;

public class GroupPostNotification extends Notification{

    public GroupPostNotification(String userId, String groupId) {
        super(userId, groupId);
    }

    public GroupPostNotification() {
    }
    
    @Override
    public String toString() {
        return "A new post has been added to group " + Newsfeed.groupManager.searchGroupById(getId2()).getGroupName();
    }
}
