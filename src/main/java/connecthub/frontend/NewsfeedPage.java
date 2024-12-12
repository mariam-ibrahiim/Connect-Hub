package connecthub.frontend;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import connecthub.backend.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewsfeedPage {

private static ProfileView userProfile;
    public static void show (Stage stage, User currentUser){
        final Newsfeed newsfeed = new Newsfeed(currentUser);

        //  Friends friends = new Friends();
        //Adding all users to suggested friends except the currentUser's friends and the currentUser itself


        //  FriendManagement friendManager = new FriendManagement(currentUser.getUserId(),friendRequestManagement);
        FriendManagement friendManager = newsfeed.getFriendManagement();
        // friendManager.populateSuggested(App.userAccountManager.getDatabase());
        //  friendManager.setSuggested(suggested);

        stage.setTitle("Newsfeed");
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane,900,750);

        stage.setOnCloseRequest(e->{
            App.userAccountManager.logout(currentUser);
        });

        //instantiate User profile
       userProfile =  new ProfileView(stage, scene,currentUser,friendManager);




        ///Testt
        //Creating the profile menu on the left side of the newsfeed
        HBox profileIdentifier = new HBox(20);
  /*      ObjectProperty<Image> profilePhoto = new SimpleObjectProperty<>();
        profilePhoto.set(new Image(new File(currentUser.getProfile().getProfilePhoto()).toURI().toString()));

        ImageView profilePhotoView = new ImageView();
        profilePhotoView.imageProperty().bind(profilePhoto);*/

        profileIdentifier.setCursor(Cursor.HAND);
        profileIdentifier.setOnMouseClicked(e->{
            userProfile.show(stage);
        });


        Button manageFriendsButton = new Button("Manage Friends");
        Button addStoryButton = new Button("Add Story");
        Button addPostButton = new Button("Add Post");
        Button createGroupButton = new Button("Create a group");
        Button notificationButton = new Button("Notifications");
        Button viewGroupsButton = new Button("View Groups");
        Button logoutButton = new Button("Logout");
        Image refreshImage = new Image(new File("C:\\Users\\Nadine\\Desktop\\LAAB10\\Connect-Hub-Lab-10\\src\\main\\resources\\refresh.png").toURI().toString());
        ImageView refreshView = new ImageView(refreshImage);
        refreshView.setFitWidth(25);
        refreshView.setFitHeight(25);
        refreshView.getStyleClass().add("refresh-icon");
        refreshView.setCursor(Cursor.HAND);

        manageFriendsButton.setOnAction(e->{
            manageFriends(friendManager);
        });
/* SwingUtilities.invokeLater(() -> {
        YourClassWithInitComponents window = new YourClassWithInitComponents();
        window.initComponents(); // Initialize your components
        window.showAsModal(); // Show the window as modal
    }); */

        addPostButton.setOnAction(e->{
            AddContent.addContent(currentUser, Newsfeed.postManagement);
        });
        addStoryButton.setOnAction(e->{
            AddContent.addContent(currentUser, Newsfeed.storyManagement);
        });

        createGroupButton.setOnAction(e->{
            CreateGroupWindow.show(currentUser);
        });
        viewGroupsButton.setOnAction(e->{
            ViewGroupsWindow.show(currentUser);
        });
        logoutButton.setOnAction(e->{
            App.userAccountManager.logout(currentUser);
            Platform.exit();
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Managing friends' stories view in the newsfeed
        //Search functionality
        HBox searchHbox = new HBox(25);

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        Button searchButton = new Button("Search");


        ListView<Object> listView = new ListView<>();
        listView.setVisible(false);

        listView.setPrefHeight(100);
        listView.setMaxWidth(160);
        listView.setTranslateY(-18);

        listView.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                listView.setVisible(false);
            }
        });
        searchButton.getStyleClass().add("searchButton");

        searchHbox.getChildren().addAll(searchField,searchButton);

        listView.setFocusTraversable(true);


        searchButton.setOnAction(e->{
            listView.getItems().clear();

            String key = searchField.getText();
            List<Object> results = Search.userSearch(key,friendManager);

            results.addAll(Search.groupSearch(key));

            results.remove(currentUser);

            for(Object u: results) {
                if (u instanceof User)
                    listView.getItems().add(u);
                if(u instanceof Group)
                    listView.getItems().add(u);
            }

            listView.setVisible(true);
        });


        listView.setOnMouseClicked(e->{
            Object selectedObject = listView.getSelectionModel().getSelectedItem();
            if(selectedObject instanceof User)
                SearchResultWindow.show(stage,scene,friendManager,(User) selectedObject);
            if(selectedObject instanceof Group) {
                //GroupProfile.show((Group) selectedObject,currentUser);
            }
            //new OthersProfile(stage,scene,selectedUser,friendManager);
        });

        VBox searchVbox = new VBox(10, searchHbox, listView);



        VBox menuVbox = new VBox(15,refreshView,profileIdentifier,manageFriendsButton,addStoryButton,addPostButton,createGroupButton,notificationButton,viewGroupsButton,logoutButton,searchVbox);
        menuVbox.setPadding(new Insets(10,0,0,10));


        ScrollPane storyScrollPane = new ScrollPane();
        storyScrollPane.setLayoutX(5);
        storyScrollPane.setLayoutY(5);
        storyScrollPane.setPrefWidth(740);
        storyScrollPane.setPrefHeight(80);



        ScrollPane friendScrollPane = new ScrollPane();
        //friendScrollPane.setLayoutX(600);
        //friendScrollPane.setLayoutY(90);
        friendScrollPane.setPrefWidth(140);
        friendScrollPane.setPrefHeight(500);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ImageView profilePhotoView = new ImageView();
        profilePhotoView.imageProperty().bind(userProfile.getprofilePhoto());
        //ImageView profilePhotoView = new ImageView("file:src/main/resources/airport.png");
        profilePhotoView.setFitWidth(50);
        profilePhotoView.setFitHeight(50);
        profilePhotoView.setPreserveRatio(true);

        CirclePhotoFrame.createCircleFrame(profilePhotoView);

        Label userName = new Label(currentUser.getName());
        userName.getStyleClass().add("specialLabel");
        // postsVbox.getStyleClass().add("VBox");
        ScrollPane postScrollPane = new ScrollPane();
        //postScrollPane.setPrefWidth(400);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Platform.runLater(() -> {

            profileIdentifier.getChildren().addAll(profilePhotoView,userName);

          //  renderComponents(friendManager, currentUser, storyScrollPane, pane, friendScrollPane, postScrollPane, newsfeed);
        });
        refreshView.setOnMouseClicked(e->{
            Platform.runLater(()->{
                profileIdentifier.getChildren().addAll(profilePhotoView,userName);
                //   renderComponents(friendManager,currentUser,storyScrollPane,pane,friendScrollPane,postScrollPane,newsfeed);
            });
        });
        friendScrollPane.setContent(null);
        VBox friendsVbox = new VBox(10);
        friendsVbox.getChildren().clear();
        Label friendlistLabel = new Label("Friends List");
        friendlistLabel.getStyleClass().add("specialLabel");
        friendsVbox.getChildren().add(friendlistLabel);
        for (String friendId : friendManager.getFriends().getFriendsIds()) {
            User friend = App.userAccountManager.searchById(friendId);
            HBox friendIdentifier = new HBox();
            ObjectProperty<Image> friendProfilePhoto = new SimpleObjectProperty<>();
            friendProfilePhoto.set(new Image(new File(friend.getProfile().getProfilePhoto()).toURI().toString()));
            ImageView friendView = new ImageView();
            friendView.imageProperty().bind(friendProfilePhoto);
            CirclePhotoFrame.createCircleFrame(profilePhotoView);
            friendView.setCursor(Cursor.HAND);
            friendView.setFitWidth(40);
            friendView.setFitHeight(40);
            Label friendName = new Label(friend.getName());
            friendName.getStyleClass().add("specialLabel");
            Label status = new Label(friend.getStatus());
            friendIdentifier.getChildren().addAll(friendView,friendName,status);
            friendsVbox.getChildren().add(friendIdentifier);
        }
        friendScrollPane.setContent(friendsVbox);
        friendScrollPane.setPrefWidth(200);
        showPosts(friendManager, newsfeed, currentUser, postScrollPane);
        HBox storyHbox = new HBox(10);
        for(String userId: friendManager.getFriends().getFriendsIds()){
            User friend = App.userAccountManager.searchById(userId);
            ObjectProperty<Image> storyImage = new SimpleObjectProperty<>();
            storyImage.set(new Image(new File(friend.getProfile().getProfilePhoto()).toURI().toString()));
            ImageView storyImageView = new ImageView();
            storyImageView.imageProperty().bind(storyImage);
            CirclePhotoFrame.createCircleFrame(storyImageView);
                 /*story.getImageView().setFitWidth(40);
                story.getImageView().setFitHeight(40);*/
            storyImageView.setOnMouseClicked(e ->{
                StoryManagement searchStories = (StoryManagement) Newsfeed.storyManagement;
                List<Story> stories = searchStories.searchStories(userId);
                if(stories==null)
                    return;
                StorySlider.createStories(
                        pane,
                        stories,
                        friend.getName(),
                        friend.getProfile().getProfilePhoto()
                );
            });

            storyImageView.getStyleClass().add("backIcon");
            storyHbox.getChildren().add(storyImageView);
        }
        storyScrollPane.setContent(storyHbox);

        pane.setRight(friendScrollPane);
        pane.setTop(storyScrollPane);
        pane.setCenter(postScrollPane);
        pane.setLeft(menuVbox);
        //ScrollPane profilePane = new ScrollPane();


        scene.getStylesheets().add(NewsfeedPage.class.getResource("/css/Newsfeed.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    private static void showPosts(FriendManagement friendManager,Newsfeed newsfeed,User user,ScrollPane postScrollPane){
        VBox postsVBox = new VBox();
        postScrollPane.setContent(null);
        List<Post> posts = newsfeed.getFriendsPosts(user.getUserId());
        for(Post post: posts){
         //   postsVBox.getChildren().add(PostFrame.createPost(post));
        }
        postScrollPane.setContent(postsVBox);
    }

/*

    private static void renderComponents(FriendManagement friendManager,User currentUser,ScrollPane storyScrollPane,BorderPane pane,ScrollPane friendScrollPane,ScrollPane postScrollPane,Newsfeed newsfeed){
        //   friendManager.load();
        newsfeed.reloadDatabase();
        friendManager.populateSuggested(App.userAccountManager.getDatabase());
        friendManager.load();
        //   Newsfeed.friendRequestManagement.load();
        //  manageFriends(friendManager);
        friendScrollPane.setContent(null);
        VBox friendsVbox = new VBox(10);
        friendsVbox.getChildren().clear();
        Label friendlistLabel = new Label("Friends List");
        friendlistLabel.getStyleClass().add("specialLabel");
        friendsVbox.getChildren().add(friendlistLabel);
        for (String friendId : friendManager.getFriends().getFriendsIds()) {
            User friend = App.userAccountManager.searchById(friendId);
            HBox friendIdentifier = new HBox();
            ImageView friendView = CirclePhotoFrame.createCircleFrame(friend.getProfile().getProfilePhoto());
            friendView.setCursor(Cursor.HAND);
            friendView.setFitWidth(40);
            friendView.setFitHeight(40);
            Label friendName = new Label(friend.getName());
            friendName.getStyleClass().add("specialLabel");
            Label status = new Label(friend.getStatus());
            friendIdentifier.getChildren().addAll(friendView,friendName,status);
            friendsVbox.getChildren().add(friendIdentifier);
        }
        friendScrollPane.setContent(friendsVbox);
        friendScrollPane.setPrefWidth(200);
        showPosts(friendManager, newsfeed, currentUser, postScrollPane);
        HBox storyHbox = new HBox(10);
        for(String userId: friendManager.getFriends().getFriendsIds()){
            User friend = App.userAccountManager.searchById(userId);
            ImageView storyImageView = CirclePhotoFrame.createCircleFrame(friend.getProfile().getProfilePhoto());
                 */
/*story.getImageView().setFitWidth(40);
                story.getImageView().setFitHeight(40);*//*

            storyImageView.setOnMouseClicked(e ->{
                StoryManagement searchStories = (StoryManagement) Newsfeed.storyManagement;
                List<Story> stories = searchStories.searchStories(userId);
                if(stories==null)
                    return;
                StorySlider.createStories(
                        pane,
                        stories,
                        friend.getName(),
                        friend.getProfile().getProfilePhoto()
                );
            });

            storyImageView.getStyleClass().add("backIcon");
            storyHbox.getChildren().add(storyImageView);
        }
        storyScrollPane.setContent(storyHbox);
    }
*/

    private static void manageFriends(FriendManagement friendManager){
        friendManager.populateSuggested(App.userAccountManager.getDatabase());
        friendManager.load();
        Newsfeed.friendRequestManagement.load();
        FriendManagementFront friendManagementFront = new FriendManagementFront(friendManager,App.userAccountManager);
        friendManagementFront.setVisible(true);
        friendManagementFront.setLocationRelativeTo(null);
        friendManagementFront.putRequests(friendManager);
        friendManagementFront.putFriends(friendManager);
        friendManagementFront.putBlocked(friendManager);
        friendManagementFront.putSuggested(friendManager);
    }
}