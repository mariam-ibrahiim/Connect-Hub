package connecthub.backend;

import java.util.List;

import static connecthub.backend.Newsfeed.groupManager;

public class PrimaryAdmin extends Admin{

    public PrimaryAdmin() {
        super();
    }

    public PrimaryAdmin(String userId) {
        super(userId);
    }

    @Override
    public void removeMember(User user,Group group){
        List<User> members = groupManager.searchGroupById(group.getGroupId()).getMembers();
        members.remove(user);
    }

    public void promoteUser(User user, Group group){

        Admin admin = new Admin(user.getUserId());
        groupManager.searchGroupById(group.getGroupId()).addAdmin(admin);
        Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(getUserId(), group.getGroupId(), "group status"));
    }

    public void demoteAdmin(Admin admin,Group group){
        groupManager.searchGroupById(group.getGroupId()).removeAdmin(admin);
    }

    public void deleteGroup(Group group){
        groupManager.removeGroup(group);
    }

}