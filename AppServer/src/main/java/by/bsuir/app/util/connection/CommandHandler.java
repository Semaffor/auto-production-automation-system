package by.bsuir.app.util.connection;

import by.bsuir.app.dao.impl.AccountDaoImpl;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Status;
import lombok.extern.log4j.Log4j2;

import static by.bsuir.app.util.Constants.INCORRECT_VALUE_MSG;

@Log4j2
public class CommandHandler {
    private static AccountDaoImpl accountDao = new AccountDaoImpl();

    public static Object execute(Commands command, Object obj) {
        Object response = null;

        try {
            response = switch (command) {
                case ADD_OR_UPDATE_USER -> accountDao.saveOrUpdate((Account) obj);
                case AUTHORISATION -> accountDao.auth((Account) obj);
                case DELETE -> accountDao.delete((Account) obj);
                case DELETE_BY_ID -> accountDao.deleteById((Long) obj);
                case GET_USER_BY_ID -> accountDao.findById((Long) obj);
                case GET_ALL_USERS -> accountDao.findAll();
                default -> defaultBranch(command);

            };
        } catch (ClassCastException e) {
            log.info(e.getMessage());
        }
        return response;
    }

    private static Object defaultBranch(Commands command) {
        log.info(INCORRECT_VALUE_MSG + command);
        return Status.REQUEST_ERROR;
    }
}
