package connecthub.backend;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDatabase { // implements database interface
    private List<User> users = new ArrayList<>();
    private static UserDatabase instance;

    private UserDatabase(){
        loadFromFile();
    }

    public static UserDatabase getInstance(){
        if (instance==null)
            instance = new UserDatabase();
        return instance;
    }

    public List<User> getUsers(){
        return users;
    }

    public void saveToFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.USER_FILENAME+".json"), users);
            System.out.println("Saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //loads all user data from users database
    public void loadFromFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("resources\\database\\"+Constants.USER_FILENAME+".json");
        try {
            if(file.length()!=0) {
                users = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class));
                System.out.println("Loaded successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add(User user){
        users.add(user);
    }
}
/* public class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        users =  new ArrayList<>();
    }

    public void loadUsers(){
        ObjectMapper objectMapper = new ObjectMapper();
            try {
                users = objectMapper.readValue(new File("resources\\database\\user.json"),
                                                                 objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public void saveUsers(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\user.json"), users);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addUser(User user){
        users.add(user);
        saveUsers();
    }
    public void removeUser(User user){
        users.remove(user);
        saveUsers();
    }
    public List<User> getUsers(){
        return users;
    }
    public User getUserById(String userId){
        for (User user : users) {
            if(user.getUserId().equals(userId))
                return user;
        }
        return null;
    }

} */