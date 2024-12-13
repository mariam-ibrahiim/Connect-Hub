package connecthub.backend;


public class GroupStatusNotification extends Notification{

    public GroupStatusNotification(String userId,String groupId) {
        super(userId, groupId);
    }

    public GroupStatusNotification() {
    }

    @Override
    public String toString() {
        Group group = Newsfeed.groupManager.searchGroupById(getId2());
        if(group.getAdmin(getUserId())!=null)
            return "You have been promoted in " + group.getGroupName();
        else
            return "You have been demoted in " + group.getGroupName();
    }
}
