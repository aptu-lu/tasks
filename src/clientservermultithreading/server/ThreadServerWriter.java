package clientservermultithreading.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServerWriter extends Thread {
    private Socket socket;
    private Database db;

    public ThreadServerWriter(Database db, Socket socket) {

        this.db = db;
        this.socket = socket;

        start();
    }

    @Override
    public void run() {
        try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            while (!socket.isClosed()) {
                System.out.println("4itaet iz bd");
                if (db.haveNewInfo()) {
                    String clientMessage = db.fileReader();
                    dos.writeUTF(clientMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
