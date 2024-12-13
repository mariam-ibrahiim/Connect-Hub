package connecthub.frontend;


import connecthub.backend.UserAccountManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    public static final UserAccountManager userAccountManager = UserAccountManager.getInstance();
    public static Scene menuScene;
    private static Stage primaryStage;
    public static void main(String args[]){
        launch();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Connect-Hub");

        Button signupButton = new Button("Sign Up");
        Button loginButton = new Button("Login");

        VBox vBox = new VBox(20,signupButton,loginButton);
        vBox.getStyleClass().add("VBox");
        Scene menuScene = new Scene(vBox,250,250);



        signupButton.setOnAction(e->{
            SignupForm.show(stage,menuScene);
        });
        loginButton.setOnAction(e->{
            LoginForm.show(stage,menuScene);
        });


        menuScene.getStylesheets().add(App.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(menuScene);
        stage.show();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
}




