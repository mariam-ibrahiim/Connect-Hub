package connecthub.backend;

public class GroupAdditionNotification extends GroupActivitiesNotification{
    public GroupAdditionNotification(String userId, String groupId) {
        super(userId,groupId);
    }

    @Override
    public String toString(){
        return "You've been added to " + Newsfeed.groupManager.searchGroupById(getGroupId()).getGroupName();
    }
}
