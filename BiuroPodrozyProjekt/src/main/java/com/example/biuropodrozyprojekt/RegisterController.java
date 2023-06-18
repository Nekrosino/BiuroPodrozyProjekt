package com.example.biuropodrozyprojekt;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private ConnectionManager connectionManager = new ConnectionManager();
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField LoginField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField AdresField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField SurnameField;
    @FXML
    private TextField RepeatPasswordField;
    @FXML
    private TextField PhoneField;

    @FXML
    private TextField EmailField;

    private String Login;
    private String Password;
    private String RepeatPassword;
    private String Name;
    private String Surname;
    private String Phone;
    private String Adres;
    private String Email;
    //private String[] dane;

    public void switchToLoginScene(ActionEvent e) throws IOException {
//
        fillForm();
        if(Password.equals(RepeatPassword))
        {
            System.out.println("Pomyślnie zatwierdzono");
            System.out.println("Wypelnione dane: " + Name + " " + Surname + " " + Adres + " " + Phone + " " + Login + " " + Password + " " + Email);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BiuroPodróży.fxml"));
        Parent root = loader.load();
        BiuroPodrozyController biuroPodrozyController = loader.getController();
        connectionManager.registerUser(Name,Surname,Adres,Phone,Email,Login,Password,"99999.9");
        // pulpitController.setLogin(login);

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
        }
        else
        {
            System.out.println("Hasla sie roznia!");
        }
    }

    public void fillForm()
    {
        Name = NameField.getText();
        Surname = SurnameField.getText();
        Login = LoginField.getText();
        Password = PasswordField.getText();
        RepeatPassword = RepeatPasswordField.getText();
        Phone = PhoneField.getText();
        Adres = AdresField.getText();
        Email = EmailField.getText();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
