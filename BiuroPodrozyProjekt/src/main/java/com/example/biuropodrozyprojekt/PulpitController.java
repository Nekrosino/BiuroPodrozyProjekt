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

public class PulpitController extends BiuroPodrozyController implements Initializable {
    private ConnectionManager connectionManager;
    @FXML
    private Label helloLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    protected String login;
    public String password;

    public String[] parts;

    public void printLogin() {
        System.out.println("Login w pulpicie: " + getLogin());
        login = getLogin();
    }

    public void printPassword(){
        password = getPassword();
    }

    public void setLogin(String[] parts) {
        this.parts = parts;
    }

    public String[] getParts()
    {
        return parts;
    }


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

    public void onProfileButtonClick(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
        Parent root = loader.load();
        UserProfileController userProfileController = loader.getController();
        userProfileController.setLogin(login);
        userProfileController.setPassword(password);
        System.out.println("Login wyslany"+login);
        System.out.println("Haslo wyslane"+password);
        connectionManager.connectToServer(login,password);
        String parts = connectionManager.getProfileData(login,password);
        String[] splittedparts = parts.split(" ");
//        String username = splittedparts[1];
//        String password = splittedparts[2];
//        String saldo = splittedparts[3];
        //System.out.println("Otrzymany wynik "+username+" "+password+" "+saldo);
        //userProfileController.setParts(parts);
        userProfileController.initialize(null, null); // Manually call the initialize method
        // pulpitController.setLogin(login);

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    public void logout(ActionEvent e) throws IOException {


            connectionManager.logout();
            connectionManager.disconnect();
            printLogin();
            switchToLoginScene(e);
        }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
       //setConnectionManager(connectionManager);
        connectionManager = new ConnectionManager();
        printLogin();
        printPassword();
        helloLabel.setText("Witaj " + login);
       // helloLabel.setText(login);

        //login=connectionManager.getData();
       // System.out.println("Dane z managera"+login);
       // helloLabel.setText("Witaj "+login);
    }
}
