package connecthub.backend;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class GroupRequestNotificationDatabase implements NotificationDatabase{
    private List<Notification> notifications = new ArrayList<>();
    private static volatile GroupRequestNotificationDatabase instance;

    private GroupRequestNotificationDatabase() {
        load();
    }

    public static GroupRequestNotificationDatabase getInstance() {
       GroupRequestNotificationDatabase result = instance;
        if (result == null) {
            synchronized (GroupRequestNotificationDatabase.class) {
                result = instance;
                if(result == null) {
                    instance = result = new GroupRequestNotificationDatabase();
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
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\" + Constants.GROUP_REQUEST_NOTIFICATION_DATABASE + ".json"), notifications);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

/*
        objectMapper.activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder().allowIfSubType(Notification.class).build(),
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE
        );
*/

        File file = new File("resources\\database\\" + Constants.GROUP_REQUEST_NOTIFICATION_DATABASE + ".json");
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try {
            //Deserialize
          //  notifications = objectMapper.readerFor(GroupPostNotification.class).readValue(file);
            notifications = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, GroupRequestNotification.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addNotification(Notification notification) {
        notifications.add(notification);
        save();
    }

    //for safe removal
    @Override
    public void deleteNotification(Notification notification) {
        Iterator<Notification> iterator = notifications.iterator();
        while (iterator.hasNext()) {
            Notification n = iterator.next();
            if (n.getNotificationId().equals(notification.getNotificationId())) {
                iterator.remove();
                System.out.println("removed");
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
