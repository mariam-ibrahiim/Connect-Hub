package connecthub.backend;

import connecthub.frontend.App;

public class GroupStatusNotification extends GroupActivitiesNotification{

    public GroupStatusNotification(String userId,String groupId) {
        super(userId, groupId);
    }

    @Override
    public String toString() {
        Group group = Newsfeed.groupManager.searchGroupById(getGroupId());
        if(group.isAdmin(getUserId()))
            return "You have been promoted in " + group.getGroupName();
        else
            return "You have been demoted in " + group.getGroupName();
    }
}
