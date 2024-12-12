package connecthub.frontend;

import connecthub.backend.*;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ViewGroupsWindow {
    public static void show(User user){
        Stage stage = new Stage();
        stage.setTitle("Groups");
        Scene scene;
        stage.initModality(Modality.WINDOW_MODAL);

        VBox vBox = new VBox(10);
        List<Group> groups = Newsfeed.groupManager.getGroups();
        for(Group group : groups){
            if(group.isMember(user.getUserId())){
                Label groupName = new Label(group.getGroupName());
                System.out.println(groupName);
                vBox.getChildren().add(groupName);
            }
        }
        vBox.getStyleClass().add("VBox");
        ScrollPane scrollPane = new ScrollPane(vBox);

        scene = new Scene(scrollPane,400,250);
        scene.getStylesheets().add(LoginForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}