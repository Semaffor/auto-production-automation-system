package by.bsuir.app.util.connection;

import by.bsuir.app.dao.*;
import by.bsuir.app.dao.impl.*;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Feedback;
import by.bsuir.app.entity.Message;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.service.Services;
import by.bsuir.app.util.Status;
import lombok.extern.log4j.Log4j2;

import javax.mail.MessagingException;

import static by.bsuir.app.util.ConstantsMSG.INCORRECT_VALUE_MSG;

@Log4j2
public class CommandHandler {
    private static final AccountDao accountDao = new AccountDaoImpl();
    private static final HistoryLogDao historyLogDao = new HistoryLogDaoImpl();
    private static final CarDao carDao = new CarDaoImpl();
    private static final ModelDao modelDao = new ModelDaoImpl();
    private static final FeedbackDao feedbackDao = new FeedbackDaoImpl();

    public static Object execute(Commands command, Object obj) {
        Object response = null;

        //TODO COMMAND
        try {
            response = switch (command) {
                case USER_ADD_OR_UPDATE -> accountDao.saveOrUpdate((Account) obj);
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
                case ADD_NEW_CAR -> carDao.saveOrUpdate((Car) obj);
                case GET_ALL_MODELS -> modelDao.findAll();
                case GET_ALL_CARS -> carDao.findAll();
                case GET_ALL_FEEDBACKS_BY_USER_LOGIN -> feedbackDao.findAllByUserLogin((String) obj);
                case ADD_QUESTION_FROM_USER -> feedbackDao.saveOrUpdate((Feedback) obj);
                case SEND_MESSAGE_TO_USER -> Services.sendMessage((Message) obj);
                case GET_ALL_USERS_FEEDBACKS -> feedbackDao.findAll();
                case ADD_ANSWER_ON_QUESTION -> feedbackDao.saveAnswer((Feedback) obj);
                case GET_ALL_MODELS_GROUPED_BY_QUANTITY -> carDao.findAllGroupedByQuantity();
                default -> defaultBranch(command);

            };
        } catch (ClassCastException | MessagingException | DAOException e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private static Object defaultBranch(Commands command) {
        log.info(INCORRECT_VALUE_MSG + command);
        return Status.REQUEST_ERROR;
    }
}
