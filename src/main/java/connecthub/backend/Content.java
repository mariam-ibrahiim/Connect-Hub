package connecthub.backend;
import java.time.LocalDateTime;
import java.util.UUID;


public  abstract class Content {
    private String contentId,authorId;
    protected LocalDateTime timestamp;
    private ContentDetails contentDetails;
       public Content(String authorId,String text,String image){
        contentId = UUID.randomUUID().toString();
        this.authorId=authorId;
        this.contentDetails=new ContentDetails(text,image);
        this.timestamp=LocalDateTime.now();
    }
    public Content(){
    } 
    public String getAuthorId(){
        return authorId;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public String getContentId() {
        return contentId;
    }
    public ContentDetails getContentDetails() {
        return contentDetails;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }
    
}
