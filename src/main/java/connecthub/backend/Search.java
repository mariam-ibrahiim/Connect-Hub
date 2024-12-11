package connecthub.backend;


import connecthub.frontend.App;
import connecthub.frontend.NewsfeedPage;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public static List<User> userSearch(String key,FriendManagement friendManager){
        List<User> results = new ArrayList<>();
        for(User user : App.userAccountManager.getUsers()){
            if(user.getName().toLowerCase().contains(key)){
                if(!friendManager.getBlocked().isBlocked(user.getUserId()))
                    results.add(user);
            }
        }
        return results;
    }

    public static List<Group> groupSearch (String key){
        List<Group> results = new ArrayList<>();
        for(Group group: Newsfeed.groupManager.getGroups()){
            if(group.getGroupName().toLowerCase().contains(key))
                results.add(group);
        }
        return results;
    }
}