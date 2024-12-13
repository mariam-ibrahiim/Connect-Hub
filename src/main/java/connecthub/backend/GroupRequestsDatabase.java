package connecthub.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupRequestsDatabase implements Database{
    private List<GroupRequest> requests = new ArrayList<>();

    private static GroupRequestsDatabase instance;

    private GroupRequestsDatabase(){
        load();
    }

    public static GroupRequestsDatabase getInstance(){
        if(instance==null)
            instance = new GroupRequestsDatabase();
        return instance;
    }

    public List<GroupRequest> getRequests() {
        return requests;
    }

    public void addRequest(GroupRequest groupRequest){
        requests.add(groupRequest);
    }

    public void removeRequest(GroupRequest groupRequest){
        requests.remove(groupRequest);
    }

    @Override
    public void save() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\" + Constants.GROUP_REQUESTS_FILENAME + ".json"), requests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("resources\\database\\" + Constants.GROUP_REQUESTS_FILENAME + ".json");
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try {
            requests = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, GroupRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
