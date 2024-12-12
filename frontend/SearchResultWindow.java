package com.mycompany.mavenproject2.frontend;

import com.mycompany.mavenproject2.backend.FriendManagement;
import com.mycompany.mavenproject2.backend.Friends;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchResultWindow {
    public static void show(Stage prevStage,Scene prevScene,FriendManagement friendManager,User searchResultUser){
        Stage stage = new Stage();
        stage.setTitle("Search Result");
        Scene scene;
        stage.initModality(Modality.WINDOW_MODAL);

        Button manageFriendshipButton = new Button();


        boolean isFriend = false;
        for(String friendId : friendManager.getFriends().getFriendsIds()){
            if(friendId.equals(searchResultUser.getUserId()))
                isFriend = true;
        }

        if(isFriend)
            manageFriendshipButton.setText("Remove Friend");
        else
            manageFriendshipButton.setText("Add Friend");

        Button blockButton = new Button("Block User");

        Button viewProfileButton = new Button("View Profile");

        VBox vBox = new VBox(20,manageFriendshipButton,blockButton,viewProfileButton);
        vBox.getStyleClass().add("VBox");

        if(isFriend)
            manageFriendshipButton.setOnAction(e->{
                friendManager.removeFriend(searchResultUser.getUserId());
                stage.close();
            });
        else
            manageFriendshipButton.setOnAction(e->{
                friendManager.addFriend(searchResultUser.getUserId());
                stage.close();
            });

        blockButton.setOnAction(e->{
            friendManager.block(searchResultUser.getUserId());
            stage.close();
        });

        viewProfileButton.setOnAction(e->{
            new OthersProfile(prevStage,prevScene,searchResultUser);
            stage.close();
        });


        scene = new Scene(vBox,400,250);
        scene.getStylesheets().add(LoginForm.class.getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}