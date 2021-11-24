package by.bsuir.app.util.constants;

public class Constants {
    public static final int PORT = 5556;
    public static final int ATTEMPTS_COUNT = 3;
    public static final int CONNECTION_WAIT_TIME = 3000;
    public static final int UPDATE_WAIT_TIME = 1500;
    public static final int MIN_LOGIN_LENGTH = 3;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MIN_MESSAGE_LENGTH = 10;

    public static final String IP_ADDRESS = "127.0.0.1";
    public static final String AUTH_FAIL = "Auth has failed. - ";
    public static final String REQUEST_MSG = "REQUEST: ";
    public static final String RESPONSE_MSG = "RESPONSE: ";
    public static final String DELIMITER_MSG = " : ";
    public static final String CHANGE_PASSWORD_ERROR_MSG = "Не удалось сменить пароль. ";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Такой аккаунт не найден.";
    public static final String NEW_PASSWORD_FAIL_MSG = "Не удалось сменить пароль. ";
    public static final String NEW_PASSWORD_MSG = "Ваш новый пароль выслан на почту. ";
    public static final String FILL_FIELDS_MSG = "Заполните все поля";
    public static final String DUPLICATE_LOGIN_MSG = "Логин занят. Придумайте другой.";
    public static final String SUCCESSFUL_REG_MSG = "Вы зарегистрировались.";
    public static final String PASSWORDS_NOT_MATCH = "Пароли не совпадают";
    public static final String LOGIN_IN_USE = "Данный логин уже занят.";
    public static final String MIN_LOGIN_LENGTH_MSG = "Логин менее " + MIN_LOGIN_LENGTH + " символов";
    public static final String MIN_PASSWORD_LENGTH_MSG = "Пароль менее " + MIN_PASSWORD_LENGTH + " символов";
    public static final String REPORT_CREATE_DATE_MSG = "Дата создания отчета: ";
    public static final String LAUNCHES_MSG = "Количество запусков ";
    public static final String REPORT_PATTERN_HEAD_MSG = "\n\tID   User ID\tTime\n  ";
    public static final String GETTING_DATA_FAILURE = "Произошла ошибка при получении данных.";
    public static final String EDITING_DATA_FAILURE = "Ошибка. Изменения не применены.";
    public static final String EDITING_DATA_SUCCESS = "Данные изменены. ";
    public static final String EDITING_DATA_SUCCESS_UPDATE = EDITING_DATA_SUCCESS + "Обновите страницу.";
    public static final String INCORRECT_DATA_MSG = "Некорректные данные.";
    public static final String INCORRECT_MAIL_MSG = "Проверьте корректность введенной почты.";
    public static final String ADD_SUCCESS_MSG = "Успешно. Данные добавлены";
    public static final String ADD_FAIL_MSG = "Не удалось добавить данные.";
    public static final String DELETE_SUCCESS_MSG = "Успешно. Данные удалены.";
    public static final String DELETE_FAIL_MSG = "Не удалось удалить данные.";
    public static final String CURRENCY_MSG = " $";
    public static final String MIN_MESSAGE_LENGTH_MSG = "Минимальное количество символов: " + MIN_MESSAGE_LENGTH;
    public static final String MESSAGE_SUCCESS_MSG = "Успешно отправлено.";
    public static final String MESSAGE_FAIL_MSG = "Ошибка отправки сообщения. Повторите попытку позже.";
    public static final String MESSAGE_CHOOSE_TO_SEND_MSG = "Выберите, кому хотите отправить письмо.";
    public static final String MAIL_IS_NOT_SET_MSG = "Ошибка отправки. Почта не указана.";
    public static final String FAIL_MSG = "Произошла ошибка.";
    public static final String ERROR_SELECT_FIELD_MSG = "Выделите необходимый элемент.";
    public static final String CREATED_RESTORE_POINT_MSG = "Создана точка восстановления.";
    public static final String LAST_RESTORE_POINT_MSG = "Возращено к последней точки восстановления. ";
    public static final String INCORRECT_RATE_MSG = "Рейтинг от 0,1 до 5,0. ";
    public static final String USE_COMA_INSTEADOF_POINT = "Используйте ',' вместо '.'";
    public static final String ACCOUNT_IS_BLOCKED_MSG = "Ваш аккаунт заблокирован.";
    public static final String INCORRECT_LOGIN_OR_PASSWORD_MSG = "Неверный логин или пароль. Повторите попытку";

}
