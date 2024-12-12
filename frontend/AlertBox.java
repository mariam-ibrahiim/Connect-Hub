package com.mycompany.mavenproject2.frontend;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertBox {
    private static Stage window;

    public static void displayWarning(String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Message");

        Label label = new Label(message);
        Button okButton = new Button("OK");
        VBox layout = new VBox(15);
        //  StackPane layout = new StackPane();
        HBox buttonContainer  = new HBox(10);
        buttonContainer.getChildren().add(okButton);
        okButton.setOnAction(e -> window.close());

        label.getStyleClass().add("warning-message");
        okButton.getStyleClass().add("ok-button");
        layout.getStyleClass().add("layout");
        buttonContainer.getStyleClass().add("button-container");
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
    //    Image warningIcon = new Image("C:\\TERM 5\\Programming II\\Assignments\\Lab 5 copy - Copy\\Lab5\\GymMembershipManagementSystem\\warning.png");
       // Image warningIcon = new Image(getClass().getResource("warning.png").toExternalForm());
        Image warningIcon = new Image(new File("warning.png").toURI().toString());
        ImageView iconView = new ImageView(warningIcon);
        HBox warning = new HBox(20);
        warning.getStyleClass().add("warning");

        warning.getChildren().addAll(iconView,label);
        layout.getChildren().addAll(warning,buttonContainer);


        Scene alert = new Scene(layout);
        alert.getStylesheets().add(AlertBox.class.getResource("/css/AlertBox.css").toExternalForm());
        window.setScene(alert);
        window.showAndWait();
    }

    public static void displayMessage(String message){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Message");
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setContentText(message);
        confirmationAlert.showAndWait();
    }
}

