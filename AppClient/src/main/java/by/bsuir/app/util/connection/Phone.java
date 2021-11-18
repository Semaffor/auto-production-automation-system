package by.bsuir.app.util.connection;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.net.Socket;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Phone {
    static Socket socket;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    public Phone(Socket s) throws IOException {
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            socket = s;
    }

    public static void send(String str) throws IOException {
        oos.writeUTF(str);
        oos.flush();
    }

    public static void sendObject(Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }

    public static Object readObject() throws ClassNotFoundException, IOException {
        return ois.readObject();
    }

    public static String read() throws IOException {
        return ois.readUTF();
    }

    public static void shutdown() throws IOException {
        ois.close();
        oos.close();
        socket.close();
    }

    public static Socket getSocket() {
        return socket;
    }
}
