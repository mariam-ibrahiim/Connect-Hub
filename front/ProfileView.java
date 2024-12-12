package com.mycompany.mavenproject2.frontend;

import com.mycompany.mavenproject2.backend.User;
import com.mycompany.mavenproject2.backend.Story;
import com.mycompany.mavenproject2.backend.PostManagement;
import com.mycompany.mavenproject2.backend.Profile;
import com.mycompany.mavenproject2.backend.StoryManagement;
import com.mycompany.mavenproject2.backend.Post;
import com.mycompany.mavenproject2.backend.UpdateProfile;
import com.mycompany.mavenproject2.backend.Newsfeed;
import com.mycompany.mavenproject2.backend.FriendManagement;
import java.io.File;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ProfileView{
  private ImageView profilePhotoView = new ImageView();
   private  ObjectProperty<Image> profilePhoto = new SimpleObjectProperty<>();
    private StackPane bioPanel;
    private Scene scene;
    public ProfileView(Stage window, Scene previousScene, User user, FriendManagement friendManager){
        User oldUser = App.userAccountManager.searchById(user.getUserId());
        Profile profile = oldUser.getProfile();
        BorderPane layout = new BorderPane();
        VBox leftPanel = new VBox(60);
       // VBox centerPanel = new VBox(20);
        ScrollPane centerPanel = new ScrollPane();
        HBox profileIdentifier = new HBox(20);
        Button updateProfileButton = new Button("Update Profile");
        Button addPostButton = new Button("Add Post");
        Button addStoryButton = new Button("Add Story");
        Button manageFriendsButton = new Button("Manage Friends");
        Button logoutButton = new Button("Logout");
        updateProfileButton.setOnAction(e->{
           // new UpdateProfileWindow(new UpdateProfile(profile));
            UpdateProfileWindow.updateProfile(oldUser,new UpdateProfile(profile),this);
        });
        addPostButton.setOnAction(e->{
            AddContent.addContent(oldUser,Newsfeed.postManagement);
            showPostsAction( user,centerPanel,profilePhoto);
        });
        addStoryButton.setOnAction(e->{
            AddContent.addContent(oldUser,Newsfeed.storyManagement);
        });
        manageFriendsButton.setOnAction(e->{
          FriendManagementFront friendManagementFront = new FriendManagementFront(friendManager,App.userAccountManager);
          friendManagementFront.setVisible(true);
          friendManagementFront.setLocationRelativeTo(null);
          friendManagementFront.putRequests(friendManager);
          friendManagementFront.putFriends(friendManager);
          friendManagementFront.putBlocked(friendManager);
          friendManagementFront.putSuggested(friendManager);
      });

        logoutButton.setOnAction(e->{
            App.userAccountManager.logout(oldUser);
            window.close();
        });
        StackPane updateProfileButtonContainer = new StackPane();
        updateProfileButtonContainer.getChildren().add(updateProfileButton);
        Label name = new Label(user.getName());
     //   StackPane profilePhotoContainer = new StackPane();

        profilePhoto.set(new Image(new File(profile.getProfilePhoto()).toURI().toString()));
        profilePhotoView.imageProperty().bind(profilePhoto);
        Text bioText = new Text(profile.getBio());
        bioPanel = new StackPane(new TextFlow(bioText));
/*         profilePhotoContainer.getChildren().add(profilePhotoView);
        profileIdentifier.getChildren().addAll(profilePhotoContainer,name); */
        profileIdentifier.getChildren().addAll(profilePhotoView,name);
        Circle circle= new Circle();
        circle.radiusProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerXProperty().bind(profilePhotoView.fitWidthProperty().divide(2));
        circle.centerYProperty().bind(profilePhotoView.fitHeightProperty().divide(2));
        profilePhotoView.setClip(circle);
        Image iconImage = new Image(new File("resources\\back-icon.png").toURI().toString());
        ImageView iconView = new ImageView(iconImage);
        iconView.getStyleClass().add("backIcon");

        iconView.setFitWidth(25);
        iconView.setFitHeight(25);
        


        leftPanel.getChildren().addAll(iconView,profileIdentifier,bioPanel,updateProfileButtonContainer,addPostButton,addStoryButton,manageFriendsButton,logoutButton);
        ScrollPane leftScrollPane = new ScrollPane(leftPanel);

        HBox topPanel = new HBox(20);
        Button showPosts = new Button("Posts");
        Button showFriends = new Button("Friends");
      //  Button showStories = new Button("Stories");
        topPanel.getChildren().addAll(showPosts,showFriends);

        profilePhotoView.setFitWidth(150);
        profilePhotoView.setFitHeight(150);
        profilePhotoView.setPreserveRatio(true);

        showFriends.setOnAction(e->{
            BorderPane.setAlignment(topPanel, Pos.TOP_RIGHT);
          friendManager.load();
          centerPanel.setContent(null);
           VBox friendsVbox = new VBox(10);
           friendsVbox.getChildren().clear();
           for (String friendId : friendManager.getFriends().getFriendsIds()) {
               User friend = App.userAccountManager.searchById(friendId);
               HBox friendIdentifier = new HBox(20);
               ObjectProperty<Image> friendImage = new SimpleObjectProperty<>();
               friendImage.set(new Image(new File(friend.getProfile().getProfilePhoto()).toURI().toString()));
               ImageView friendView = new ImageView();
               friendView.imageProperty().bind(friendImage);
               CirclePhotoFrame.createCircleFrame(friendView);
               friendView.setCursor(Cursor.HAND);
               friendView.setFitWidth(40);
               friendView.setFitHeight(40);
               Label friendName = new Label(friend.getName());
            //   friendName.getStyleClass().add("specialLabel");
               Label status = new Label(friend.getStatus());
               friendIdentifier.getChildren().addAll(friendView,friendName,status);
               friendsVbox.getChildren().add(friendIdentifier);
           }
            centerPanel.setContent(friendsVbox);
        });

        showPosts.setOnAction(e->{
          showPostsAction(user, centerPanel,profilePhoto);
           // centerPanel.getChildren().clear();
/*            centerPanel.setContent(null);
           VBox content = new VBox(20);
            List<Post> posts = postManagement.searchPosts(user.getUserId());  */
 /*            for(Content post : posts){
               centerPanel.getChildren().add(PostFrame.createPost(post));
                content.getChildren().add(PostFrame.createPost(post,userAccountManager));} */
/*             for(int i=posts.size()-1;i>=0;i--){
                content.getChildren().add(PostFrame.createPost(posts.get(i),userAccountManager));
            }
        
        centerPanel.setContent(content); */
        });
        //temp
     //   ImageView image = CirclePhotoFrame.createCircleFrame(user.getProfile().getProfilePhoto());
        profilePhotoView.setCursor(Cursor.HAND);
/*         showStories.setOnAction(e->{
            centerPanel.getChildren().clear();
            centerPanel.getChildren().add(image);
           // centerPanel.setAlignment(Pos.CENTER);
            topPanel.setAlignment(Pos.CENTER_RIGHT);
        }); */
        profilePhotoView.setOnMouseClicked(e->{
          //  centerPanel.getChildren().clear();
/*             StoryManagement storyManagement = new StoryManagement();
            List<Story> stories = storyManagement.searchStories(user.getUserId());
            StackPane storyFrame = new StackPane();
            for (Node frame : storyFrame.getChildren()) {
                 frame.setVisible(false);
            }
            storyFrame.getChildren().get(0).setVisible(true);
           // centerPanel.getChildren().add(storyFrame); */
           StoryManagement searchStories =(StoryManagement) Newsfeed.storyManagement;
           List<Story> stories = searchStories.searchStories(user.getUserId());
           if(!stories.isEmpty())
           StorySlider.createStories(layout,stories,user.getName(),profile.getProfilePhoto());
         //  layout.getChildren().add(storyFrame);
    
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
      //  ScrollPane root = new ScrollPane(layout);
      //  Scene scene = new Scene(root, 800, 600);
        scene = new Scene(layout, 800, 600);
        layout.getStylesheets().add(getClass().getResource("/css/Profile.css").toExternalForm());
        updateProfileButton.getStyleClass().add("update-profile-button");
        name.getStyleClass().add("user-name");
        profileIdentifier.getStyleClass().add("profile-identifier");
        leftPanel.getStyleClass().add("left-panel");
        showPosts.getStyleClass().add("top-button");
        showFriends.getStyleClass().add("top-button");
        centerPanel.getStyleClass().add("center-scroll-pane");
      //  showStories.getStyleClass().add("top-button");
        topPanel.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
       // profilePhotoContainer.getStyleClass().add("profile-photo-container");

    }
    public void show(Stage stage) {
        stage.setScene(scene);
        stage.show();
    }
    private void showPostsAction(User user,ScrollPane centerPanel,ObjectProperty<Image> profilePhoto){
      centerPanel.setContent(null);
      VBox content = new VBox(20);
      PostManagement searchPosts = (PostManagement) Newsfeed.postManagement;
       List<Post> posts = searchPosts.searchPosts(user.getUserId()); ;
/*            for(Content post : posts){
          centerPanel.getChildren().add(PostFrame.createPost(post));
           content.getChildren().add(PostFrame.createPost(post,userAccountManager));} */
       for(int i=posts.size()-1;i>=0;i--){
           content.getChildren().add(PostFrame.createPost(posts.get(i),profilePhoto));
    }
       
    centerPanel.setContent(content);
}
public void setProfilePhoto(Image image){
//profilePhotoView.setImage(image);
    profilePhoto.set(image);
}
public void setBio(String text){
    bioPanel.getChildren().clear();
    Text bioText = new Text(text);
    bioPanel.getChildren().add(new TextFlow(bioText));
}
public ObjectProperty<Image> getprofilePhoto(){
        return profilePhoto;
}
}
