package connecthub.backend;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "@type"
)*/
/*@JsonSubTypes({
        @JsonSubTypes.Type(value = FriendRequestNotification.class, name = "friendRequest"),
        @JsonSubTypes.Type(value = GroupAdditionNotification.class, name = "groupAddition"),
        @JsonSubTypes.Type(value = GroupPostNotification.class, name = "groupPost"),
        @JsonSubTypes.Type(value = GroupStatusNotification.class, name = "groupStatus")
})*/
public abstract class Notification{

    //  private String message;
    private String userId;
    private String id2;
    private LocalDateTime timestamp;

    public void setId2(String id2) {
        this.id2 = id2;
        this.timestamp=LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getId2() {
        return id2;
    }

    public Notification(String userId,String id2) {
        this.userId = userId;
        this.id2 = id2;
    }

    public Notification(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Notification [type=" + getClass().getSimpleName() + ", userId=" + userId + ", id2=" + id2 + "]";
    }
 //   public abstract String getType();


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