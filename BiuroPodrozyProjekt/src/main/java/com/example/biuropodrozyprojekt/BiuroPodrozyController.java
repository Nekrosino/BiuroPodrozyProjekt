package com.example.biuropodrozyprojekt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

import static com.example.biuropodrozyprojekt.BiuroPodrozyApplication.*;


public class BiuroPodrozyController implements Initializable
 {

     private StringProperty labelTextProperty = new SimpleStringProperty();


     private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label loginText;

    @FXML
    private ImageView image;

    @FXML
    private Button Register;

    @FXML
    private Button Login;
    @FXML
    private MenuItem Logout;

    @FXML
    private TextField LoginInput;

    @FXML
    private PasswordField PasswordInput;

    @FXML
    private Label errorMessageLabel;
    @FXML
    private String errorMessage = " ";

     @FXML
     private MenuItem logoutMenuItem;
@FXML
     private MenuButton menuButton;

     @FXML
     private String HelloMessage;


     @FXML
     private Button logoutButton;

     @FXML
     private Label Saldo;

     String test = "test";
     String queryLogin;
     private String login;
     private String password;

     public BiuroPodrozyController() {
     }

     private Socket clientSockett;
     private PrintWriter out;
     private BufferedReader in;
     private String sessionId;
     @FXML
     public Label HelloLabel;

     public String Message;


    @FXML
     public StringProperty labelTextProperty() {
         return labelTextProperty;
     }

     public void setLabelText(String labelText) {
         this.labelTextProperty.set(labelText);
     }



     public void connectToServer(ActionEvent e) throws IOException {

             // Tworzenie połączenia z serwerem
             clientSockett = new Socket("localhost", 1234);
             out = new PrintWriter(clientSockett.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
             // Wysyłanie żądania logowania
                login=LoginInput.getText();
                password=PasswordInput.getText();
             out.println("LOGIN "+login+" "+password);
             System.out.println("Wysłane dane:"+login+" "+password);



             String response = in.readLine();
             // Jeżeli logowanie powiodło się, otrzymujemy identyfikator sesji
             if(response.startsWith("SESSION_ID"))
             {
                 sessionId = response.split(" ")[1];
                 System.out.println("Zalogowano pomyślnie. SESSION ID: "+sessionId);
                 setLabelText(login);
                 switchToLoggedScene(e);

             }
             else{
                 System.out.println("Błąd logowania");
             }

         }


     public void logout(ActionEvent e) throws IOException {
         // Sprawdzenie, czy klient jest zalogowany
         if (sessionId != null) {
             // Wysłanie żądania wylogowania
             out.println("LOGOUT " + sessionId);
             String response = in.readLine();

             if (response.equals("LOGOUT_SUCCESS")) {
                 // Wylogowanie zakończone pomyślnie
                 sessionId = null;

                 System.out.println("Wylogowano pomyślnie");
             } else {
                 System.out.println("Błąd wylogowania");
             }
         } else {
             System.out.println("Klient nie jest zalogowany");
             switchToLoginScene(e);
             disconnect();
         }
     }

     public void disconnect() throws IOException {
         // Zamknięcie połączenia
         if (clientSockett != null) {
             clientSockett.close();
         }
     }


     public boolean isFieldFilled()
    {
        boolean isFilled = true;
        if(LoginInput.getText().isEmpty()){
            isFilled = false;
            errorMessage = "Username is Empty";
        }

        if(PasswordInput.getText().isEmpty()){
            isFilled = false;
            if(errorMessage.isEmpty()){
                errorMessage = "Password is empty";
            }
            else {
                errorMessage += "\nPassword is empty!";
            }
        }
        errorMessageLabel.setText(errorMessage);
        return isFilled;
    }

     public boolean isValid() {
         boolean isValid = true;
         String username = LoginInput.getText();
         String password = PasswordInput.getText();
         errorMessage = "";

         if (username.equals(BiuroPodrozyApplication.usernameAdmin)) {
             // Sprawdzenie poprawności danych dla administratora
             if (!password.equals(BiuroPodrozyApplication.passwordAdmin)) {
                 isValid = false;
                 errorMessage = "Invalid Password!";
             }

         } else if (username.equals(BiuroPodrozyApplication.usernameClient)) {
             // Sprawdzenie poprawności danych dla użytkownika
             if (!password.equals(BiuroPodrozyApplication.passwordClient)) {
                 isValid = false;
                 errorMessage = "Invalid Password!";
             }
         } else {
             // Nieznany użytkownik
             isValid = false;
             errorMessage = "Invalid Username!";
         }

         errorMessageLabel.setText(errorMessage);
         return isValid;
     }

    String css=this.getClass().getResource("style.css").toExternalForm();
//
//        public void switchToLoginScene(ActionEvent event) throws IOException {
//       Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
//       // stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //do przycisku
//            Stage stage = (Stage) logoutMenuItem.getParentPopup().getOwnerWindow(); //do menuItem
//        scene = new Scene(root);
//        scene.getStylesheets().add(css);
//        stage.setScene(scene);
//        stage.show();
//        }

public void switchToLoginScene(ActionEvent e) throws IOException {
    //System.out.println("Zmienna login:"+login);

    Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = stage.getScene();
    scene.setRoot(root);
    scene.getStylesheets().add(css);
    stage.show();
}

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Rejestracja.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }


public void switchToLoggedScene(ActionEvent e) throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("Pulpit_cli.fxml"));

             stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
             scene = stage.getScene();
             scene.setRoot(root);
             scene.getStylesheets().add(css);
             stage.show();
             System.out.println(login);


}
//Zmieniona funkcja i działa chyba
//     public void switchToLoggedScene(ActionEvent event) throws IOException {
//
//          login = LoginInput.getText();
//          password = PasswordInput.getText();
//         connectToServer();
//         BiuroPodrozyApplication application = new BiuroPodrozyApplication();
//         application.receiveLogin(login);
//
//       // queryLogin = login;
//        //out.println(login);
//
//        //queryLogin = password;
//       // out.println(password);
//        // String query1 = in.readLine();
//        // String query2 = in.readLine();
//        // usernameClient = query1;
//       //  passwordClient=query2;
//
//        // System.out.println(query1);
//         HelloLabel.setText("Hello, " + usernameClient);
//
//
//
//         if(isFieldFilled()&&isValid()&&login.equals(BiuroPodrozyApplication.usernameAdmin)) {
//             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_adm.fxml"));
//
//             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//             scene = stage.getScene();
//             scene.setRoot(root);
//             scene.getStylesheets().add(css);
//             stage.show();
//         }
//         else if(isFieldFilled()&&isValid()&&login.equals(BiuroPodrozyApplication.usernameClient)) {
//             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_cli.fxml"));
//
//             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//             scene = stage.getScene();
//             scene.setRoot(root);
//             scene.getStylesheets().add(css);
//             stage.show();
//         }
//     }

     public void DashboardButtonOnClick(ActionEvent event) throws IOException {



             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_cli.fxml"));

             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = stage.getScene();
             scene.setRoot(root);
             scene.getStylesheets().add(css);
             stage.show();

     }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connectToServer();





    }

}

