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
        List<String> members = groupManager.searchGroupById(group.getGroupId()).getMembers();
        members.remove(user.getUserId());
        Admin admin = group.getAdmin(user.getUserId());
        if(admin != null)
            group.removeAdmin(admin);
    }

    //promote user to admin and notify user promoted

    public void promoteUser(User user, Group group){

        Admin admin = new Admin(user.getUserId());
        groupManager.searchGroupById(group.getGroupId()).addAdmin(admin);
        groupManager.saveToFile();
        Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(admin.getUserId(), group.getGroupId(), "group status"));
    }

    //demote to user and notify user demoted

    public void demoteAdmin(Admin admin,Group group){
        groupManager.searchGroupById(group.getGroupId()).removeAdmin(admin);
        groupManager.saveToFile();
        Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(admin.getUserId(), group.getGroupId(), "group status"));
    }

    public void deleteGroup(Group group){
        groupManager.removeGroup(group);
    }

}