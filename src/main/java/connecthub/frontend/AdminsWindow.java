package connecthub.frontend;

import connecthub.backend.Admin;
import connecthub.backend.Group;
import connecthub.backend.Newsfeed;
import connecthub.backend.PrimaryAdmin;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
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

/*
public class AdminsWindow {
    public static void show(Group group, PrimaryAdmin primaryAdmin, Stage stage, Scene previousScene){
        Scene scene;

        for(Admin admin:group.getAdmins()) {
            HBox hBox = new HBox(40);
            Label adminName = new Label(App.userAccountManager.searchByUsername(admin.getUserId()).getName());
            Button demoteButton = new Button("Demote");
            hBox.getChildren().addAll(adminName,demoteButton);
        }


        VBox vBox = new

        scene = new Scene(pane,500,450);
        scene.getStylesheets().add(GroupProfile.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
*/
