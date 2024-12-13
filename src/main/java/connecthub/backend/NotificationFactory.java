package connecthub.backend;

import java.util.ArrayList;
import java.util.List;

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
//diff return type
    public static List<Notification> createGroupRequestNotification(String senderId, String groupId){
        Group group = Newsfeed.groupManager.searchGroupById(groupId);
        String primaryAdmin = group.getPrimaryAdmin().getUserId();
        List<String> adminIds = new ArrayList<>();
        for(Admin admin : group.getAdmins()){
            adminIds.add(admin.getUserId());    
        }
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new GroupRequestNotification(primaryAdmin,groupId,senderId));
        for(String adminId : adminIds){
            notifications.add(new GroupRequestNotification(adminId,groupId,senderId));
        }
        return notifications;
    }

}
