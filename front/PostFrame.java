package com.mycompany.mavenproject2.frontend;

import java.io.File;
import java.time.format.DateTimeFormatter;

import com.mycompany.mavenproject2.backend.Content;
import com.mycompany.mavenproject2.backend.User;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class PostFrame {

    /*method responsible for creating a template for posts
      it takes the content and user manager to define the post author's name */
    public static VBox createPost(Content post, ObjectProperty<Image> profilePhoto){

        HBox profileIdentifier = new HBox(20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = post.getTimestamp().format(formatter);

        Label time = new Label(formattedDateTime);
        //profileIdentifier.setAlignment(Pos.CENTER_LEFT);


        User user = App.userAccountManager.searchById(post.getAuthorId());
        Label userName = new Label(user.getName());
        userName.getStyleClass().add("specialLabel");

       // Image profilePhoto = new Image(new File(user.getProfile().getProfilePhoto()).toURI().toString());
       // Image profilePhoto = new Image(user.getProfile().getProfilePhoto());


        ImageView profilePhotoView = new ImageView();
        profilePhotoView.imageProperty().bind(profilePhoto);
        CirclePhotoFrame.createCircleFrame(profilePhotoView);
        profilePhotoView.setFitWidth(50);
        profilePhotoView.setFitHeight(50);
        profilePhotoView.setPreserveRatio(true);

        profileIdentifier.getChildren().addAll(profilePhotoView,userName,time);



        //adding caption to the post
        Text caption = new Text(post.getContentDetails().getText());
        StackPane textPanel = new StackPane(new TextFlow(caption));


        VBox postFrame = new VBox(20);

        //adding image to a rectangle shape
        Image attachedImage = new Image(new File(post.getContentDetails().getPhoto()).toURI().toString()); //why was it new Image(new File(user.getProfile().getProfilePhoto()).toURI().toString());
        ImageView attachedImageView = new ImageView(attachedImage);
        attachedImageView.setFitWidth(300);
        attachedImageView.setFitHeight(300);
        attachedImageView.setPreserveRatio(true);     
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(attachedImageView.getFitWidth());
        rectangle.setHeight(attachedImageView.getFitHeight());
        attachedImageView.setClip(rectangle);

        postFrame.getChildren().addAll(profileIdentifier,textPanel,attachedImageView);
        postFrame.getStylesheets().add(PostFrame.class.getResource("/css/Post.css").toExternalForm());
     //  profileIdentifier.getStyleClass().add("profile-identifier");
        /*VBox vBox = new VBox(10);
        vBox.setPrefWidth(350);
        //vBox.setPrefHeight();
        Label label = new Label(ownerName);
        label.getStyleClass().add("specialLabel");
        vBox.getChildren().add(label);
        if(caption!=null){
            Label cap = new Label(caption);
            vBox.getChildren().add(cap);
        }
        if(image!=null){
            vBox.getChildren().add(postView);
        }
        return vBox;*/
        //userName.getStyleClass().add("user-name");
        return postFrame;
    }

}