package com.example.mgagumagalawnakamay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Scene1Controller scene1Controller = fxmlLoader.getController();
        scene1Controller.initializeKamay();

        stage.setTitle("Mga kamay");
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}