package by.bsuir.app;

import by.bsuir.app.util.connection.ClientHandler;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;

import static by.bsuir.app.util.Constants.*;


@Log4j2
public class Server {


    private static int PORT = 5556;
    private static final AtomicInteger countOfConnected = new AtomicInteger(0);
    private static volatile boolean isActive = true;
    private static final ThreadGroup threadGroup = new ThreadGroup("mainGroup");

    public static void main(String[] args) {
        while (isActive) {

            try (ServerSocket ss = new ServerSocket(PORT)) {
                log.info(SERVER_STARTED_MSG);
                log.info(CURRENT_PORT_MSG + PORT);

                Socket socket;
                while (isActive) {
                    socket = null;
                    socket = ss.accept();
                    countOfConnected.incrementAndGet();

                    log.info(CLIENT_CONNECTED_MSG + socket + " " + CURRENT_CONNECTION_MSG + countOfConnected);

                    Runnable runnable = new ClientHandler(threadGroup, "Client_" + countOfConnected, socket);

                    Thread newThread = new Thread(runnable);
                    newThread.start();

                }
                do {
                    log.info(SERVER_OFF_MSG + countOfConnected);
                } while (threadGroup.activeCount() > 0);
            } catch (BindException e) {
                log.error(e.getMessage() + " " + CURRENT_PORT_MSG + PORT);
                ++PORT;
            } catch (SocketException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                decrementCountOfConnected();
            }
        }
    }

    public static void decrementCountOfConnected() {
        log.info(COUNT_CONNECTED_MSG + countOfConnected.decrementAndGet());
    }
}
