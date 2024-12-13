package connecthub.backend;

import connecthub.frontend.App;

import java.util.List;

public class Admin{
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
        List<String> members = Newsfeed.groupManager.searchGroupById(group.getGroupId()).getMembers();
        if(!group.getAdminsIds().contains(user.getUserId()))
            members.remove(user.getUserId());
    }

    public void managePosts(){

    }

    @Override
    public String toString(){
        return App.userAccountManager.searchById(userId).getName();
    }
}