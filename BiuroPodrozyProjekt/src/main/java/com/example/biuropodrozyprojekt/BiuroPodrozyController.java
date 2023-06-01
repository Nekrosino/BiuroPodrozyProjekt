package com.example.biuropodrozyprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BiuroPodrozyController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label loginText;

    @FXML
    private ImageView image;

    @FXML
    public void onHelloButtonClick() {
        welcomeText.setText("Tu będzie kiedyś elegancki projekt");
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent e) {
        loginText.setText("Rejestracja");
    }




}