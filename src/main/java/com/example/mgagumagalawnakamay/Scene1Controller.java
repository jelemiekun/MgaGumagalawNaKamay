package com.example.mgagumagalawnakamay;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Random;

public class Scene1Controller {
    private int ilangKamay = 100;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView imageView;
    @FXML
    Image image = new Image(getClass().getResourceAsStream("/com/example/mgagumagalawnakamay/Media/kamay.png"));
    private Random random = new Random();
    public void initializeKamay() {
        for (int i = 0; i < ilangKamay; i++) {
            double randomX = random.nextDouble(rootPane.getPrefWidth() - 60);
            double randomY = random.nextDouble(rootPane.getPrefHeight() - 70);

            imageView = new ImageView();
            imageView.setImage(image);
            imageView.setLayoutX(randomX);
            imageView.setLayoutY(randomY);
            imageView.setFitWidth(60);
            imageView.setFitHeight(70);
            rootPane.getChildren().add(imageView);
            moveKamay(imageView);
        }
    }

    private void moveKamay(ImageView imageView) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.25), event -> {
                    imageView.setLayoutY(imageView.getLayoutY() + 2);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

/*
if (imageView.getLayoutX() == (rootPane.getPrefWidth() - 60) || imageView.getLayoutY() == (rootPane.getPrefHeight() - 70)) {

        }
 */