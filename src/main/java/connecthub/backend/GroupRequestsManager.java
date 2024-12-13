package connecthub.backend;

import static connecthub.backend.Newsfeed.groupManager;

import java.util.List;

public class GroupRequestsManager {
    private final GroupRequestsDatabase groupRequestsDatabase = GroupRequestsDatabase.getInstance();
    private static GroupRequestsManager instance;

    private GroupRequestsManager(){
    }

    public static GroupRequestsManager getInstance(){
        if(instance == null)
            instance = new GroupRequestsManager();
        return instance;
    }

    //linear group search by id and returns the group
    public GroupRequest searchGroupRequestsById(String id){
        for(GroupRequest groupRequest:getRequests()){
            if(groupRequest.getGroupRequestId().equals(id))
                return groupRequest;
        }
        return null;
    }

    public GroupRequest searchGroupRequest(String userId,String groupId){
        for(GroupRequest groupRequest: getRequests()){
            if(groupRequest.getUserId().equals(userId) && groupRequest.getGroupId().equals(groupId))
            System.out.print("Goup request found");
            return groupRequest;
        }
        return null;
    }

    public List<GroupRequest> getRequests(){
        return groupRequestsDatabase.getRequests();
    }

    public void addRequest(GroupRequest groupRequest){
        groupRequestsDatabase.addRequest(groupRequest);
    }

    public void removeRequest(GroupRequest groupRequest){
        groupRequestsDatabase.removeRequest(groupRequest);
    }


    public void loadFromFile(){
        groupRequestsDatabase.load();
    }

    public void saveToFile() {
       groupRequestsDatabase.save();
    }
}
