package connecthub.backend;

import java.util.List;

public class DisplayProfile {
/*     PostManagement postManagement;
    StoryManagement storyManagement;
    
    public ProfileManagement(String userId) {
        postManagement = new PostManagement();
        storyManagement = new StoryManagement();
    } */
    public static List<Post> fetchPosts(PostManagement postManagement,User user){
       return  postManagement.searchPosts(user.getUserId());
    }

    public static List<Story> fetchStories(StoryManagement storyManagement,User user){
        return storyManagement.searchStories(user.getUserId());
    }
    
    //fetchFriends
}
