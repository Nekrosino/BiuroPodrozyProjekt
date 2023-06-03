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

public class BiuroPodrozyController implements Initializable
 {

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

    public boolean isValid(){
        boolean isValid = true;
        if(!LoginInput.getText().equals(BiuroPodrozyApplication.usernameAdmin))
        {
            isValid =false;
            errorMessage = "Invalid Username";
        }

    /*
        if(!LoginInput.getText().equals(BiuroPodrozyApplication.usernameClient))
        {
            isValid =false;
            errorMessage = "Invalid Username";
        }
*/
        if(!PasswordInput.getText().equals(BiuroPodrozyApplication.passwordAdmin)){
            isValid = false;
            if(errorMessage.isEmpty()) {
                errorMessage = "Invalid Password!";
            } else {
                errorMessage +="\nInvalid Password!";
            }
        }
/*
        if(!PasswordInput.getText().equals(BiuroPodrozyApplication.passwordClient)){
            isValid = false;
            if(errorMessage.isEmpty()) {
                errorMessage = "Invalid Password";
            } else {
                errorMessage += "\nInvalid Password";
            }
        }

*/
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



    /* ZMIANA SCENY NA PANEL ADMINA PO ZALOGOWANIU - NIE DZIALA
     public void switchToScene3() throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Pulpit_adm.fxml"));
         //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         //scene = new Scene(root);
         Stage stage = new Stage();
         scene.getStylesheets().add(css);
         stage.setScene(new Scene(root));
         stage.show();
     }
*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            Login.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    errorMessage = " ";
                    if(isFieldFilled() && isValid()){
                        // do wklejenia funkcja zmieniająca scene na odpowiedni pulpit admin/client
                    }
                }
            });
    }


}

