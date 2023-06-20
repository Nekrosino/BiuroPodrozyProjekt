package com.example.biuropodrozyprojekt;


import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class BasketController extends PulpitController implements Initializable{
    private ConnectionManager connectionManager;
    private Scene scene;
    private Stage stage;


    private String login;
    Text opis = new Text( "Wycieczka do Stegny to niezapomniane doświadczenie, które łączy piękno natury i bogatą historię tego malowniczego miejsca.\\n" +
            "Stegna, położona nad Bałtykiem, przyciąga turystów swoimi piaszczystymi plażami, czystym powietrzem i urokliwym krajobrazem.\\n" +
            "\\n");
    @FXML
    TextFlow descriptionFlow;


    public void ReturntoPulpit(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pulpit_cli.fxml"));
        Parent root = loader.load();
        PulpitController pulpitController = loader.getController();
        pulpitController.setLogin(login);
        pulpitController.initialize(null, null); // Manually call the initialize method
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    public void printLogin() {
        System.out.println("Login w profilu: " + getLogin());
        login = getLogin();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connectionManager = new ConnectionManager();
        printLogin();
        descriptionFlow.getChildren().clear();
            descriptionFlow.getChildren().add(opis);

    }



}
