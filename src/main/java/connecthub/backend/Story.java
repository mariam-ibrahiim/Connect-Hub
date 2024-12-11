package connecthub.backend;

import java.time.LocalDateTime;
import java.util.UUID;

public class Story extends Content{
       public Story(String authorId,String text,String image){
        super(authorId,text,image);
    }
    public Story(){
    } 
}
