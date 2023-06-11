package com.example.biuropodrozyprojekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BiuroPodrozyController implements Initializable
 {

     String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl";
     String username = "your_username";
     String password = "your_password";


     Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
     // Tutaj możesz wykonywać operacje na bazie danych



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
    private TextField LoginInput;

    @FXML
    private PasswordField PasswordInput;

    @FXML
    private Label errorMessageLabel;

    private String errorMessage = " ";

     public BiuroPodrozyController() throws SQLException {
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

        public void switchToScene1(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        }


    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Rejestracja.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }



//Zmieniona funkcja i działa chyba
     public void switchToScene3(ActionEvent event) throws IOException {
         String username = LoginInput.getText();

         if(isFieldFilled()&&isValid()&&username.equals(BiuroPodrozyApplication.usernameAdmin)) {
             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_adm.fxml"));

             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = stage.getScene();
             scene.setRoot(root);
             scene.getStylesheets().add(css);
             stage.show();
         }
         else if(isFieldFilled()&&isValid()&&username.equals(BiuroPodrozyApplication.usernameClient)) {
             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_cli.fxml"));

             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = stage.getScene();
             scene.setRoot(root);
             scene.getStylesheets().add(css);
             stage.show();
         }
     }

     public void switchToScene4(ActionEvent event) throws IOException {
         String username = LoginInput.getText();

         if(isFieldFilled()&&isValid()&&username.equals(BiuroPodrozyApplication.usernameClient)) {
             Parent root = FXMLLoader.load(getClass().getResource("Pulpit_cli.fxml"));

             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = stage.getScene();
             scene.setRoot(root);
             scene.getStylesheets().add(css);
             stage.show();
         }
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}

