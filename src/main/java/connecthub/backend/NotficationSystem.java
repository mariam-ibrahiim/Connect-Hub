package connecthub.backend;

public class NotficationSystem {
    private boolean isActive;
    private static NotficationSystem instance;
    private final NotificationDatabase notificationDatabase = NotificationDatabase.getInstance();
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
        notificationDatabase.addNotification(notification);
    }
    public void removeNotification(Notification notification) {
        notificationDatabase.deleteNotification(notification);
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
    public void notifyUser() {

    }

}
