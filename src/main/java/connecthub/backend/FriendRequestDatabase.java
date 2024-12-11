/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FriendRequestDatabase {
    private ArrayList<FriendRequest> friendRequests;
    private static volatile FriendRequestDatabase instance;

    private FriendRequestDatabase() {
        friendRequests=loadFriendRequests();
    }

    public static FriendRequestDatabase getInstance() {
        FriendRequestDatabase result = instance;
        if (result == null) {
            synchronized (FriendRequestDatabase.class) {
                result = instance;
                if (result == null) {
                    instance = result = new FriendRequestDatabase();
                }
            }
        }
        return result;
    }

    public ArrayList<FriendRequest> getFriendRequests() {
        return friendRequests;
    }


    public void saveFriendRequests() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.FRIENDS_REQUEST_DATABASE+".json"), friendRequests);
            System.out.println("Friend requests saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FriendRequest> loadFriendRequests() {
        File file = new File("resources\\database\\"+Constants.FRIENDS_REQUEST_DATABASE+".json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (file.exists() && file.length() > 0) {
                return objectMapper.readValue(
                        file,
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, FriendRequest.class)
                );
            } else {
                System.out.println("The file is empty or does not exist.");
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
    }
    }


}
