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
     private ConnectionManager connectionManager;
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
     public String login;
     private String password;

     public BiuroPodrozyController() {
     }
     public String getLogin() {
         return login;
     }

     public void setLogin(String login) {
         this.login = login;
     }

     public String getPassword(){
         return password;
     }

     public void setPassword(String password){
         this.password=password;
     }

     private Socket clientSockett;
     private PrintWriter out;
     private BufferedReader in;
     private String sessionId;


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
                setLogin(LoginInput.getText());
                System.out.println("Powinnobyc:"+login);
                setPassword(PasswordInput.getText());
         if(LoginInput.getText().isEmpty())
         {
           login = "shdjhsdkj";
         }

         if(PasswordInput.getText().isEmpty())
         {
             password = "shkjhsadjhdsajkds";
         }

         String sessionId = connectionManager.connectToServer(login,password);

         if (sessionId != null) {
             Message = login;
             System.out.println("Dane wyslane do managera"+Message);
             switchToLoggedScene(e);

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

public void test(MouseEvent e) throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = stage.getScene();
    scene.setRoot(root);
    scene.getStylesheets().add(css);
    stage.show();

}

    public void switchToRegisterScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rejestracja.fxml"));
        Parent root = loader.load();
        RegisterController registerController = loader.getController();
        //pulpitController.setLogin(login);

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }


public void switchToLoggedScene(ActionEvent e) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Pulpit_cli.fxml"));
    Parent root = loader.load();
    PulpitController pulpitController = loader.getController();
    pulpitController.setLogin(login);  // Ustawienie wartości pola login
    pulpitController.setPassword(password);
    pulpitController.initialize(null, null); // Manually call the initialize method
   // pulpitController.setConnectionManager(connectionManager);


    //pulpitController.setLogin(login);
    //pulpitController.setConnectionManager(connectionManager);

    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = stage.getScene();
    scene.setRoot(root);
    stage.show();


}



    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connectToServer();
        connectionManager = new ConnectionManager();





    }

}

