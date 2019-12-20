package clientservermultithreading.client3.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ThreadClientRead extends Thread {
    Socket socket;

    public ThreadClientRead(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            while (!socket.isClosed()) {
                String message = dis.readUTF();
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("Disconnecting from the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
