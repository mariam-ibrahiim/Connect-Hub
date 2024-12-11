package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupDatabase implements Database{
    private static GroupDatabase instance;
    private List<Group> groups = new ArrayList<>();

    private GroupDatabase (){
        load();
    }

    public List<Group> getGroups(){
        return groups;
    }

    public static GroupDatabase getInstance(){
        if(instance==null)
            instance = new GroupDatabase();
        return instance;
    }

    @Override
    public void save(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.GROUP_FILENAME+".json"), groups);
            for(Group group:groups)
                System.out.println("group: "+group.getGroupName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\Nadine\\Desktop\\Trial\\Connect-Hub\\Connect-Hub\\Connect-Hub\\resources\\database\\" + Constants.GROUP_FILENAME + ".json");
        try {
            if (file.length() != 0) {
                groups = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Group.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void removeGroup(Group group){
        groups.remove(group);
    }
}