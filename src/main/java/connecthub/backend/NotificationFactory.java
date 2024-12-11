package connecthub.backend;

public class NotificationFactory {
    public static Notification createNotification(String id1,String id2,String notificationType) {
        if (notificationType.equalsIgnoreCase("friend request")) {
            return new FriendRequestNotification(id1, id2);
        }
        if (notificationType.equalsIgnoreCase("group post")) {
            return new GroupPostNotification(id1, id2);
        }
        if (notificationType.equalsIgnoreCase("group status")) {
            return new GroupStatusNotification(id1, id2);
        }
        if(notificationType.equalsIgnoreCase("group addition")) {
            return new GroupAdditionNotification(id1,id2);
        }
        return null;
    }
}
