package connecthub.backend;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestNotificationDatabase implements NotificationDatabase{
    private List<Notification> notifications = new ArrayList<>();
    private static volatile FriendRequestNotificationDatabase instance;

    private FriendRequestNotificationDatabase() {
        load();
    }

    public static FriendRequestNotificationDatabase getInstance() {
        FriendRequestNotificationDatabase result = instance;
        if (result == null) {
            synchronized (FriendRequestNotification.class) {
                result = instance;
                if(result == null) {
                    instance = result = new FriendRequestNotificationDatabase();
                }
            }
        }
        return result;
    }
       @Override
    public void save(){
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.registerModule(new JavaTimeModule());

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.FRIEND_REQUEST_NOTIFICATION_DATABASE+".json"), notifications);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(){
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.registerModule(new JavaTimeModule());
         File file = new File("resources\\database\\"+Constants.FRIEND_REQUEST_NOTIFICATION_DATABASE+".json");
         if(!file.exists() || file.length()==0)
         return;

            try {
                notifications = objectMapper.readValue((file),
                                                                 objectMapper.getTypeFactory().constructCollectionType(List.class, FriendRequestNotification.class));
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    @Override
    public void addNotification(Notification notification) {
        notifications.add(notification);
        save();
    }
    @Override
    public void deleteNotification(Notification notification) {
        for(Notification friendRequestNotification : notifications){
            if(friendRequestNotification.getNotificationId().equals(notification.getNotificationId())){
                notifications.remove(friendRequestNotification);
                break;
            }
        }
        save();
    }
    @Override
    public List<Notification> searchNotifications(String userId){
        List<Notification> notifications = new ArrayList<>();
        for(Notification notification : this.notifications){
            if(notification.getUserId().equals(userId)){
                notifications.add(notification);
            }
        }
        return notifications;
    }
}
