package connecthub.frontend;

import java.io.File;

import connecthub.backend.User;
import connecthub.backend.Validation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SignupForm {
    public static void show (Stage stage, Scene previousScene){

        stage.setTitle("SIGN UP");
        Scene scene;

        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField("");

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        Label birthdateLabel = new Label("Birthdate");
        DatePicker birthdateField = new DatePicker();

        Button signupButton = new Button("Sign Up");

        HBox hBox1 = new HBox(50,nameLabel,nameField);
        HBox hBox2 = new HBox(50,usernameLabel,usernameField);
        HBox hBox3 = new HBox(50,emailLabel,emailField);
        HBox hBox4 = new HBox(50,passwordLabel,passwordField);
        HBox hBox5 = new HBox(50,birthdateLabel,birthdateField);

        hBox1.getStyleClass().add("HBox");
        hBox2.getStyleClass().add("HBox");
        hBox3.getStyleClass().add("HBox");
        hBox4.getStyleClass().add("HBox");
        hBox5.getStyleClass().add("HBox");

        birthdateField.getStyleClass().add("longTextfield");

        VBox vBox = new VBox(20,hBox1,hBox2,hBox3,hBox4,hBox5,signupButton);
        vBox.getStyleClass().add("VBox");

        Image iconImage = new Image(new File("resources\\back-icon.png").toURI().toString());
        ImageView iconView = new ImageView(iconImage);
        iconView.getStyleClass().add("backIcon");

        Pane pane = new Pane(vBox,iconView);
        vBox.setLayoutX(45);
        vBox.setLayoutY(70);

        iconView.setFitWidth(25);
        iconView.setFitHeight(25);

        signupButton.setOnAction(e->{
            if(!Validation.isValidEmail(emailField.getText()) || !Validation.isValidPasswordLength(passwordField.getText())) {
                AlertBox.displayWarning("INVALID DATA ENTERED!");
                return;
            }

            User user = new User(nameField.getText(),emailField.getText(),usernameField.getText(),passwordField.getText(),birthdateField.getValue().toString(),"offline");

            boolean signed = App.userAccountManager.signUp(user);
            if(!signed) {
                AlertBox.displayWarning("Username already exists!");
                return;
            }

            AlertBox.displayMessage("Signed up successfully!");
        });

        iconView.setOnMouseClicked(event -> {
            stage.setScene(previousScene);
        });


        scene = new Scene(pane,400,400);
        scene.getStylesheets().add(SignupForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}