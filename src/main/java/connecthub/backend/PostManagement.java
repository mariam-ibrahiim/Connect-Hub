package connecthub.backend;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class PostManagement implements ContentManagement {
    private final PostDatabase postDatabase =  PostDatabase.getInstance();
    private static volatile PostManagement instance;


    private PostManagement() {
       // postDatabase = new PostDatabase();
        // storyDatabase.load();
    }
    public void editPost(Content post,String text,String imagePath){
        for(Content post1:postDatabase.getPosts()){
            if(post1.getContentId().equals(post.getContentId())){
                post1.getContentDetails().setText(text);
                post1.getContentDetails().setPhoto(imagePath);
            }
        }
        saveToDatabase();
    }

/*
    @Override
    public void addContent(Content post) {
        postDatabase.addPost(post);
        saveToDatabase();
    }
*/
        public void deletePost(Content post) {
        postDatabase.deletePost(post);
        System.out.println(post.getContentId() +" deleted");
        }

    public static PostManagement getInstance() {
        PostManagement result = instance;
        if(result == null){
            synchronized (PostManagement.class) {
                result = instance;
                if(result == null){
                    instance = result = new PostManagement();
                }
            }
         //   return result;
        }
        return result;
    }
    @Override
    public void saveToDatabase() {
        postDatabase.save();
    }

    public List<Post> searchPosts(String authorId) {
        List<Post> posts = new ArrayList<>();
        for (Content post : postDatabase.getPosts()) {
            if (post.getAuthorId().equals(authorId)) {
                posts.add((Post) post);
            }
        }
        return posts;
    }

    @Override
    public void reloadDatabase() {
        postDatabase.load();
    }

    @Override
    public void createContent(String authorId, String text, String image) {
        postDatabase.addPost(new Post(authorId, text, image));
        postDatabase.save();
    }
}
