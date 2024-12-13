package connecthub.frontend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import connecthub.backend.Post;
import connecthub.backend.PostManagement;
import connecthub.backend.Profile;
import connecthub.backend.Story;
import connecthub.backend.StoryManagement;
import connecthub.backend.UpdateProfile;
import connecthub.backend.User;
import connecthub.backend.UserAccountManager;
import connecthub.backend.Content;
import connecthub.backend.FriendManagement;
import connecthub.backend.Newsfeed;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OthersProfile{
    private ImageView profilePhotoView = new ImageView();
    private StackPane bioPanel;
    public OthersProfile(Stage window,Scene previousScene,User user){
        Profile profile = user.getProfile();
        BorderPane layout = new BorderPane();
        VBox leftPanel = new VBox(60);
        ScrollPane centerPanel = new ScrollPane();
        HBox profileIdentifier = new HBox(20);

        Label name = new Label(user.getName());
        Image profilePhoto = new Image(new File(profile.getProfilePhoto()).toURI().toString());
        profilePhotoView.setImage(profilePhoto);
        Text bioText = new Text(profile.getBio());
        bioPanel = new StackPane(new TextFlow(bioText));

        profileIdentifier.getChildren().addAll(profilePhotoView,name);
        Circle circle= new Circle();
        circle.radiusProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerXProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerYProperty().bind(profilePhotoView.fitHeightProperty().divide(2));
        profilePhotoView.setClip(circle);
        Image iconImage = new Image(App.class.getResource("/back-icon.png").toExternalForm());
        ImageView iconView = new ImageView(iconImage);
        iconView.getStyleClass().add("backIcon");

        iconView.setFitWidth(25);
        iconView.setFitHeight(25);

        leftPanel.getChildren().addAll(iconView,profileIdentifier,bioPanel);
        ScrollPane leftScrollPane = new ScrollPane(leftPanel);

        HBox topPanel = new HBox(20);
        Button showPosts = new Button("Posts");

        topPanel.getChildren().addAll(showPosts);

        profilePhotoView.setFitWidth(150);
        profilePhotoView.setFitHeight(150);
        profilePhotoView.setPreserveRatio(true);

        showPosts.setOnAction(e->{
            showPostsAction(user, centerPanel);
        });
        profilePhotoView.setCursor(Cursor.HAND);

        profilePhotoView.setOnMouseClicked(e->{
            StoryManagement searchStories =(StoryManagement) Newsfeed.storyManagement;
            List<Story> stories = searchStories.searchStories(user.getUserId());
            if(!stories.isEmpty())
                StorySlider.createStories(layout,stories,user.getName(),profile.getProfilePhoto());
        });

        iconView.setOnMouseClicked(event -> {
            window.setScene(previousScene);
        });

        Platform.runLater(() -> {
            showPosts.fire();
        });

        layout.setLeft(leftScrollPane);
        layout.setTop(topPanel);
        layout.setCenter(centerPanel);

        Scene scene = new Scene(layout, 800, 600);
        layout.getStylesheets().add(getClass().getResource("/css/Profile.css").toExternalForm());
        name.getStyleClass().add("user-name");
        profileIdentifier.getStyleClass().add("profile-identifier");
        leftPanel.getStyleClass().add("left-panel");
        showPosts.getStyleClass().add("top-button");

        centerPanel.getStyleClass().add("center-scroll-pane");

        topPanel.setAlignment(javafx.geometry.Pos.TOP_RIGHT);

        window.setScene(scene);
    }
    private void showPostsAction(User user,ScrollPane centerPanel){
        centerPanel.setContent(null);
        VBox content = new VBox(20);
        PostManagement searchPosts = (PostManagement) Newsfeed.postManagement;
        List<Post> posts = searchPosts.searchPosts(user.getUserId()); ;
           for(Post post : posts){
                User postOwner = App.userAccountManager.searchById(post.getAuthorId());
                ObjectProperty<Image> profile = new SimpleObjectProperty<>();
                profile.set(new Image(new File(postOwner.getProfile().getProfilePhoto()).toURI().toString()));
                content.getChildren().add(PostFrame.createPost(post,profile));
        }
        centerPanel.setContent(content);
        }
    public void setProfileView(Image image){
        profilePhotoView.setImage(image);
    }
    public void setBio(String text){
        bioPanel.getChildren().clear();
        Text bioText = new Text(text);
        bioPanel.getChildren().add(new TextFlow(bioText));
    }

}
