package by.bsuir.app.util.connection;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.net.Socket;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Phone {
    static Socket socket;
    static BufferedReader reader;
    static BufferedWriter writer;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    public Phone(Socket s) throws IOException {
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            socket = s;
    }

    public static void send(String str) throws IOException {
        writer.write(str);
        writer.newLine();
        writer.flush();
    }

    public static void sendObject(Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }

    public static Object readObject() throws ClassNotFoundException, IOException {
        return ois.readObject();
    }

    public static String read() throws IOException {
        return reader.readLine();
    }

    public static void shutdown() throws IOException {
        reader.close();
        writer.close();
        ois.close();
        oos.close();
        socket.close();
    }

    public static Socket getSocket() {
        return socket;
    }
}
