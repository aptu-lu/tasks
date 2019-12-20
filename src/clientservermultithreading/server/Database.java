package clientservermultithreading.server;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Database {
    private static final String fileName = "database.txt";
    private String message;
    private long mark;

    String fileReader() {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            raf.seek(mark);
            message = raf.readUTF();
            mark = raf.length();
        } catch (EOFException e) {
            //TODO исключение при работе более чем с 1 клиентом
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    void fileWriter(String message) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName),"rw")){
            mark = raf.length();
            raf.seek(mark);
            raf.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     boolean haveNewInfo() {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName),"rw")){
            if (raf.length() - mark > 0) {
                return true;
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
        return false;
    }

    void setStartMark() {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName),"rw")){
            mark = raf.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
