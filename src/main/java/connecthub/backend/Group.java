package connecthub.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private String groupName;
    private String groupId;
    private PrimaryAdmin primaryAdmin;
    private List<Admin> admins = new ArrayList<>();
    private List<User> members = new ArrayList<>();

    public Group(){

    }
    public Group(String groupName, PrimaryAdmin primaryAdmin) {
        this.groupName = groupName;
        this.groupId = UUID.randomUUID().toString();
        this.primaryAdmin = primaryAdmin;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PrimaryAdmin getPrimaryAdmin() {
        return primaryAdmin;
    }

    public void setPrimaryAdmin(PrimaryAdmin primaryAdmin) {
        this.primaryAdmin = primaryAdmin;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<String> getAdminsIds() {
        List<String> ids = new ArrayList<>();
        for(Admin admin: admins){
            ids.add(admin.getUserId());
        }
        return ids;
    }

    //NOTE CHECK IF CONTAINS DOESN'T RETURN THE SAME ADDRESS
    public boolean isAdmin(String userId){
        return getAdminsIds().contains(userId);
    }
    public void addAdmin(Admin admin){
        admins.add(admin);
    }
    public void removeAdmin(Admin admin){
        admins.remove(admin);
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
