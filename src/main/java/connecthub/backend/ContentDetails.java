package connecthub.backend;

public class ContentDetails {
    String text,photo;

    public ContentDetails(String text, String photo) {
        this.text = text;
        this.photo = photo;
    }

    public ContentDetails() {
    }

    public String getText() {
        return text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
}
