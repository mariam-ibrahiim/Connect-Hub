package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private final GroupDatabase groupDatabase = GroupDatabase.getInstance();
    private static GroupManager instance;

    private GroupManager(){
    }

    public static GroupManager getInstance(){
        if(instance == null)
            instance = new GroupManager();
        return instance;
    }

    //linear group search by id and returns the group
    public Group searchGroupById(String id){
        for(Group group: getGroups()){
            if(group.getGroupId().equals(id))
                return group;
        }
        return null;
    }

    public List<Group> getGroups(){
        return groupDatabase.getGroups();
    }

    public void addGroup(Group group){
        groupDatabase.addGroup(group);
    }

    public void removeGroup(Group group){
        groupDatabase.removeGroup(group);
    }


    public void loadFromFile(){
        groupDatabase.load();
    }

    public void saveToFile() {
        groupDatabase.save();
    }
}