package com.example.mgagumagalawnakamay;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Random;

public class Scene1Controller {
    private int ilangKamay = 250;

    private int directions = 8;
    private Timeline currentTimeline;
    private int kamayCounter = 0;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label kamayCount;

    @FXML
    private ImageView imageView;
    @FXML
    Image image = new Image(getClass().getResourceAsStream("/com/example/mgagumagalawnakamay/Media/kamay.png"));
    private Random random = new Random();

    public void initializeKamay() {
        for (int i = 0; i < ilangKamay; i++) {
            makeKamay();
        }
    }
    private void makeKamay() {
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
        kamayCounter++;
        displayKamayCount(kamayCounter);
    }

    private void displayKamayCount(int kamayCounters) {
        kamayCount.setText(String.valueOf(kamayCounters));
    }

    private void moveKamay(ImageView imageView) {
        int direction = random.nextInt(directions) + 1;

        // Use the class-level variable to store the current timeline
        currentTimeline = createTimeline(imageView, direction);

        if (currentTimeline != null) {
            currentTimeline.setCycleCount(Timeline.INDEFINITE);
            currentTimeline.play();
        }
    }

    private Timeline createTimeline(ImageView imageView, int direction) {
        double[] delta = getDelta(direction);

        double deltaY = delta[0];
        double deltaX = delta[1];

        // Add a flag to check if the ImageView has been removed
        boolean[] removed = {false};

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> {
                    imageView.setLayoutY(imageView.getLayoutY() + deltaY);
                    imageView.setLayoutX(imageView.getLayoutX() + deltaX);
                    if (checkBounds(imageView)) {
                        if (!removed[0]) {
                            rootPane.getChildren().remove(imageView);
                            kamayCounter--;


                            displayKamayCount(kamayCounter);
                            if (currentTimeline != null) {
                                makeKamay();
                            }
                            removed[0] = true;
                        }
                    } else {
                        // Reset the removed flag when the ImageView is within bounds
                        removed[0] = false;
                    }
                })
        );

        return timeline;
    }


    private double[] getDelta(int direction) {
        double deltaY = 0, deltaX = 0;

        // Set deltaY and deltaX based on the direction
        switch (direction) {
            case 1: // UP
                deltaY = -1.3;
                deltaX = 0;
                break;
            case 2: // UP-RIGHT
                deltaY = -1.3;
                deltaX = 1.3;
                break;
            case 3: // RIGHT
                deltaY = 0;
                deltaX = 1.3;
                break;
            case 4: // DOWN-RIGHT
                deltaY = 1.3;
                deltaX = 1.3;
                break;
            case 5: // DOWN
                deltaY = 1.3;
                deltaX = 0;
                break;
            case 6: // DOWN-LEFT
                deltaY = 1.3;
                deltaX = -1.3;
                break;
            case 7: // LEFT
                deltaY = 0;
                deltaX = -1.3;
                break;
            case 8: // UP-LEFT
                deltaY = -1.3;
                deltaX = -1.3;
                break;
        }

        return new double[]{deltaY, deltaX};
    }

    private boolean checkBounds(ImageView imageView) {
        double imageWidth = imageView.getBoundsInParent().getWidth();
        double imageHeight = imageView.getBoundsInParent().getHeight();

        double layoutX = imageView.getLayoutX();
        double layoutY = imageView.getLayoutY();

        return (layoutX + imageWidth >= rootPane.getPrefWidth() ||
                layoutY + imageHeight >= rootPane.getPrefHeight() ||
                layoutX <= 0 ||
                layoutY <= 0);
    }


}