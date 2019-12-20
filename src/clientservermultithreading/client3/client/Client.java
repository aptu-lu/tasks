package clientservermultithreading.client3.client;

import java.io.IOException;
import java.net.Socket;

public class Client{
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777)) {
            System.out.println("Successful connection to the server");
            Thread asd = new ThreadClientWrite(socket);
            Thread sdf = new ThreadClientRead(socket);
            asd.join();
            sdf.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
