package com.example.biuropodrozyprojekt;

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

public class PulpitController implements Initializable {
    private ConnectionManager connectionManager;
    @FXML
    private Label helloLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String login;






    public void switchToLoginScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BiuroPodróży.fxml"));
        Parent root = loader.load();
        BiuroPodrozyController biuroPodrozyController = loader.getController();
       // pulpitController.setLogin(login);

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    public void logout(ActionEvent e) throws IOException {


            connectionManager.logout();
            connectionManager.disconnect();
        switchToLoginScene(e);
        }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
       //setConnectionManager(connectionManager);
        connectionManager = new ConnectionManager();

        //login=connectionManager.getData();
       // System.out.println("Dane z managera"+login);
       // helloLabel.setText("Witaj "+login);
    }
}
