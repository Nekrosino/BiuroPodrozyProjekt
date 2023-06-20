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
import java.time.LocalDate;


public class BasketController extends PulpitController implements Initializable{
    private ConnectionManager connectionManager;
    private Scene scene;
    private Stage stage;

    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.toString();


    private String login;
    private String dataRozpoczecia;
    private String dataZakonczenia;
    private String nazwaWycieczki;
    private  String cenaWycieczka;

    private String[] dane;
    private String userID;
    private String userSaldoText;
    private  String idWycieczka;
    private String userNameText;
   private String  userSurnameText;

    private String parts;
    @FXML
    private Label zakonczenieLabel;
    @FXML
    private Label rozpoczecieLabel;
    @FXML
    private Label nazwaLabel;
    @FXML
    private Label nazwaLabel2;
    @FXML
    private Label cenaLabel;

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



    public void purchaseWycieczka(ActionEvent e) throws IOException{
        parts = connectionManager.getProfileData(login);
        splitdata();
        idWycieczka=getIDwycieczka();
        connectionManager.buyWycieczka(userID,idWycieczka,formattedDate);
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

    public void splitdata() {
        if (parts != null) {
            dane = parts.split(" ");
            userNameText = dane[1];
            userSurnameText = dane[2];
            userSaldoText = dane[3];
            userID = dane[4];
            System.out.println("Otrzymane saldo " + dane[3]);
            System.out.println("Otrzymane id " + dane[4]);

        }
    }

//    public void printLogin() {
//        System.out.println("Login w profilu: " + getLogin());
//        login = getLogin();
//    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionManager = new ConnectionManager();
        login = getLogin();
        nazwaWycieczki=getNazwaWycieczki();
        dataRozpoczecia=getDataRozpoczecia();
        dataZakonczenia=getDataZakonczenia();
        cenaWycieczka=getCenaWycieczka();
        descriptionFlow.getChildren().clear();
        descriptionFlow.getChildren().add(opis);
        nazwaLabel.setText(nazwaWycieczki);
        nazwaLabel2.setText(nazwaWycieczki);
        rozpoczecieLabel.setText(dataRozpoczecia);
        zakonczenieLabel.setText(dataZakonczenia);
        cenaLabel.setText(cenaWycieczka);


    }



}
