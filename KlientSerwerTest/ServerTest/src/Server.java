import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Utworzenie serwerowego gniazda nasłuchującego na porcie 12345
            ServerSocket serverSocket = new ServerSocket(12345);

            System.out.println("Serwer nasłuchuje na porcie 12345...");

            while (true) {
                // Akceptowanie połączenia klienta
                Socket clientSocket = serverSocket.accept();

                // Utworzenie wątku obsługującego połączenie klienta
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            // Odbieranie danych od klienta
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Odpowiedź do klienta
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = reader.readLine();
            System.out.println("Odebrano żądanie od klienta: " + request);

            // Przetwarzanie żądania
            String response = "Witaj, to jest serwer!";

            // Wysyłanie odpowiedzi do klienta
            writer.println(response);

            // Zamknięcie połączenia
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
