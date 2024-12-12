package com.mycompany.mavenproject2.frontend;

import com.mycompany.mavenproject2.backend.UpdateProfile;
import com.mycompany.mavenproject2.backend.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangeBioWindow {
    public static void changeBio(User user, UpdateProfile updateProfile, String oldBio){
        Stage window = new Stage();
        TextField bio = new TextField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e->{
            updateProfile.updateBio(bio.getText());
            window.close();
        });
        GridPane layout = new GridPane();
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.add(bio, 0, 0);
        layout.add(saveButton, 1, 2);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("Update Profile");
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
