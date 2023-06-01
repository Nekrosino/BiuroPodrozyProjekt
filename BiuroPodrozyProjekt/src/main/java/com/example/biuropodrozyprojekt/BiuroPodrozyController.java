package com.example.biuropodrozyprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BiuroPodrozyController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label loginText;

    @FXML
    public void onHelloButtonClick() {
        welcomeText.setText("Tu będzie kiedyś elegancki projekt");
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent e) {
        loginText.setText("Rejestracja");
    }

}