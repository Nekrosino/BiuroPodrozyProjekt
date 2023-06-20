package com.example.biuropodrozyprojekt;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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



    Text opis = new Text( "Wycieczka do Stegny to niezapomniane doświadczenie, które łączy piękno natury i bogatą historię tego malowniczego miejsca.\n" +
            "Stegna, położona nad Bałtykiem, przyciąga turystów swoimi piaszczystymi plażami, czystym powietrzem i urokliwym krajobrazem.\n" +
            "\n");

    Text opis2 = new Text("Marzysz o podróży pełnej niezapomnianych wrażeń? Wyobraź sobie słoneczne promienie, aromatyczne kawy, romantyczne zaułki i smakujące wykwintnie włoskie potrawy...\n" + " \n" +
            " To wszystko czeka na Ciebie podczas naszej niesamowitej wycieczki do Włoch!" + "Rzym, wieczne miasto pełne historii i majestatycznych ruin. Spacerując po starożytnym Koloseum czy wędrując po uliczkach Wiecznego Miasta, poczujesz magiczną atmosferę przeszłości.\n" );

    Text opis3 = new Text("Pragniesz odkryć rajskie plaże, kryształowo czyste wody i zapierające dech w piersiach widoki? Mamy dla Ciebie niezwykłą propozycję - wycieczkę do pięknej Chorwacji!\n" +
            "\n" +
            "Zapomnij na chwilę o codziennych troskach i pozwól nam zabrać Cię w podróż do krainy niebiańskiej urody. Zaczniemy naszą przygodę od malowniczej Dubrownika, miasta znanego ze swych imponujących murów obronnych. Spacerując po wąskich uliczkach Starego Miasta, poczujesz mistyczną atmosferę tego miejsca.\n");

     Text opis4 = new Text("Marzysz o romantycznej podróży do miasta miłości? Mamy dla Ciebie idealną propozycję - wycieczkę do magicznego Paryża!\n" +
             "\n" +
             "Zapraszamy Cię do odkrywania uroków tego niezwykłego miejsca, gdzie historia, sztuka i romantyzm splatają się w jedno. Spacerując po pięknych bulwarach Sekwany, wędrując wśród wież Eiffla i malowniczych uliczek Montmartre, poczujesz niepowtarzalny klimat tego miasta.\n");

     Text opis5 = new Text("Zapraszamy Cię do zapoznania się z niezwykłym urokiem Sztokholmu - stolicy Skandynawii, która skradnie Twoje serce. Nasza wycieczka do Sztokholmu to podróż pełna fascynujących kontrastów i niezapomnianych doświadczeń.\n" +
             "\n" +
             "Rozpocznij swoją przygodę od eksploracji Starego Miasta, Gamla Stan, gdzie wąskie brukowane uliczki prowadzą do kolorowych domów i urokliwych sklepików. Poczuj magię średniowiecznej historii i zanurz się w niepowtarzalnej atmosferze tego miejsca.");

     Text opis6 = new Text("Pragniesz odkryć nieskażoną naturę, majestatyczne krajobrazy i zapierające dech w piersiach widoki? Mamy dla Ciebie niezwykłą propozycję - wycieczkę do magicznego Husaviku na Islandii!\n" +
             "\n" +
             "Husavik, malownicze miasteczko położone na północy Islandii, jest prawdziwym rajem dla miłośników przyrody. Rozpocznij swoją przygodę od niezapomnianego rejsu na poszukiwanie wielorybów. Będziesz miał okazję zobaczyć te olbrzymie ssaki w ich naturalnym środowisku i doświadczyć niezwykłych emocji.");


   // Image image1 = new Image(getClass().getResource("/Images/plaza.jpg").toExternalForm());

    //String image1 = "@../../../plaza.jpg";
    //Image newImage;
    //Image image1 = new Image("/src/main/resources/obraz");

    //Image image3 = new Image("src/main/resources/plaza.jpg");
    //Image image4 = new Image("src/main/resources/plaza.jpg");
   // Image image5 = new Image("src/main/resources/plaza.jpg");
    //Image image6 = new Image("src/main/resources/plaza.jpg");
    String imagePath1 = getClass().getResource("/plaza.jpg").toExternalForm();
    String imagePath2 = getClass().getResource("/1włochyRzym.jpg").toExternalForm();
    String imagePath3 = getClass().getResource("/2chorwacjaDubrovnik.jpg").toExternalForm();
    String imagePath4 = getClass().getResource("/3francjaParyz.jpg").toExternalForm();
    String imagePath5 = getClass().getResource("/4szwecjaSztokholm.jpg").toExternalForm();
    String imagePath6 = getClass().getResource("/5husavikIslandia.jpg").toExternalForm();
    @FXML
    TextFlow descriptionFlow;

    @FXML
    ImageView imageView;


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
        connectionManager.paymentWycieczka(cenaWycieczka,login);
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
        idWycieczka=getIDwycieczka();
        descriptionFlow.getChildren().clear();

        if(idWycieczka == "1")
        {
            Image image = new Image(imagePath1);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis);
        }
        else if(idWycieczka == "2")
        {
            Image image = new Image(imagePath2);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis2);
        }

        else if(idWycieczka == "3")
        {
            Image image = new Image(imagePath3);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis3);
        }
        else if(idWycieczka == "4")
        {
            Image image = new Image(imagePath4);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis4);
        }
        else if(idWycieczka == "5")
        {
            Image image = new Image(imagePath5);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis5);
        }
        else if(idWycieczka == "6")
        {
            Image image = new Image(imagePath6);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis6);
        }



        nazwaLabel.setText(nazwaWycieczki);
        nazwaLabel2.setText(nazwaWycieczki);
        rozpoczecieLabel.setText(dataRozpoczecia);
        zakonczenieLabel.setText(dataZakonczenia);
        cenaLabel.setText(cenaWycieczka);


    }



}
