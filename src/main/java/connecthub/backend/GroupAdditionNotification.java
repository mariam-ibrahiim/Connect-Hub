package connecthub.backend;

public class GroupAdditionNotification extends Notification{
    public GroupAdditionNotification(String userId, String groupId) {
        super(userId,groupId);
    }

    @Override
    public String toString(){
        return "You've been added to " + Newsfeed.groupManager.searchGroupById(getId2()).getGroupName();
    }
}
