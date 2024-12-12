package com.mycompany.mavenproject2.frontend;

import com.mycompany.mavenproject2.backend.User;
import com.mycompany.mavenproject2.backend.PrimaryAdmin;
import com.mycompany.mavenproject2.backend.Newsfeed;
import com.mycompany.mavenproject2.backend.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateGroupWindow {
    public static void show(User currentUser){
        Stage stage = new Stage();
        stage.setTitle("Create a group");
        Scene scene;
        stage.initModality(Modality.WINDOW_MODAL);

        Button saveButton = new Button("Save");

        Label groupNameLabel = new Label("Group Name");
        TextField groupNameField = new TextField();

        HBox hBox = new HBox(30,groupNameLabel,groupNameField);
        hBox.getStyleClass().add("HBox");

        VBox vBox = new VBox(20,hBox,saveButton);
        vBox.getStyleClass().add("VBox");


        saveButton.setOnAction(e->{
            Group group = new Group(groupNameField.getText(),new PrimaryAdmin(currentUser.getUserId()));
            Newsfeed.groupManager.addGroup(group);
            Newsfeed.groupManager.saveToFile();
            AlertBox.displayMessage(groupNameField.getText()+" group created successfully!");
            stage.close();
        });

        scene = new Scene(vBox,400,250);
        scene.getStylesheets().add(LoginForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}