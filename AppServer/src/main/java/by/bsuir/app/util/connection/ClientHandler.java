package by.bsuir.app.util.connection;

import by.bsuir.app.Server;
import by.bsuir.app.entity.Status;
import by.bsuir.app.util.Constants;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.Socket;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientHandler extends Thread {
    final Phone phone;
    final Socket s;
    volatile boolean keepRunning;

    public ClientHandler(ThreadGroup group, String name, Socket socket) throws IOException {
        super(group, name);
        s = socket;
        keepRunning = true;
        phone = new Phone(socket);
    }

    @Override
    public void run() {
        String requestAction = null;
        Object requestObject = null;
        while (keepRunning) {
            try {

                requestAction = phone.read();
                requestObject = phone.readObject();
                log.info(Constants.REQUEST_MSG + this.getName() + " - " + requestAction + " - " + requestObject);

                //TODO Заменить на COMMAND
                Object response = null;
                Status responseStatus = Status.OK;

                if (Commands.valueOf(requestAction) == Commands.CLOSE_CONNECTION) {
                    keepRunning = false;
                    response = Status.CLOSE_CONNECTION.name();
                } else {
                    response = CommandHandler.execute(Commands.valueOf(requestAction), requestObject);
                    if (response == null)
                        responseStatus = Status.REQUEST_ERROR;
                }

                phone.send(responseStatus.toString());

                if (responseStatus == Status.OK)
                    phone.sendObject(response);

                log.info(Constants.RESPONSE_MSG + this.getName() + " - " + response);

            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage());
                //e.printStackTrace();
                keepRunning = false;
                Server.decrementCountOfConnected();
            }
        }
        try {
            phone.shutdown();
        } catch (IOException e) {
            log.info(e.getMessage());
            //e.printStackTrace();
        }
    }
}

