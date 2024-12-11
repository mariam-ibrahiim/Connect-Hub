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

public class StoryDatabase implements Database{
    List<Content> stories;
    private static volatile StoryDatabase instance;

    private StoryDatabase() {
        stories = new ArrayList<>();
        load();
    }

    public static StoryDatabase getInstance() {
        StoryDatabase result = instance;
        if (result == null) {
            synchronized (StoryDatabase.class) {
                result = instance;
                if(result == null) {
                    instance = result = new StoryDatabase();
                }
            }
        }
        return result;
    }
    public void save(){
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.registerModule(new JavaTimeModule());
           

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources\\database\\"+Constants.STORY_FILENAME+".json"), stories);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Content> getStories() {
        return stories;
    }
    public void load(){
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.registerModule(new JavaTimeModule());
         File file = new File("resources\\database\\"+Constants.STORY_FILENAME+".json");
         if(!file.exists() || file.length()==0)
         return;

            try {
                stories = objectMapper.readValue((file),
                                                                 objectMapper.getTypeFactory().constructCollectionType(List.class, Story.class));
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  
        public void addStory(Content story) {
            stories.add(story);
        }
        public void deleteStory(Content story) {
            stories.remove(story);
        }
}
