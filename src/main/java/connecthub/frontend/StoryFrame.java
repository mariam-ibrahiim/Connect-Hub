package connecthub.frontend;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StoryFrame {
    public static VBox createStory (String name, String photoPath ,String storyText,String imagePath){
        VBox storyPanel = new VBox(20);
        HBox profileIdentifier = new HBox(20);
        Label userName = new Label(name);
     //   StackPane profilePhotoContainer = new StackPane();
        Image profilePhoto = new Image(new File(photoPath).toURI().toString());
        ImageView profilePhotoView = new ImageView(profilePhoto);
/*         profilePhotoContainer.getChildren().add(profilePhotoView);
        profileIdentifier.getChildren().addAll(profilePhotoContainer,name); */
        profileIdentifier.getChildren().addAll(profilePhotoView,userName);
        Circle circle= new Circle();
        circle.radiusProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerXProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerYProperty().bind(profilePhotoView.fitHeightProperty().divide(2));
        profilePhotoView.setClip(circle);
        profileIdentifier.setAlignment(Pos.CENTER_LEFT);
        profilePhotoView.setFitWidth(50);
        profilePhotoView.setFitHeight(50);
        profilePhotoView.setPreserveRatio(true);
        //temp
      //  Label userName = new Label("John Doe");
        //user.getProfilePhoto()
        
      //  Text text = new Text(story.getContentDetails().getText());
        Text text = new Text(storyText);
        StackPane textPanel = new StackPane(new TextFlow(text));
        Image attachedImage = new Image(new File(imagePath).toURI().toString());
        ImageView attachedImageView = new ImageView(attachedImage);
        attachedImageView.setFitWidth(500);
        attachedImageView.setFitHeight(600);
        attachedImageView.setPreserveRatio(true);     
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(attachedImageView.getFitWidth());
        rectangle.setHeight(attachedImageView.getFitHeight());
        attachedImageView.setClip(rectangle);
        storyPanel.getChildren().addAll(profileIdentifier,textPanel,attachedImageView);
        return storyPanel;
    }


}
