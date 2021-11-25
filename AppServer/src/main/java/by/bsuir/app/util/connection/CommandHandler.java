package by.bsuir.app.util.connection;

import by.bsuir.app.dao.*;
import by.bsuir.app.dao.impl.*;
import by.bsuir.app.entity.*;
import by.bsuir.app.exception.DAOException;
import by.bsuir.app.service.Services;
import by.bsuir.app.service.reportFactory.ReportFactory;
import by.bsuir.app.service.reportFactory.ReportTypes;
import by.bsuir.app.service.snapshot.Manipulator;
import by.bsuir.app.util.constants.Status;
import lombok.extern.log4j.Log4j2;

import javax.mail.MessagingException;

import static by.bsuir.app.util.connection.Commands.CREATE_PDF_REPORT;
import static by.bsuir.app.util.constants.ConstantsMSG.INCORRECT_VALUE_MSG;

@Log4j2
public class CommandHandler {
    private static final AccountDao accountDao = new AccountDaoImpl();
    private static final HistoryLogDao historyLogDao = new HistoryLogDaoImpl();
    private static final CarDao carDao = new CarDaoImpl();
    private static final ModelDao modelDao = new ModelDaoImpl();
    private static final FeedbackDao feedbackDao = new FeedbackDaoImpl();
    private static final Manipulator manipulator = new Manipulator();
    private static final PersonalDataDao personalData = new PersonalDataDaoImpl();
    private static final ReportFactory reportFactory = ReportFactory.getInstance();

    public static Object execute(Commands command, Object obj) {
        Object response = null;

        //TODO COMMAND
        try {
            response = switch (command) {
                case USER_ADD_OR_UPDATE -> accountDao.saveOrUpdate((Account) obj);
                case AUTHORISATION -> accountDao.auth((Account) obj);
                case DELETE_USER -> accountDao.delete((Account) obj);
                case DELETE_USER_BY_ID -> accountDao.deleteById((Long) obj);
                case GET_USER_BY_ID -> accountDao.findById((Long) obj);
                case GET_ALL_USERS -> accountDao.findAll();
                case GET_USER_BY_LOGIN -> accountDao.findByLogin((String) obj);
                case PASSWORD_RECOVERY -> accountDao.resetPassword((Account) obj);
                case REGISTRATION -> accountDao.registration((Account) obj);
                case GET_LAUNCHES_COUNT_DATA -> historyLogDao.findAllGropedByDate();
                case GET_ALL_USER_LAUNCHES -> historyLogDao.findAllUserLaunches();
                case ADD_NEW_CAR, CAR_ADD_OR_UPDATE -> carDao.saveOrUpdate((Car) obj);
                case GET_ALL_MODELS -> modelDao.findAll();
                case GET_ALL_CARS -> carDao.findAll();
                case GET_ALL_FEEDBACKS_BY_USER_LOGIN -> feedbackDao.findAllByUserLogin((String) obj);
                case ADD_QUESTION_FROM_USER -> feedbackDao.saveOrUpdate((Feedback) obj);
                case SEND_MESSAGE_TO_USER -> Services.sendMessage((Message) obj);
                case GET_ALL_USERS_FEEDBACKS -> feedbackDao.findAll();
                case ADD_ANSWER_ON_QUESTION -> feedbackDao.saveAnswer((Feedback) obj);
                case GET_ALL_MODELS_GROUPED_BY_QUANTITY -> carDao.findAllGroupedByQuantity();
                case SAVE_CAR_DATA_LOCAL_STORAGE -> manipulator.saveObjectInMemory(new Car() );
                case RESTORE_CAR_DATA_LOCAL_STORAGE -> manipulator.getObjectFromMemory(new Car() );
                case DELETE_CAR_BY_VIN -> carDao.deleteByVIN((String) obj);
                case GET_AGE_PERCENT_PROPORTION -> personalData.findAgePercentProportion();
                case CREATE_PDF_REPORT -> reportFactory.getReport(ReportTypes.PDF);
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
