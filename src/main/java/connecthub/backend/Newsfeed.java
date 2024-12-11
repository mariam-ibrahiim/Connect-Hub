package connecthub.backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class Newsfeed {
   // public static final ContentManagement postManagement = new PostManagement();
    public static final ContentManagement postManagement = PostManagement.getInstance();
    public static final ContentManagement storyManagement = StoryManagement.getInstance();
    //singletom in those classes
    public static final FriendRequestManagement friendRequestManagement = FriendRequestManagement.getInstance();
    public static final GroupManager groupManager = GroupManager.getInstance();
    public static final NotficationSystem notficationSystem = NotficationSystem.getInstance();
    private FriendManagement friendManagement;

    public Newsfeed(User user) {

        friendManagement = FriendManagement.getInstance(user.getUserId(), friendRequestManagement);
    }

    public void reloadDatabase(){
        postManagement.reloadDatabase();
        storyManagement.reloadDatabase();
        friendRequestManagement.load();
        friendManagement.reloadDatabase();
    }
    public FriendManagement getFriendManagement() {
        return friendManagement;
    }



    public List<Post>getFriendsPosts(String userId)
    {
        PostManagement postsSearch =(PostManagement) postManagement;
        List <Post> newsFeedPosts = new ArrayList<>();
        Friends friends = friendManagement.getFriends();       
        if(friends!=null)
        {
            ArrayList<String> friendsIds = friends.getFriendsIds();
        for(String friendId : friendsIds)
        {
           List<Post> friendPosts = postsSearch.searchPosts(friendId);
           newsFeedPosts.addAll(friendPosts);
        }
        newsFeedPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());
        }
        return newsFeedPosts;
    }
    
    public List <Story> getFriendsStories(String userId)
    {
        StoryManagement storiesSearch = (StoryManagement)storyManagement;
        List <Story> newsFeedStories = new ArrayList<>();
        Friends friends = friendManagement.getFriends();
        if(friends!=null)
        {
            ArrayList<String> friendsId = friends.getFriendsIds();
        for(String friendId : friendsId)
        {
           List<Story> friendStories = storiesSearch.searchStories(friendId);
           newsFeedStories.addAll(friendStories);
        }
        }
        
        return newsFeedStories;
    }
    /* 
        public List<HashMap<User, String>> getListOfFriendsStatus () //lesa
    {
        List<HashMap<User, String>> friendsWithStatus= new ArrayList<>();
        Friends friends = friendManagement.getFriends();
        if(friends!=null)
        {
           
            ArrayList<String> friendsId = friends.getFriendsIds();
            for(String friendId : friendsId)
            {
                 User friend = userAccountManager.searchById(friendId);
                 if(friend!=null)
                 {
                String status = friend.getStatus();
                HashMap<User, String> friendWithStatus = new HashMap<>();
                friendWithStatus.put(friend, status);
                friendsWithStatus.add(friendWithStatus);
                 }
            }
        }
        return friendsWithStatus;
    } */
    
    public ArrayList<String> getListOfSuggestedFriends (String userId)
    {
        return friendManagement.getSuggested().getSuggestedIds();
    }
    
}