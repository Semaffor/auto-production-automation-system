package by.bsuir.app.util.connection;

import by.bsuir.app.dao.impl.AccountDaoImpl;
import by.bsuir.app.dao.impl.HistoryLogDaoImpl;
import by.bsuir.app.entity.Account;
import by.bsuir.app.util.Status;
import lombok.extern.log4j.Log4j2;

import static by.bsuir.app.util.ConstantsMSG.INCORRECT_VALUE_MSG;

@Log4j2
public class CommandHandler {
    private static AccountDaoImpl accountDao = new AccountDaoImpl();
    private static HistoryLogDaoImpl historyLogDao = new HistoryLogDaoImpl();

    public static Object execute(Commands command, Object obj) {
        Object response = null;

        //TODO COMMAND
        try {
            response = switch (command) {
                case ADD_OR_UPDATE_USER -> accountDao.saveOrUpdate((Account) obj);
                case AUTHORISATION -> accountDao.auth((Account) obj);
                case DELETE -> accountDao.delete((Account) obj);
                case DELETE_BY_ID -> accountDao.deleteById((Long) obj);
                case GET_USER_BY_ID -> accountDao.findById((Long) obj);
                case GET_ALL_USERS -> accountDao.findAll();
                case GET_USER_BY_LOGIN -> accountDao.findByLogin((String) obj);
                case PASSWORD_RECOVERY -> accountDao.resetPassword((Account) obj);
                case REGISTRATION -> accountDao.registration((Account) obj);
                case GET_LAUNCHES_COUNT_DATA -> historyLogDao.findAllGropedByDate();
                case GET_ALL_USER_LAUNCHES -> historyLogDao.findAllUserLaunches();
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
