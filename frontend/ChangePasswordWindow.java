package com.mycompany.mavenproject2.frontend;

import com.mycompany.mavenproject2.backend.UpdateProfile;
import com.mycompany.mavenproject2.backend.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePasswordWindow {
    public static void changePassword(User user, UpdateProfile updateProfile){
        Stage window = new Stage();
        Label oldPasswordLabel = new Label("Old Password");
        Label newPasswordLabel = new Label("New Password");
        TextField oldPassword = new TextField();
        TextField newPassword = new TextField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e->{
            updateProfile.updatePassword(user,oldPassword.getText(),newPassword.getText());
            window.close();
        });
        GridPane layout = new GridPane();
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.add(oldPasswordLabel, 0, 0);
        layout.add(oldPassword, 1, 0);
        layout.add(newPasswordLabel, 0, 1);
        layout.add(newPassword, 1, 1);
        layout.add(saveButton, 1, 2);
        layout.setPadding(new Insets(20));
        
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("Update Profile");
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
