package connecthub.frontend;

import connecthub.backend.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class PrimaryAdminGroupProfile{
    public static void show(Group group,PrimaryAdmin primaryAdmin,Stage stage,Scene previousScene){
        stage.setTitle("Group");
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
            show(group, primaryAdmin, stage, previousScene);
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
        Button promoteButton = new Button("Promote");
        Button demoteButton = new Button("Demote");
        Button removeMemberButton = new Button("Remove");
        Button deleteGroupButton = new Button("Delete Group");

        demoteButton.getStyleClass().add("smallButton");
        promoteButton.getStyleClass().add("smallButton");
        removeMemberButton.getStyleClass().add("smallButton");


        //Promote user
        HBox promoteHbox = new HBox(10);

        ComboBox<User> membersComboBox = new ComboBox<>();
        membersComboBox.setPromptText("Group Members");
        for(String id:group.getMembers()) {
            User user = App.userAccountManager.searchById(id);
            Admin admin = group.getAdmin(user.getUserId());
            if(admin==null && !primaryAdmin.getUserId().equals(id))
                membersComboBox.getItems().add(user);
        }

        promoteButton.setOnAction(e -> {
            User selectedUser = membersComboBox.getValue();
            if(selectedUser!=null) {
                boolean ans = AlertBox.displayConfirmation("Are you sure you want to promote " + selectedUser.getName());
                if (ans) {
                    primaryAdmin.promoteUser(selectedUser, group);
                }
            }
        });
        promoteHbox.getChildren().addAll(membersComboBox,promoteButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        HBox demoteHbox = new HBox(10);
        ComboBox<Admin> adminComboBox = new ComboBox<>();
        adminComboBox.setPromptText("All admins");
        adminComboBox.getItems().addAll(group.getAdmins());

        demoteButton.setOnAction(e -> {
            Admin selectedAdmin = adminComboBox.getValue();
            if(selectedAdmin!=null) {
                boolean ans = AlertBox.displayConfirmation("Are you sure you want to demote " + App.userAccountManager.searchById(selectedAdmin.getUserId()).getName());
                if (ans) {
                    primaryAdmin.demoteAdmin(selectedAdmin, group);
                }
            }
        });
        demoteHbox.getChildren().addAll(adminComboBox,demoteButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HBox removeHbox = new HBox(10);

        ComboBox<User> allMembersComboBox = new ComboBox<>();
        allMembersComboBox.setPromptText("All Members");

        for(String id:group.getMembers()) {
            User user = App.userAccountManager.searchById(id);
            if(!primaryAdmin.getUserId().equals(id))
                allMembersComboBox.getItems().add(user);
        }

        removeMemberButton.setOnAction(e -> {
            User selectedUser = allMembersComboBox.getValue();
            if(selectedUser!=null) {
                boolean ans = AlertBox.displayConfirmation("Are you sure you want to remove " + selectedUser.getName());
                if (ans) {
                    primaryAdmin.removeMember(selectedUser, group);
                    for(int i=0; i<group.getPosts().size(); i++){
                        if(group.getPosts().get(i).getAuthorId().equals(selectedUser.getUserId()))
                            group.getPosts().remove(group.getPosts().get(i));
                    }
                    Newsfeed.groupManager.saveToFile();
                }
            }
        });
        removeHbox.getChildren().addAll(allMembersComboBox,removeMemberButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        deleteGroupButton.setOnAction(e->{
            primaryAdmin.deleteGroup(group);
            Newsfeed.groupManager.removeGroup(group);
            Newsfeed.groupManager.saveToFile();
            stage.setScene(previousScene);
        });

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(iconView,refreshView);
        menuVbox.getChildren().addAll(buttons,addPostButton,promoteHbox,demoteHbox,removeHbox,deleteGroupButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        addPostButton.setOnAction(e->{
            AddContent.addContent(primaryAdmin.getUserId(),group);
        });

        ScrollPane postScrollpane = new ScrollPane();
        showPosts(group,postScrollpane);

        pane.setTop(groupIdentifier);
        pane.setLeft(menuVbox);
        pane.setCenter(postScrollpane);

        scene = new Scene(pane,800,750);
        scene.getStylesheets().add(PrimaryAdminGroupProfile.class.getResource("/css/styles.css").toExternalForm());
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
            postsVBox.getChildren().add(PostFrame.createGroupPost(post,profile,group));
        }
        postScrollPane.setContent(postsVBox);
    }
}
