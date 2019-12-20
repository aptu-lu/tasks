package clientservermultithreading.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            System.out.println("Start server");
            int i = 1;
            Database database = new Database();
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                database.setStartMark();
                System.out.println("Successful client connection");
                new ThreadServerWriter(database, client);
                new ThreadServerReader(database, client, "clientName" + i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
