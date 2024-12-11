package connecthub.backend;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoryManagement implements ContentManagement{
    private final StoryDatabase storyDatabase = StoryDatabase.getInstance();
    private static final Duration EXPIRATION_DURATION = Duration.ofHours(24);
    private static volatile StoryManagement instance;

    private StoryManagement() {
        //storyDatabase = new StoryDatabase();
       // storyDatabase.load();
        deleteExpiredStories();
    }

    public  static StoryManagement getInstance() {
        StoryManagement result = instance;
        if(result == null) {
            synchronized (StoryManagement.class) {
                result = instance;
                if(result == null) {
                    instance = result = new StoryManagement();
                }
            }
        }
        return result;
    }

    private boolean isStoryExpired(Content story){
        Duration timeElapsed = Duration.between(story.getTimestamp(), LocalDateTime.now());
        return timeElapsed.compareTo(EXPIRATION_DURATION) > 0;
    }

    private void deleteExpiredStories(){
        List<Content> stories = new ArrayList<>();
        for(Content story : storyDatabase.getStories()){
            if(isStoryExpired(story)){
                stories.add(story);
            }
        }
        for(Content story : stories){
            storyDatabase.deleteStory(story);
        }
        storyDatabase.save();
    }


/*    @Override
    public void deleteContent(Content story) {
        storyDatabase.deleteStory(story);
        storyDatabase.save();
    }*/

    @Override
    public void saveToDatabase() {
        storyDatabase.save();
    }
    public List<Story> searchStories(String authorId){
        List<Story> stories= new ArrayList<>();
        for(Content story : storyDatabase.getStories()){
            if(story.getAuthorId().equals(authorId)){
                stories.add((Story)story);
            }
        }
        return stories;
    }

    @Override
    public void reloadDatabase() {
        storyDatabase.load();
        deleteExpiredStories();
    }

    @Override
    public void createContent(String authorId, String text, String image) {
        storyDatabase.addStory(new Story(authorId, text, image));
        storyDatabase.save();
    }
}
