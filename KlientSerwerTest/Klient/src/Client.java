import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Tworzenie gniazda klienta i łączenie się z serwerem na porcie 12345
            Socket socket = new Socket("localhost", 12345);

            // Wysyłanie danych do serwera
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Select login,password from user");
            writer.println("");
            String test;
            test = writer

            // Odbieranie odpowiedzi od serwera
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();

            System.out.println("Odpowiedź serwera: " + response);

            // Zamknięcie połączenia
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
