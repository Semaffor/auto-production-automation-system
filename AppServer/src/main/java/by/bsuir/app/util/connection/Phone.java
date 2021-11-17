package by.bsuir.app.util.connection;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.net.Socket;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Phone {
    BufferedReader reader;
    BufferedWriter writer;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public Phone(Socket s) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String str) throws IOException {
        writer.write(str);
        writer.newLine();
        writer.flush();
    }

    public void sendObject(Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }

    public Object readObject() throws ClassNotFoundException, IOException {
        return ois.readObject();
    }

    public String read() throws IOException {
        return reader.readLine();
    }

    public void shutdown() throws IOException {
        reader.close();
        writer.close();
        ois.close();
        oos.close();
    }

}
