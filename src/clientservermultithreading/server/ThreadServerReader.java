package clientservermultithreading.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class ThreadServerReader extends Thread {
    private Database db;
    private Socket socket;
    private String clientName;

    public ThreadServerReader(Database db, Socket socket, String clientName) {
        this.db = db;
        this.socket = socket;
        this.clientName = clientName;
        start();
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            while (!socket.isClosed()) {
                String clientMessage = dis.readUTF();
                System.out.println(clientName + ": " + clientMessage + "; " + new Date());
                db.fileWriter(clientName + ": " + clientMessage + "; " + new Date());
                if (clientMessage.equalsIgnoreCase("quit")) {
                    System.out.println(clientName + " disconnect because 'quit'");
                    socket.close();
                }
            }
        } catch (SocketException e) {
            System.out.println("Client disconnect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
