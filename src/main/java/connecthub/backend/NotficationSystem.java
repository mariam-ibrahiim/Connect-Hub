package connecthub.backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NotficationSystem {
    private boolean isActive;
    private static NotficationSystem instance;
    private final FriendRequestNotificationDatabase friendRequestNotificationDatabase = FriendRequestNotificationDatabase.getInstance();
    private final GroupPostNotificationDatabase groupPostNotificationDatabase = GroupPostNotificationDatabase.getInstance();
    private final GroupAdditionNotificationDatabase groupAdditionNotificationDatabase = GroupAdditionNotificationDatabase.getInstance();
    private final GroupStatusNotificationDatabase groupStatusNotificationDatabase = GroupStatusNotificationDatabase.getInstance();
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

    }
    /*    public List<Notification> getNotifications() {
            return notifications;
        }*/
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public void ignoreNotiification(Notification notification) {
        setActive(false);
    }

    public List<Notification> getNotificationsForUser(String userId) {
        List<Notification> notifications = new ArrayList<>();
        notifications.addAll(friendRequestNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupPostNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupAdditionNotificationDatabase.searchNotifications(userId));
        notifications.addAll(groupStatusNotificationDatabase.searchNotifications(userId));
        notifications.sort(Comparator.comparing(Notification::getTimestamp).reversed());
        return notifications;
    }

}
