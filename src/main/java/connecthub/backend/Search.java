package connecthub.backend;


import connecthub.frontend.App;
import connecthub.frontend.NewsfeedPage;

import java.util.ArrayList;
import java.util.List;

public class Search {
    //linear user search by id and returns the object
    public static List<Object> userSearch(String key,FriendManagement friendManager){
        List<Object> results = new ArrayList<>();
        for(User user : App.userAccountManager.getUsers()){
            if(user.getName().toLowerCase().contains(key)){
                if(!friendManager.getBlocked().isBlocked(user.getUserId()))
                    results.add(user);
            }
        }
        return results;
    }

    //linear group search by id and returns the object
    public static List<Object> groupSearch (String key){
        List<Object> results = new ArrayList<>();
        for(Group group: Newsfeed.groupManager.getGroups()){
            if(group.getGroupName().toLowerCase().contains(key)){
                    results.add(group);
            }
        }
        return results;
    }
}