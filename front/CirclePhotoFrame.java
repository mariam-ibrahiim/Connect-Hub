package com.mycompany.mavenproject2.frontend;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;

public class CirclePhotoFrame {
    public static void createCircleFrame(ImageView imageView){
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100); 
        Circle circle= new Circle();
        circle.radiusProperty().bind(imageView.fitWidthProperty().divide(2));
        circle.centerXProperty().bind(imageView.fitWidthProperty().divide(2));
        circle.centerYProperty().bind(imageView.fitHeightProperty().divide(2));
        imageView.setClip(circle); 
    }
}
