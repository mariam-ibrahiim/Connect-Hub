package connecthub.backend;

import java.util.List;

public class Admin {
    private String userId;

    public Admin(){

    }

    public Admin(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void approveRequest(User user){

    }

    public void declineRequest(User user){

    }

    public void removeMember(User user,Group group){
        List<User> members = Newsfeed.groupManager.searchGroupById(group.getGroupId()).getMembers();
        if(!group.getAdminsIds().contains(user.getUserId()))
            members.remove(user);
    }

    public void managePosts(){

    }

    @Override
    public String toString(){
        return userId;
    }
}