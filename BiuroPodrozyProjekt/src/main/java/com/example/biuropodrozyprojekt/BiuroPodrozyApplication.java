package com.example.biuropodrozyprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

import java.io.IOException;

public class BiuroPodrozyApplication extends Application {

    public static final String usernameAdmin = "admin";
    public static final String passwordAdmin = "admin";
    public static final String usernameClient = "client";
    public static final String passwordClient = "client";

    public static String[] tab = new String[2];

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BiuroPodróży.fxml"));
        Scene loginScene = new Scene( root);
        stage.setTitle("Travel Agency Management System");
        stage.getIcons().add(new Image(("palma.png")));
        String css=this.getClass().getResource("style.css").toExternalForm();
        loginScene.getStylesheets().add(css);
        stage.setScene(loginScene);
        stage.show();
    }


    public static void main(String[] args) {

        try {
            // Tworzenie gniazda klienta i nawiązywanie połączenia z serwerem
            Socket clientSocket = new Socket("localhost", 1234);
            System.out.println("Połączono z serwerem: " + clientSocket.getInetAddress().getHostAddress());

            // Inicjalizacja strumieni wejścia/wyjścia
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Wysyłanie zapytań do serwera
            int i=0;
            String[] queries = {"Zapytanie 1","Zapytanie 2"};
            tab[0] = "nwm";
            tab[1] = "fasfa";
            for (String query : queries) {
                System.out.println("Wysyłanie zapytania do serwera: " + query);
                out.println(query);

                // Odczytywanie odpowiedzi serwera
                String response = in.readLine();
                tab[i] = response;
                System.out.println("Odpowiedź serwera: " + response);
                i++;
            }

            System.out.println("TEST1" + tab[0]);
            System.out.println(tab[1]);
            //System.out.println(tab[0]);

            launch();
            // Zamknięcie połączenia
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}