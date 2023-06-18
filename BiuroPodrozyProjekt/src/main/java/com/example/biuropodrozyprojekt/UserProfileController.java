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
//private String[] dane;



@FXML
private Label helloLabel;

@FXML
private Label nameLabel;
@FXML
private Label surnameLabel;
@FXML
private Label saldoLabel;
protected String login;

protected  String parts;
private String[] dane;

String userSaldoText;
String userNameText;
String userSurnameText;
protected String password;
    public void printLogin() {
        System.out.println("Login w profilu: " + getLogin());
        login = getLogin();
    }

    public void printParts()
    {
        parts = getParts();
        //dane = parts.split(" ");
         //userNameText = dane[1];
        // userSurnameText = dane[2];
        // userSaldoText = dane[3];
    }

    public void splitdata()
    {
        if (parts != null) {
            dane = parts.split(" ");
                        userNameText = dane[1];
                        userSurnameText = dane[2];
                        userSaldoText = dane[3];
                     System.out.println("Otrzymane imie "+dane[1]);
                    System.out.println("Otrzymane nazwisko "+dane[2]);
                    System.out.println("Otrzymane saldo "+dane[3]);
        }

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
        printParts();
        splitdata();
        System.out.println("DOSTAŁEM "+parts);
        nameLabel.setText(userNameText);
        surnameLabel.setText(userSurnameText);
        saldoLabel.setText(userSaldoText);

        helloLabel.setText("Hello " + login);


    }
}
