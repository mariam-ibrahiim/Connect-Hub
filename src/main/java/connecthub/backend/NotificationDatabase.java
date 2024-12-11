package connecthub.backend;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//notfication db for each user
//has user id
//the notify method just adds to notification list
//

public class NotificationDatabase implements Database{
    private List<Notification> notifications = new ArrayList<>();
    private static volatile NotificationDatabase instance;

    private NotificationDatabase() {
        load();
    }

    public static NotificationDatabase getInstance() {
       NotificationDatabase result = instance;
        if (result == null) {
            synchronized (StoryDatabase.class) {
                result = instance;
                if(result == null) {
                    instance = result = new NotificationDatabase();
                }
            }
        }
        return result;
    }
    public void save(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.NOTIFICATION_DATABASE+".json"), notifications);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Notification> getNotifications() {
        return notifications;
    }
    public void load(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("resources\\database\\"+Constants.NOTIFICATION_DATABASE+".json");
        if(!file.exists() || file.length()==0)
            return;

        try {
            notifications = objectMapper.readValue((file),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Story.class));
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
    public void deleteNotification(Notification notification) {
        notifications.remove(notification);
    }

}
