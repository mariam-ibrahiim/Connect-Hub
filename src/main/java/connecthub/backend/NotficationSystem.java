package connecthub.backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NotficationSystem {
  //  private boolean isActive;
    private static NotficationSystem instance;
    private final FriendRequestNotificationDatabase friendRequestNotificationDatabase = FriendRequestNotificationDatabase.getInstance();
    private final GroupPostNotificationDatabase groupPostNotificationDatabase = GroupPostNotificationDatabase.getInstance();
    private final GroupAdditionNotificationDatabase groupAdditionNotificationDatabase = GroupAdditionNotificationDatabase.getInstance();
    private final GroupStatusNotificationDatabase groupStatusNotificationDatabase = GroupStatusNotificationDatabase.getInstance();
    private final GroupRequestNotificationDatabase groupRequestNotificationDatabase = GroupRequestNotificationDatabase.getInstance();
    private NotficationSystem() {

    }
    public static NotficationSystem getInstance() {
        NotficationSystem result = instance;
        if (result == null) {
            synchronized (NotficationSystem.class) {
                result = instance;
                if (result == null) {
                    instance = result = new NotficationSystem();
                }
            }
        }
        return result;
    }
    public void addNotification(Notification notification) {
        if(notification instanceof FriendRequestNotification) {
            friendRequestNotificationDatabase.addNotification((FriendRequestNotification) notification);
        }
        else if(notification instanceof GroupPostNotification) {
            groupPostNotificationDatabase.addNotification((GroupPostNotification) notification);
        }
        else if(notification instanceof GroupAdditionNotification) {
            groupAdditionNotificationDatabase.addNotification((GroupAdditionNotification) notification);
        }
        else if(notification instanceof GroupStatusNotification) {
            groupStatusNotificationDatabase.addNotification((GroupStatusNotification) notification);
        }
        else if(notification instanceof GroupRequestNotification){
            groupRequestNotificationDatabase.addNotification((GroupRequestNotification) notification);
        }
    }
    public void removeNotification(Notification notification) {
        if(notification instanceof FriendRequestNotification) {
            friendRequestNotificationDatabase.deleteNotification((FriendRequestNotification) notification);
        }
        else if(notification instanceof GroupPostNotification) {
            groupPostNotificationDatabase.deleteNotification((GroupPostNotification) notification);
        }
        else if(notification instanceof GroupAdditionNotification) {
            groupAdditionNotificationDatabase.deleteNotification((GroupAdditionNotification) notification);
        }
        else if(notification instanceof GroupStatusNotification) {
            groupStatusNotificationDatabase.deleteNotification((GroupStatusNotification) notification);
        }
        else if(notification instanceof GroupRequestNotification){
            groupRequestNotificationDatabase.deleteNotification((GroupRequestNotification) notification);
        }

    }


 public void reloadDatabase(){
        friendRequestNotificationDatabase.load();
        groupPostNotificationDatabase.load();
        groupAdditionNotificationDatabase.load();
        groupStatusNotificationDatabase.load();
        groupRequestNotificationDatabase.load();
 }

    public List<Notification> getNotificationsForUser(String userId) {
        List<Notification> notifications = new ArrayList<>();
        notifications.addAll(friendRequestNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupPostNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupAdditionNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupStatusNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupRequestNotificationDatabase.searchNotifications(userId));
        notifications.sort(Comparator.comparing(Notification::getTimestamp));
        return notifications;
    }
    public FriendRequestNotification searchForFriendRequestNotification(String userId,String id2){
        List<FriendRequestNotification> notifications = new ArrayList<>();
        for(Notification notification:getNotificationsForUser(userId)){
            if(notification instanceof FriendRequestNotification)
            notifications.add((FriendRequestNotification)notification);
        }
        for(Notification notification : notifications){
            if(notification.getId2().equals(id2)){
                System.out.println("Notification found");
                return (FriendRequestNotification)notification;
            }
        }
        return null;
    }
    public GroupPostNotification searchForGroupPostNotification(String userId,String groupId){
        List<GroupPostNotification> notifications = new ArrayList<>();
        for(Notification notification:getNotificationsForUser(userId)){
            if(notification instanceof GroupPostNotification)
                notifications.add((GroupPostNotification)notification);
        }
        for(Notification notification : notifications){
            if(notification.getId2().equals(groupId)){
                System.out.println("Notification found");
                return (GroupPostNotification)notification;
            }
        }
        return null;
    }

    public GroupStatusNotification searchForGroupStatusNotification(String userId,String groupId){
        List<GroupStatusNotification> notifications = new ArrayList<>();
        for(Notification notification:getNotificationsForUser(userId)){
            if(notification instanceof GroupStatusNotification)
                notifications.add((GroupStatusNotification)notification);
        }
        for(Notification notification : notifications){
            if(notification.getId2().equals(groupId)){
                System.out.println("Notification found");
                return (GroupStatusNotification)notification;
            }
        }
        return null;
    }
    public GroupAdditionNotification searchForGroupAdditionNotification(String userId,String groupId){
        List<GroupAdditionNotification> notifications = new ArrayList<>();
        for(Notification notification:getNotificationsForUser(userId)){
            if(notification instanceof GroupAdditionNotification)
                notifications.add((GroupAdditionNotification)notification);
        }
        for(Notification notification : notifications){
            if(notification.getId2().equals(groupId)){
                System.out.println("Notification found");
                return (GroupAdditionNotification)notification;
            }
        }
        return null;
    }
    public GroupRequestNotification searchForGroupRequestNotification(String userId,String groupId){
        List<GroupRequestNotification> notifications = new ArrayList<>();
        for(Notification notification:getNotificationsForUser(userId)){
            if(notification instanceof GroupRequestNotification)
                notifications.add((GroupRequestNotification)notification);
        }
        for(Notification notification : notifications){
            if(notification.getId2().equals(groupId)){
                System.out.println("Notification found");
                return (GroupRequestNotification)notification;
            }
        }
        return null;
    }

}
