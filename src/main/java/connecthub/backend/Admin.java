package connecthub.backend;

import connecthub.frontend.App;

import static connecthub.backend.Newsfeed.groupManager;

import java.util.Iterator;
import java.util.List;

public class Admin{
    private String userId;

    public Admin(){

    }

    public Admin(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

     //add user and remove notification for admins & primary admin
     //add notification of group addition to user
     //delete request
    public void approveRequest(User sender,Group group){
        List<String> members = Newsfeed.groupManager.searchGroupById(group.getGroupId()).getMembers();
        if(!group.getAdminsIds().contains(sender.getUserId()))
          {
            members.add(sender.getUserId());
            Newsfeed.groupManager.saveToFile();
            for(String adminId : group.getAdminsIds()){
                GroupRequestNotification notification = Newsfeed.notficationSystem.searchForGroupRequestNotification(adminId, group.getGroupId());
                if(notification!=null)
                Newsfeed.notficationSystem.removeNotification(notification);
            }
            PrimaryAdmin primaryAdmin = group.getPrimaryAdmin();
            if(primaryAdmin!=null){
                GroupRequestNotification notification = Newsfeed.notficationSystem.searchForGroupRequestNotification(primaryAdmin.getUserId(), group.getGroupId());
                if(notification!=null)
                Newsfeed.notficationSystem.removeNotification(notification);
            }
            Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(sender.getUserId(),group.getGroupId(),"group addition"));
            Newsfeed.groupRequestsManager.removeRequest(Newsfeed.groupRequestsManager.searchGroupRequest(sender.getUserId(),group.getGroupId()));
          } 

    }


    //remove user and remove notification for admins
    //delete request
    public void declineRequest(User sender,Group group){
        List<String> members = Newsfeed.groupManager.searchGroupById(group.getGroupId()).getMembers();
        if(group.getAdminsIds().contains(sender.getUserId())){
            members.remove(sender.getUserId());
            Newsfeed.groupManager.saveToFile();
            for(String adminId : group.getAdminsIds()){
                Notification notification = Newsfeed.notficationSystem.searchForGroupRequestNotification(adminId, group.getGroupId());
                if(notification!=null)
                Newsfeed.notficationSystem.removeNotification(notification);
            }
            PrimaryAdmin primaryAdmin = group.getPrimaryAdmin();
            if(primaryAdmin!=null){
                Notification notification = Newsfeed.notficationSystem.searchForGroupRequestNotification(primaryAdmin.getUserId(), group.getGroupId());
                if(notification!=null)
                Newsfeed.notficationSystem.removeNotification(notification);
            }
        Newsfeed.groupRequestsManager.removeRequest(Newsfeed.groupRequestsManager.searchGroupRequest(sender.getUserId(),group.getGroupId()));
        }

    }
    //remove grp addition notification
    public void removeMember(User user,Group group){
        List<String> members = Newsfeed.groupManager.searchGroupById(group.getGroupId()).getMembers();
        if(!group.getAdminsIds().contains(user.getUserId()))
        members.remove(user.getUserId());
         Notification notification = Newsfeed.notficationSystem.searchForGroupAdditionNotification(user.getUserId(),group.getGroupId());
         if(notification!=null)
        Newsfeed.notficationSystem.removeNotification(notification);
        List<Notification> notifications =(List<Notification>) Newsfeed.notficationSystem.searchForGroupPostNotification(user.getUserId(), group.getGroupId());
        for(Notification n: notifications){
            Newsfeed.notficationSystem.removeNotification(notification);
        } 
        notifications = (List<Notification>) Newsfeed.notficationSystem.searchForGroupRequestNotification(user.getUserId(), group.getGroupId());
        for(Notification n: notifications){
            Newsfeed.notficationSystem.removeNotification(notification);
        }
        notifications = (List<Notification>) Newsfeed.notficationSystem.searchForGroupStatusNotification(user.getUserId(), group.getGroupId());
        for(Notification n: notifications){
            Newsfeed.notficationSystem.removeNotification(notification);
        }

    }

/*     public void managePosts(){

    } */

    @Override
    public String toString(){
        return App.userAccountManager.searchById(userId).getName();
    }
}