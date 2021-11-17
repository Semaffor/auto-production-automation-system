//package by.bsuir.app.clientConnection;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ConnectionTCP implements AutoCloseable {
//    protected final Socket socket;
//    private final BufferedWriter writer;
//    private final BufferedReader reader;
//
//    public ConnectionTCP(String ip, int port) throws IOException {
//        this.socket = new Socket(ip, port);
//        this.writer = createWriter();
//        this.reader = createReader();
//    }
//
//    public ConnectionTCP(ServerSocket sSocket) throws IOException {
//        this.socket = sSocket.accept();
//        this.writer = createWriter();
//        this.reader = createReader();
//    }
//
//    public void write(String line) {
//        try {
//            writer.write(line);
//            writer.newLine();
//            writer.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void write(Object obj) {
//        try {
//            socket.getOutputStream().write((byte[]) obj);
//            socket.getOutputStream().flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String readLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Object readObject() {
//        ObjectInputStream in;
//        try {
//            in = new ObjectInputStream(socket.getInputStream());
//            return in.readObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private BufferedReader createReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
//    }
//
//    private BufferedWriter createWriter() throws IOException {
//        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//    }
//
//    private ObjectInputStream createObjReader() throws IOException {
//        return new ObjectInputStream(socket.getInputStream());
//    }
//
//    private ObjectOutputStream createObjWriter() throws IOException {
//        return new ObjectOutputStream(socket.getOutputStream());
//    }
//
//    @Override
//    public void close() throws Exception {
//        socket.close();
//        reader.close();
//        writer.close();
//    }
//}
