package clientservermultithreading.client3.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ThreadClientWrite extends Thread {
    private Socket socket;

    public ThreadClientWrite(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!socket.isClosed()) {
                String message = br.readLine();
                if (message.equalsIgnoreCase("quit")) {
                    System.out.println("Disconnect from server");
                    socket.close();
                }
                dos.writeUTF(message);
            }

        } catch (SocketException e) {
            System.out.println("Disconnecting from the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
