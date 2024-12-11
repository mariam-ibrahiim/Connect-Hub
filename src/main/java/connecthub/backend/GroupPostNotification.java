package connecthub.backend;

public class GroupPostNotification extends GroupActivitiesNotification{

    public GroupPostNotification(String userId, String groupId) {
        super(userId, groupId);
    }

    @Override
    public String toString() {
        return "A new post has been added to group " + Newsfeed.groupManager.searchGroupById(getGroupId()).getGroupName();
    }
}
