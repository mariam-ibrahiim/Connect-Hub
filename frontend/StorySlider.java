package com.mycompany.mavenproject2.frontend;

import java.io.File;
import java.util.List;

import com.mycompany.mavenproject2.backend.Content;
import com.mycompany.mavenproject2.backend.Post;
import com.mycompany.mavenproject2.backend.Story;
import com.mycompany.mavenproject2.backend.ContentManagement;
import com.mycompany.mavenproject2.backend.StoryManagement;
import com.mycompany.mavenproject2.backend.UpdateProfile;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StorySlider {
    private static int j=0;
    private static Pane storyPanel= new Pane();
    public static  GridPane createStories(Pane root,List<Story>stories,String name,String photoPath){
      if(stories.isEmpty() || stories==null)
      return null;
        GaussianBlur blur = new GaussianBlur(15);
        Stage backdrop = new Stage();
        backdrop.initOwner(root.getScene().getWindow());
        backdrop.initStyle(StageStyle.UNDECORATED);
        backdrop.initModality(Modality.APPLICATION_MODAL);
        root.setEffect(blur);
   
       // ScrollPane imageScroll = new ScrollPane(attachedImageView);
        Button closeButton = new Button("X");
        closeButton.setAlignment(Pos.TOP_RIGHT);

        closeButton.setOnAction(e -> {
            root.setEffect(null);
         //   root.getChildren().remove(storyFrame);
            backdrop.close();
        });
    //    root.getChildren().add(storyFrame);
    setStoryPanel(name, photoPath, stories);
    Button nextButton = new Button();
        Button previousButton = new Button();
        
       nextButton.setOnAction(e -> {
    /*       j = j + 1;
          if (j == stories.size()) {
              j = 0;
          } */
          j = (j + 1) % stories.size();
          setStoryPanel(name, photoPath, stories);
        //  imageView.setImage(images[j]);

      });
      previousButton.setOnAction(e -> {
     /*      j = j - 1;
          if (j == 0 || j > stories.size() + 1 || j == -1) {
              j = stories.size() - 1;
          } */
          if (j == 0) {
            j = stories.size() - 1;
        } else {
            j = j - 1;
        }
          setStoryPanel(name, photoPath, stories);
         // imageView.setImage(images[j]);

      });
       // HBox buttonContainer = new HBox(100);
      //  buttonContainer.setAlignment(Pos.CENTER);
      //  buttonContainer.getChildren().addAll(previousButton,nextButton);
        GridPane storySlider = new GridPane();
        storySlider.add(storyPanel, 1,1);
        storySlider.add(previousButton, 0, 1);
        storySlider.add(nextButton, 2, 1);
        storySlider.add(closeButton, 0, 0);
       // storyFrame.getChildren().addAll(closeButton,profileIdentifier,textPanel,buttonContainer,imageScroll);
      storySlider.getStylesheets().add(StorySlider.class.getResource("/css/Story.css").toExternalForm());
     // profileIdentifier.getStyleClass().add("profile-identifier");
       // userName.getStyleClass().add("user-name");
        previousButton.getStyleClass().add("arrow-button");
        previousButton.getStyleClass().add("prev-arrow");
        nextButton.getStyleClass().add("arrow-button");
        nextButton.getStyleClass().add("next-arrow");
        Scene scene = new Scene(storySlider, 600, 800);
        backdrop.setScene(scene);
       // window.getScene().getRoot().setEffect(blur);
        backdrop.showAndWait();
    return storySlider;
    }
public static void setStoryPanel(String name,String photoPath,List<Story>stories){

  if(storyPanel!=null){
    storyPanel.getChildren().clear();

  }

    VBox newStoryPanel = StoryFrame.createStory(
        name, photoPath, 
        stories.get(j).getContentDetails().getText(), 
        stories.get(j).getContentDetails().getPhoto());
    
    storyPanel.getChildren().add(newStoryPanel);
}
}

//loop through list of stories and display the story frame