package connecthub.backend;

public abstract class Notification{
  //  private String message;
    private String userId;

    public Notification(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

 //   public abstract Notification createNotification();
/*    public abstract void notifyUser(Notification notification); {
        Newsfeed.notficationSystem.addNotification(notification);
    }*/
}
//override toString
//create a notification
//for each notification
//if user sends friend request I want to add it to list of notifications for the certain user
//call notify user and create notification of type
//notify bb3tlha userid and then create an object