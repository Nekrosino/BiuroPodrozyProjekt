package com.example.biuropodrozyprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BiuroPodrozyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(BiuroPodrozyApplication.class.getResource("BiuroPodróży.fxml"));
        Scene loginScene = new Scene(fxmlLoaderLogin.load(), 800, 600);
        stage.setTitle("Travel Agency Management System");
        stage.getIcons().add(new Image(("palma.png")));
        stage.setScene(loginScene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}