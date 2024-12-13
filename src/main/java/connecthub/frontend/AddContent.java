package connecthub.frontend;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import connecthub.backend.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddContent { 
        private static File file;
        private static String imagePath;
        public static void addContent(User user, ContentManagement contentManagement){
        Stage window = new Stage();
        Label captionLabel = new Label("Caption");
        Label imageLabel = new Label("Image");
        TextField caption = new TextField();
        Button openButton = new Button("Open Image");
        Button saveButton = new Button("Save");
        openButton.setOnAction(e->{
           JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setDialogTitle("Choose Profile Photo");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                if (file != null) {
            imagePath = file.getPath();
                }
                else imagePath=null;
        }});
        saveButton.setOnAction(e-> {
            if((imagePath == null || imagePath.isEmpty())&& contentManagement instanceof StoryManagement){
                    AlertBox.displayWarning("You have to attach an image");
                    return;
            }
            if(caption.getText().isEmpty() && imagePath==null && contentManagement instanceof PostManagement){
                AlertBox.displayWarning("Caption cannot be empty");
                return;
            }
            contentManagement.createContent(user.getUserId(), caption.getText(), imagePath);
            imagePath=null;
            window.close();
        });
        GridPane layout = new GridPane();
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.add(captionLabel, 0, 0);
        layout.add(caption, 1, 0);
        layout.add(imageLabel, 0, 1);
        layout.add(openButton, 1, 1);
        layout.add(saveButton, 1, 2);
        layout.setPadding(new Insets(20));
        
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("Add Content");
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
    public static void addContent(String id,Group group){
        Stage window = new Stage();
        Label captionLabel = new Label("Caption");
        Label imageLabel = new Label("Image");
        TextField caption = new TextField();
        Button openButton = new Button("Open Image");
        Button saveButton = new Button("Save");
        openButton.setOnAction(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setDialogTitle("Choose Profile Photo");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                if (file != null) {
                    imagePath = file.getPath();
                }
                else imagePath=null;
            }});
        saveButton.setOnAction(e-> {
            if(caption.getText().isEmpty() && imagePath==null){
                AlertBox.displayWarning("Caption cannot be empty");
                return;
            }
            Post post = new Post(id, caption.getText(),imagePath);
            group.addPost(post);
            Newsfeed.groupManager.saveToFile();
            List<String> memberIds = group.getMembers();
            for(String memberId : memberIds){
                if(memberId.equals(id))
                    continue;
                else {
                    Newsfeed.notficationSystem.addNotification(NotificationFactory.createNotification(memberId,group.getGroupId(), "group post"));
                }
            }
            imagePath=null;
            window.close();
        });
        GridPane layout = new GridPane();
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.add(captionLabel, 0, 0);
        layout.add(caption, 1, 0);
        layout.add(imageLabel, 0, 1);
        layout.add(openButton, 1, 1);
        layout.add(saveButton, 1, 2);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("Add Content");
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
