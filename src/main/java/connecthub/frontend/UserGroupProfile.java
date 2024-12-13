package connecthub.frontend;

import connecthub.backend.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class UserGroupProfile{
    public static void show(Group group,User user,Stage stage,Scene previousScene){
        Scene scene;

        BorderPane pane = new BorderPane();
        HBox groupIdentifier = new HBox(10);
        groupIdentifier.setPadding(new Insets(10,10,10,10));

        ImageView profilePhotoView = new ImageView();
        Image profilePhoto = new Image(new File(group.getProfile().getProfilePhoto()).toURI().toString());
        profilePhotoView.setImage(profilePhoto);
        CirclePhotoFrame.createCircleFrame(profilePhotoView);

        Label name = new Label(group.getGroupName());
        name.getStyleClass().add("SpecialLabel");

        Label description = new Label(group.getDescription());
        description.getStyleClass().add("descriptionLabel");

        VBox groupName = new VBox(10,name,description);

        //refresh button

        Image refreshImage = new Image(new File("resources\\refresh.png").toURI().toString());
        ImageView refreshView = new ImageView(refreshImage);
        refreshView.setFitWidth(25);
        refreshView.setFitHeight(25);
        refreshView.getStyleClass().add("refresh-icon");
        refreshView.setCursor(Cursor.HAND);

        refreshView.setOnMouseClicked(event -> {
            Newsfeed.groupManager.loadFromFile();
            show(group, user, stage, previousScene);
        });

        groupIdentifier.getChildren().addAll(profilePhotoView,groupName);

        //back button
        Image iconImage = new Image(App.class.getResource("/back-icon.png").toExternalForm());
        ImageView iconView = new ImageView(iconImage);
        iconView.setCursor(Cursor.HAND);
        iconView.getStyleClass().add("backIcon");

        iconView.setFitWidth(25);
        iconView.setFitHeight(25);


        //back to newsfeed
        iconView.setOnMouseClicked(event -> {
            stage.setScene(previousScene);
        });


        VBox menuVbox = new VBox(10);
        menuVbox.setPadding(new Insets(10,10,10,10));

        Button addPostButton = new Button("Add post");
        addPostButton.getStyleClass().add("smallButton");
        addPostButton.setOnAction(e->{
            AddContent.addContent(user.getUserId(),group);
        });

        Button leaveGroupButton = new Button("Leave");
        leaveGroupButton.getStyleClass().add("smallButton");
        leaveGroupButton.setOnAction(e->{
            group.removeMember(user.getUserId());
            for(int i=0; i<group.getPosts().size(); i++){
                if(group.getPosts().get(i).getAuthorId().equals(user.getUserId()))
                    group.getPosts().remove(group.getPosts().get(i));
            }
            Newsfeed.groupManager.saveToFile();
            stage.setScene(previousScene);
        });

        ScrollPane postsScrollPane = new ScrollPane();

        showPosts(group,postsScrollPane);

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(iconView,refreshView);
        menuVbox.getChildren().addAll(buttons,addPostButton,leaveGroupButton);


        pane.setTop(groupIdentifier);
        pane.setLeft(menuVbox);
        pane.setCenter(postsScrollPane);

        scene = new Scene(pane,800,750);
        scene.getStylesheets().add(UserGroupProfile.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
    private static void showPosts(Group group, ScrollPane postScrollPane){
        VBox postsVBox = new VBox(20);
        postScrollPane.setContent(null);
        List<Post> posts = group.getPosts();
        for(Post post: posts){
            User postOwner = App.userAccountManager.searchById(post.getAuthorId());
            ObjectProperty<Image> profile = new SimpleObjectProperty<>();
            profile.set(new Image(new File(postOwner.getProfile().getProfilePhoto()).toURI().toString()));
            postsVBox.getChildren().add(ViewerPostFrame.createPost(post,profile));
        }
        postScrollPane.setContent(postsVBox);
    }
}
