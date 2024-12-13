package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendsDataBase {
    private Map<String, UserData> database;
    private static volatile FriendsDataBase instance;

    public FriendsDataBase() {
        this.database = new HashMap<>();
        loadFromFile();
    }
    private static FriendsDataBase getInstance() {
        FriendsDataBase result = instance;
        if (result == null) {
            synchronized (FriendsDataBase.class) {
                result = instance;
                if (result == null) {
                    instance = result = new FriendsDataBase();
                }
            }
        }
        return result;
    }

    // Method to load the database from a JSON file
    public void loadFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("resources\\database\\" + Constants.RELATIONS_DATABASE + ".json");
        try {
            if (file.exists() && file.length() != 0) {
                Map<String, UserData> loadedData = objectMapper.readValue(
                        file, objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, UserData.class)
                );
                this.database.putAll(loadedData); // Merge with the existing database
                System.out.println("Friend Database loaded successfully!");

            } else {
                System.out.println("friend database is empty or does not exist.");
            }
        } catch (IOException e) {
            System.out.println("Error loading database from file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Method to save the database to a JSON file
    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("resources\\database\\"+Constants.RELATIONS_DATABASE+".json");
        try {
            // Save the entire database map
            objectMapper.writeValue(file, this.database);
        } catch (IOException e) {
            System.out.println("Error saving database to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFriends(String userId) {
        if (database.containsKey(userId)) {
            return new ArrayList<>(database.get(userId).friends);
        }
        return new ArrayList<>(); // Return empty list if userId is not found
    }

    public ArrayList<String> getBlocked(String userId) {
        if (database.containsKey(userId)) {
            return new ArrayList<>(database.get(userId).blocked);
        }
        return new ArrayList<>(); // Return empty list if userId is not found
    }

    public ArrayList<String> getSuggested(String userId) {
        if (database.containsKey(userId)) {
            return new ArrayList<>(database.get(userId).suggested);
        }
        return new ArrayList<>(); // Return empty list if userId is not found
    }




    // Method to add a new user
    public void addUser(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("Invalid userId. Cannot add user.");
            return;
        }
        if (!database.containsKey(userId)) {
            database.put(userId, new UserData());
            saveToFile(); // Save immediately after adding
        }
    }


    // Method to remove a user
    public void removeUser(String userId) {
        if (database.remove(userId) != null) {
            System.out.println("User removed: " + userId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    // Method to add a friend for a specific user
    public void addFriend(String userId, String friendId) {
        if (database.containsKey(userId)) {
            database.get(userId).friends.add(friendId);
            System.out.println("Friend added for user " + userId + ": " + friendId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    // Method to remove a friend for a specific user
    public void removeFriend(String userId, String friendId) {
        if (database.containsKey(userId)) {
            database.get(userId).friends.remove(friendId);
            System.out.println("Friend removed for user " + userId + ": " + friendId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    public void removeBlocked(String userId, String friendId) {
        if (database.containsKey(userId)) {
            database.get(userId).blocked.remove(friendId);
            System.out.println("Friend removed for user " + userId + ": " + friendId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    // Method to block a user
    public void blockUser(String userId, String blockedId) {
        if (database.containsKey(userId)) {
            database.get(userId).blocked.add(blockedId);
            database.get(userId).friends.remove(blockedId);
            database.get(userId).suggested.remove(blockedId);
            System.out.println("User blocked for " + userId + ": " + blockedId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    // Method to suggest a friend
    public void suggestFriend(String userId, String suggestedId) {
        if (database.containsKey(userId)) {
            database.get(userId).suggested.add(suggestedId);
            System.out.println("Friend suggested for " + userId + ": " + suggestedId);
        } else {
            System.out.println("User not found: " + userId);
        }
    }
    public void removeSuggested(String userId, String suggestedId) {
        if (database.containsKey(userId)) {
            database.get(userId).suggested.remove(suggestedId);
            System.out.println("Friend removed for user " + userId + ": " + suggestedId);
        }else{
            System.out.println("User not found: " + userId);
        }
    }

    public void fillSuggested(UserDatabase userDatabase, String userId, ArrayList<String> receivedRequests, ArrayList<String> sentRequests) {
        // Ensure the user exists in the database
        if (!database.containsKey(userId)) {
            System.out.println("User not found in the database: " + userId);
            return;
        }

        UserData userData = database.get(userId); // Get the current user's data
        List<String> suggested = userData.suggested; // Get the suggested list for this user

        // Ensure receivedRequests is not null
        if (receivedRequests == null) {
            receivedRequests = new ArrayList<>();
        }

        if (sentRequests == null) {
            sentRequests = new ArrayList<>();
        }

        // Clear previous suggestions (Optional, but ensures no leftovers)
        suggested.clear();

        // Iterate through the user list and suggest users who are not in the received requests, friends, or blocked list
        for (User user : userDatabase.getUsers()) {
            String potentialSuggestion = user.getUserId();

            // Add the suggestion only if it's not the current user, not a friend, not blocked, not already suggested, and not in received requests or sent requests
            if (!potentialSuggestion.equals(userId) &&
                    !userData.friends.contains(potentialSuggestion) &&
                    !userData.blocked.contains(potentialSuggestion) &&
                    !userData.suggested.contains(potentialSuggestion) &&
                    !receivedRequests.contains(potentialSuggestion) &&
                    !sentRequests.contains(potentialSuggestion)  &&
                    !getBlocked(potentialSuggestion).contains(userId)) {
                suggested.add(potentialSuggestion); // Add to the suggested list
            }
        }

        // Now remove any user from the suggested list if they are in receivedRequests
        for (String requestId : receivedRequests) {
            // If the request is in suggested, remove it
            if (suggested.contains(requestId)) {
                suggested.remove(requestId);
                System.out.println("Removed from suggested (receiver): " + requestId);
            }
        }

        // Now remove any user from the suggested list if they are in sentRequests
        for (String requestId : sentRequests) {
            // If the request is in suggested, remove it
            if (suggested.contains(requestId)) {
                suggested.remove(requestId);
                System.out.println("Removed from suggested (sender): " + requestId);
            }
        }

        // Print the final suggested list for debugging
        System.out.println("Final suggested list for user ID " + userId + ": " + suggested);

        saveToFile();
    }


    public void populateSuggestions(UserDatabase userDatabase , ArrayList<String> recivedRequests , ArrayList<String> sentRequests) {
        for (String userId : database.keySet()) {
            fillSuggested(userDatabase, userId, recivedRequests , sentRequests);
        }
    }
    


    // Inner class to store user data
    static class UserData {
        public List<String> friends;
        public List<String> blocked;
        public List<String> suggested;

        public UserData() {
            this.friends = new ArrayList<>();
            this.blocked = new ArrayList<>();
            this.suggested = new ArrayList<>();
        }
    }


}
