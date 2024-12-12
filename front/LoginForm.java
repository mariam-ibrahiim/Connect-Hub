package com.mycompany.mavenproject2.frontend;

import java.io.File;

import com.mycompany.mavenproject2.backend.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginForm {
    public static void show (Stage stage, Scene previousScene){
        stage.setTitle("LOGIN");
        Scene scene;

        Label usernameLabel = new Label("Username/Email");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");

        HBox hBox1 = new HBox(usernameLabel,usernameField);
        HBox hBox2 = new HBox(25,passwordLabel,passwordField);
        hBox1.getStyleClass().add("HBox");
        hBox2.getStyleClass().add("HBox");

        usernameLabel.getStyleClass().add("longLabel");

        Image iconImage = new Image(new File("resources\\back-icon.png").toURI().toString());
        ImageView iconView = new ImageView(iconImage);
        iconView.getStyleClass().add("backIcon");

        iconView.setFitWidth(25);
        iconView.setFitHeight(25);

        VBox vBox = new VBox(20,hBox1,hBox2,loginButton);
        vBox.getStyleClass().add("VBox");

        Pane pane = new Pane(iconView,vBox);

        vBox.setLayoutX(45);
        vBox.setLayoutY(70);

        loginButton.setOnAction(e->{
            User login = App.userAccountManager.login(usernameField.getText(),passwordField.getText());
            if(login!=null)
                NewsfeedPage.show(stage,login);
        });

        iconView.setOnMouseClicked(event -> {
            stage.setScene(previousScene);
        });

        scene = new Scene(pane,400,250);
        scene.getStylesheets().add(LoginForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}