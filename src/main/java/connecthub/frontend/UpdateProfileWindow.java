package connecthub.frontend;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import connecthub.backend.UpdateProfile;
import connecthub.backend.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateProfileWindow {
    public static void updateProfile(User user, UpdateProfile updateProfile, ProfileView profileView){
        Stage window = new Stage();
        VBox layout = new VBox(20);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        Button changeProfilePhoto = new Button("Change Profile Photo");
        Button changeCoverPhoto = new Button("Change Cover Photo");
        Button changePassowrd = new Button("Change Password");
        Button changeBio = new Button("Change Bio");
        changeProfilePhoto.setOnAction(e-> {
                    Platform.runLater(() -> {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File("."));
                        fileChooser.setDialogTitle("Choose Profile Photo");
                        fileChooser.setAcceptAllFileFilterUsed(false);
                        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
                        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            if (file != null) {
                                updateProfile.updateProfilePhoto(file.getPath());
                              //  profileView.setProfileView(new Image(file.toURI().toString()));
                                profileView.setProfilePhoto(new Image(new File(file.getPath()).toURI().toString()));
                            }
                        }
                    });
                });
        changeCoverPhoto.setOnAction(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setDialogTitle("Choose Profile Photo");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                    if(file!=null){
                        updateProfile.updateCoverPhoto(file.getAbsolutePath());
                        //profile view set cover phoot
                        }
        }});
        changePassowrd.setOnAction(e->{
            ChangePasswordWindow.changePassword(user,updateProfile);
        });

        changeBio.setOnAction(e->{
            ChangeBioWindow.changeBio(user, updateProfile, user.getProfile().getBio());
            profileView.setBio(user.getProfile().getBio());
        });

        layout.getChildren().addAll(changeProfilePhoto,changeCoverPhoto,changePassowrd,changeBio);
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.setTitle("Update Profile");
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
