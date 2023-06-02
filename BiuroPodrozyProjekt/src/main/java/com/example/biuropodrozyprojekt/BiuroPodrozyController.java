package com.example.biuropodrozyprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BiuroPodrozyController {

    @FXML
    private Label loginText;

    @FXML
    private ImageView image;

    @FXML
    private Button Register;


   @FXML
    public void onRegisterButtonClick(ActionEvent e) {
        loginText.setText("Rejestracja");
    }


    //chlop na tutorialu dodawal niby to w Controllerze
    public void startRejestracja() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(BiuroPodrozyApplication.class.getResource("Rejestracja.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Travel Agency Management System");
        stage.getIcons().add(new Image(("palma.png")));
        stage.setScene(scene);
        stage.show();
    }


    }

