package connecthub.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;

import connecthub.frontend.AlertBox;

public class UserAccountManager {
    private final UserDatabase database = UserDatabase.getInstance();
    private static volatile UserAccountManager instance;

    private UserAccountManager() {

    }

    public static UserAccountManager getInstance() {
        UserAccountManager result = instance;
        if (result == null) {
            synchronized (UserAccountManager.class) {
                result = instance;
                if (result == null) {
                    instance = result = new UserAccountManager();
                }
            }
        }
        return result;
    }
    //linear search by username and return the user if found
    public User searchByUsername(String username){
        for (User user : database.getUsers()) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    //linear search by email and return the user if found
    public User searchByEmail(String email){
        for (User user : database.getUsers()) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }
    //linear search by user id and return the user if found
    public User searchById(String id){
        for (User user : database.getUsers()) {
            if(user.getUserId().equals(id))
                return user;
        }
        return null;
    }

    //signs up new user to the system
    public boolean signUp (User user){
        User user1 = searchByUsername(user.getUsername());
        if(user1==null){
            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
            database.getUsers().add(user);
            database.saveToFile();
            return true;
        }
        return false;
    }

    public List<User> getUsers(){
        return database.getUsers();
    }

    public UserDatabase getDatabase(){
        return database;
    }

    //user login it returns the logged in user
    public User login(String username,String password){
        User user;
        Pattern pattern = Pattern.compile(Constants.REGEX);
        if(pattern.matcher(username).matches())
            user = searchByEmail(username);
        else
            user = searchByUsername(username);

        if(user==null) {
            AlertBox.displayWarning("Wrong Username!");
            return null; // 1 --> wrong username/email
        }

        if(!BCrypt.checkpw(password,user.getPassword())) {
            AlertBox.displayWarning("Wrong Password!");
            return null;   // --> wrong password
        }

        if(user.getStatus().equals("online")) {
            AlertBox.displayWarning("User already online!");
            return null;
        }

        
        

        user.setStatus("online");

        database.saveToFile();

        return user; // --> login successful
    }

    public void addUser(User user){
        database.getUsers().add(user);
    }

    public void save(){
        database.saveToFile();
    }

    //loads all user data from users database
    public void load(){
        database.loadFromFile();
    }

    public void logout(User user){
        user.setStatus("offline");
        database.saveToFile();
    }

    public void reloadDatabase(){
        database.loadFromFile();
    }
}