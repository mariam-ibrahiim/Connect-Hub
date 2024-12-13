package connecthub.frontend;

import java.util.List;
import java.util.Stack;

import connecthub.backend.Admin;
import connecthub.backend.Group;
import connecthub.backend.Newsfeed;
import connecthub.backend.PrimaryAdmin;
import connecthub.backend.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupRequestWindow {
       public static void show(String userId,String groupId){
        Stage stage = new Stage();
        stage.setTitle("Groups");
        Scene scene;
        stage.initModality(Modality.WINDOW_MODAL);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        Button acceptButton = new Button("Accept");
        Button declineButton = new Button("Decline");
       // hBox.getStyleClass().add("VBox");
        ScrollPane scrollPane = new ScrollPane(hBox);

        Group group =  Newsfeed.groupManager.searchGroupById(groupId);
        acceptButton.setOnAction(e->{
             Admin admin = group.getAdmin(userId);
             if(admin!=null){
                admin.approveRequest(App.userAccountManager.searchById(userId), group);
             }
             else {
                PrimaryAdmin primaryAdmin = group.getPrimaryAdmin();
                if(primaryAdmin!=null)
                primaryAdmin.approveRequest(App.userAccountManager.searchById(userId), group);
             }
                stage.close();
        });

        declineButton.setOnAction(e->{
            Admin admin = group.getAdmin(userId);
            if(admin!=null){
                admin.declineRequest(App.userAccountManager.searchById(userId), group);
            }
            else {
                PrimaryAdmin primaryAdmin = group.getPrimaryAdmin();
                if(primaryAdmin!=null)
                primaryAdmin.declineRequest(App.userAccountManager.searchById(userId), group);
            }
            stage.close();
        });
        hBox.getChildren().addAll(acceptButton,declineButton);

        scene = new Scene(scrollPane,300,250);
        scene.getStylesheets().add(LoginForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
