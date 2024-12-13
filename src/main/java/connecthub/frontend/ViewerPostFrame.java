package connecthub.frontend;

import java.io.File;
import java.time.format.DateTimeFormatter;

import connecthub.backend.Content;
import connecthub.backend.Newsfeed;
import connecthub.backend.PostManagement;
import connecthub.backend.User;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ViewerPostFrame {
    public static VBox createPost(Content post, ObjectProperty<Image> profilePhoto){

        HBox profileIdentifier = new HBox(20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = post.getTimestamp().format(formatter);

        Label time = new Label(formattedDateTime);
        //profileIdentifier.setAlignment(Pos.CENTER_LEFT);


        User user = App.userAccountManager.searchById(post.getAuthorId());
        Label userName = new Label(user.getName());
        userName.getStyleClass().add("specialLabel");

       // Image profilePhoto = new Image(new File(user.getProfile().getProfilePhoto()).toURI().toString());
       // Image profilePhoto = new Image(user.getProfile().getProfilePhoto());


        ImageView profilePhotoView = new ImageView();
        profilePhotoView.imageProperty().bind(profilePhoto);
        CirclePhotoFrame.createCircleFrame(profilePhotoView);
        profilePhotoView.setFitWidth(50);
        profilePhotoView.setFitHeight(50);
        profilePhotoView.setPreserveRatio(true);

        profileIdentifier.getChildren().addAll(profilePhotoView,userName,time);



        //adding caption to the post
        Text caption = new Text(post.getContentDetails().getText());
        StackPane textPanel = new StackPane(new TextFlow(caption));


        VBox postFrame = new VBox(20);

        //adding image to a rectangle shape
        if(post.getContentDetails().getPhoto()!=null){
        
        Image attachedImage = new Image(new File(post.getContentDetails().getPhoto()).toURI().toString()); //why was it new Image(new File(user.getProfile().getProfilePhoto()).toURI().toString());
        ImageView attachedImageView = new ImageView(attachedImage);
        attachedImageView.setFitWidth(300);
        attachedImageView.setFitHeight(300);
        attachedImageView.setPreserveRatio(true);     
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(attachedImageView.getFitWidth());
        rectangle.setHeight(attachedImageView.getFitHeight());
        attachedImageView.setClip(rectangle);

        postFrame.getChildren().addAll(profileIdentifier,textPanel,attachedImageView);}
        else{
            postFrame.getChildren().addAll(profileIdentifier,textPanel);
        }
        postFrame.getStylesheets().add(PostFrame.class.getResource("/css/Post.css").toExternalForm());

        return postFrame;
    }
}
