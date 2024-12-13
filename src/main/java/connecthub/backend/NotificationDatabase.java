package connecthub.backend;

import java.util.List;

public interface NotificationDatabase extends Database{
    public void addNotification(Notification notification);
    public void deleteNotification(Notification notification);
    public List<Notification> searchNotifications(String userId);
}
