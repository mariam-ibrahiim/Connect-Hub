package connecthub.frontend;

import connecthub.backend.Admin;
import connecthub.backend.Group;
import connecthub.backend.PrimaryAdmin;
import connecthub.backend.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class GroupProfile {
    private static Stage stage = new Stage();
    private static Scene scene;

    public static void showProfile(Group group,Admin admin){

    }
    public static void showProfile(Group group,PrimaryAdmin primaryAdmin){
        stage.setTitle("Group");

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


        groupIdentifier.getChildren().addAll(profilePhotoView,groupName);


        VBox menuVbox = new VBox(10);
        menuVbox.setPadding(new Insets(10,10,10,10));

        Button addPostButton = new Button("Add post");
        Button promoteAdminButton = new Button("Promote Admin");
        Button demoteAdminButton = new Button("Demote Admin");
        Button removeMemberButton = new Button("Remove Admin");
        Button deleteGroupButton = new Button("Delete Group");

        menuVbox.getChildren().addAll(addPostButton,promoteAdminButton,demoteAdminButton,removeMemberButton,deleteGroupButton);


        pane.setTop(groupIdentifier);
        pane.setLeft(menuVbox);

        scene = new Scene(pane,800,750);
        //     scene.getStylesheets().add(GroupProfile.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public static void showProfile(Group group,User user){

    }
}
