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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController extends PulpitController implements Initializable{
private ConnectionManager connectionManager = new ConnectionManager();
private Scene scene;
private Stage stage;



@FXML
private Label helloLabel;
protected String login;

protected  String[] parts;
protected String password;
    public void printLogin() {
        System.out.println("Login w profilu: " + getLogin());
        login = getLogin();
    }

    public void printParts()
    {
        parts = getParts();
    }

    public void onDashboardButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pulpit_cli.fxml"));
        Parent root = loader.load();
        PulpitController pulpitController = loader.getController();
         pulpitController.setLogin(login);
         pulpitController.initialize(null, null); // Manually call the initialize method
        //userProfileController.setLogin(login);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        connectionManager = new ConnectionManager();

        printLogin();
        helloLabel.setText("Witaj " + login);


    }
}
