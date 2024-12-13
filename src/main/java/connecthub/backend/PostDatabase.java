package connecthub.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PostDatabase implements Database{
       List<Content> posts;
       private static volatile PostDatabase instance;

    private PostDatabase() {
        posts = new ArrayList<>();
        load();
    }
    public static PostDatabase getInstance() {
        PostDatabase result = instance;
        if (result == null) {
            synchronized (PostDatabase.class) {
                result = instance;
                if (result == null) {
                    instance = result = new PostDatabase();
                }
            }
        }
        return result;
    }
    public void save(){
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.registerModule(new JavaTimeModule());

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.POST_FILENAME+".json"), posts);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Content> getPosts() {
        return posts;
    }
    public void load(){
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.registerModule(new JavaTimeModule());
         File file = new File("resources\\database\\"+Constants.POST_FILENAME+".json");
         if (!file.exists() || file.length() ==0) {
            return;
        }
    

            try {
                posts = objectMapper.readValue(file,
                                                                 objectMapper.getTypeFactory().constructCollectionType(List.class,Post.class));
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  
        public void deletePost(Content post){
            for(Content p: posts){
                if(p.getContentId().equals(post.getContentId())){
                    posts.remove(p);
                    break;
                }
            }
            save();
        }
        public void addPost(Content post){
            posts.add(post);
        }
}
