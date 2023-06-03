package com.example.biuropodrozyprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BiuroPodrozyApplication extends Application {

    public static final String usernameAdmin = "admin";
    public static final String passwordAdmin = "admin";
    public static final String usernameClient = "client";
    public static final String passwordClient = "client";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
        Scene loginScene = new Scene( root);
        stage.setTitle("Travel Agency Management System");
        stage.getIcons().add(new Image(("palma.png")));
        String css=this.getClass().getResource("style.css").toExternalForm();
        loginScene.getStylesheets().add(css);
        stage.setScene(loginScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}