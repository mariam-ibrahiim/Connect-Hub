package connecthub.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private String groupName;
    private String groupId;
    private PrimaryAdmin primaryAdmin;
    private List<Admin> admins = new ArrayList<>();
    private List<String> members = new ArrayList<>();
    private Profile profile = new Profile();
    private String description = "No description";
    private List<Post> posts = new ArrayList<>();

    public Group(){

    }
    public Group(String groupName, PrimaryAdmin primaryAdmin) {
        this.groupName = groupName;
        this.groupId = UUID.randomUUID().toString();
        this.primaryAdmin = primaryAdmin;
        members.add(primaryAdmin.getUserId());
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


    public boolean isMember(String userId){
        return members.contains(userId);
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

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post){
        posts.add(post);
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void removeMember(String id){
        members.remove(id);
    }
    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Admin getAdmin(String userId){
        for(Admin admin : admins)
            if(admin.getUserId().equals(userId))
                return admin;
        return null;
    }

    @Override
    public String toString(){
        return groupName;
    }

}