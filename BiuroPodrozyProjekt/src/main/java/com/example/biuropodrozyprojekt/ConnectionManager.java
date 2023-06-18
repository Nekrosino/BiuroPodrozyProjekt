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


public class ConnectionManager {
    private String sessionId;
    private Socket clientSockett;
    private PrintWriter out;
    private BufferedReader in;
    private String data;

    public String connectToServer(String login, String password) throws IOException {
        // Tworzenie połączenia z serwerem
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        // Wysyłanie żądania logowania
        out.println("LOGIN " + login + " " + password);
        System.out.println("Wysłane dane: " + login + " " + password);
        data = login;

        String response = in.readLine();
        // Jeżeli logowanie powiodło się, otrzymujemy identyfikator sesji
        if (response.startsWith("SESSION_ID")) {
            sessionId = response.split(" ")[1];
            System.out.println("Zalogowano pomyślnie. SESSION ID: " + sessionId);
            return sessionId;
            // Wywołanie innych operacji związanych z połączeniem
        } else {
            System.out.println("Błąd logowania");
            return null;
        }
    }

    public void logout() throws IOException {
        // Sprawdzenie, czy klient jest zalogowany
        if (sessionId != null) {
            // Wysłanie żądania wylogowania
            out.println("LOGOUT " + sessionId);
            String response = in.readLine();

            if (response.equals("LOGOUT_SUCCESS")) {
                // Wylogowanie zakończone pomyślnie
                sessionId = null;
                //disconnect();

                System.out.println("Wylogowano pomyślnie");
            } else {
                System.out.println("Błąd wylogowania");
            }
        } else {
            System.out.println("Klient nie jest zalogowany");
            //switchToLoginScene();
            //disconnect();
        }
    }

    public void disconnect() throws IOException {
        // Zamknięcie połączenia
        if (clientSockett != null) {
            clientSockett.close();
        }
    }

    public void setData(String login) throws IOException
    {
        this.data = login;
    }

    public String getProfileData(String login,String password) throws IOException
    {
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        // Wysyłanie żądania danych
        out.println("PROFILEDATA " + login + " " + password);
        System.out.println("Wysłane dane: " + login + " " + password);


        String response = in.readLine();
        // Jeżeli logowanie powiodło się, otrzymujemy identyfikator sesji
        if (response.startsWith("PROFILEDATA")) {
            String parts = response;
            System.out.println("Otrzymano pomyślnie dane dla użytkownika: "+parts);
            return parts;

        } else {
            System.out.println("Błąd pobrania danych");
            return null;
        }
    }
    // Pozostałe metody związane z połączeniem
}
