//package by.bsuir.app.clientConnection;
//
//import java.io.IOException;
//
//
//public class Phone implements Runnable, AutoCloseable{
//
//    private static ConnectionTCP connection;
//
//    public Phone(String ip, int port) throws IOException, InterruptedException {
//        connection = new ConnectionTCP(ip,port);
//    }
//
//    public static void writeLine(String msg) {
//        connection.write(msg);
//    }
//
//    public static Object readObject() {
//        return connection.readObject();
//    }
//
//    public static String readLine() {
//        return connection.readLine();
//    }
//
//    public static ConnectionTCP getConnection() {
//        return connection;
//    }
//
//    @Override
//    public void run() {
//    }
//
//    @Override
//    public void close() throws Exception {
//        connection.close();
//    }
//}