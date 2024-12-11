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
    public static void main(String args[]){
        launch();
    }

    @Override
    public void start(Stage stage) {
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
}















/* package connecthub.frontend;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import connecthub.backend.FriendManagement;
import connecthub.backend.FriendRequestManagement;
import connecthub.backend.Post;
import connecthub.backend.PostManagement;
import connecthub.backend.Profile;
import connecthub.backend.Story;
import connecthub.backend.StoryManagement;
import connecthub.backend.UpdateProfile;
import connecthub.backend.User;
import connecthub.backend.UserAccountManager;
import connecthub.backend.UserDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

public class App extends Application
{
    public static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        launch(args);
    }    
  
    @Override
    public void start(Stage primaryStage) throws Exception {

        UserAccountManager userAccountManager = new UserAccountManager();
        PostManagement postManagement = new PostManagement();
        StoryManagement storyManagement = new StoryManagement();
     //   VBox layout = new VBox();
        VBox layout = new VBox(20);
        User user = new User("ss","m@gmail.com","eee","12234","12/12/22","online");
        User user2 = userAccountManager.searchById("505092eb-3e55-4d8e-9ba5-68e68e752400");
       userAccountManager.addUser(user);
        userAccountManager.addUser(user2);
        userAccountManager.save();
        Post post1 = new Post(user.getUserId(), "New post", "resources//profilePhoto.jpg");
        postManagement.addContent(post1);
        Post post2 = new Post(user.getUserId(), "New post", "resources//profilePhoto.jpg");
        postManagement.addContent(post2);
        postManagement.saveToDatabase();
        Story story = new Story(user.getUserId(), "New new", "resources//avatar2.png");
        Story story2 = new Story(user.getUserId(), "New bs", "resources//profilePhoto.jpg");
        Story story3 = new Story(user.getUserId(), "Newest", "resources//avatar2.png");
        storyManagement.addContent(story);
        storyManagement.addContent(story2);
        storyManagement.addContent(story3);
        postManagement.saveToDatabase();
        Button showProfile = new Button("Show Profile");
        showProfile.setOnAction(e -> {
            new ProfileView(primaryStage,user2,userAccountManager,postManagement,storyManagement);
        });

        List<Post> posts = postManagement.searchPosts(user.getUserId()); 
        layout.getChildren().add(showProfile);
        for(Post post : posts){
            layout.getChildren().add(PostFrame.createPost(post,userAccountManager));
        }
        //layout.getChildren().addAll(posts);
        ScrollPane root = new ScrollPane(layout);
        Scene scene = new Scene(root,700,500);
        ImageView img = CirclePhotoFrame.createCircleFrame(user.getProfile().getProfilePhoto());
        img.setOnMouseClicked(e->{
          //  VBox storyFrame = StorySlider.createStory(layout,story);
        //    layout.getChildren().add(storyFrame);
        });

                FriendRequestManagement friendRequestManagement = new FriendRequestManagement();
        User one = new User("1","1","1","1","1");
        User two = new User("2","2","2","2","2");
        User three = new User("3","3","3","3","3");
        User four = new User("4","4","4","4","4");
        userAccountManager.addUser(one);
        userAccountManager.addUser(two);
        userAccountManager.addUser(three);
        userAccountManager.addUser(four);
        userAccountManager.logout(one);
        FriendManagement friendManagement1 = new FriendManagement(one.getUserId(), friendRequestManagement);
        FriendManagement friendManagement2 = new FriendManagement(two.getUserId(),friendRequestManagement);
        FriendManagement friendManagement3 = new FriendManagement(three.getUserId(),friendRequestManagement);
        FriendManagement friendManagement4 = new FriendManagement(four.getUserId(),friendRequestManagement);
        friendManagement2.addFriend(one.getUserId());
        friendManagement3.addFriend(one.getUserId());
        friendManagement4.addFriend(one.getUserId());

        FriendManagementFront w = new FriendManagementFront(friendManagement1,userAccountManager);
        layout.getChildren().add(img);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        layout.getStyleClass().add("layout");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Hub");
        primaryStage.show();
    }
      }


 */