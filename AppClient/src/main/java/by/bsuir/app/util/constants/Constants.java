package by.bsuir.app.util.constants;

public class Constants {
    public static final int PORT = 5556;
    public static final int ATTEMPTS_COUNT = 3;
    public static final int CONNECTION_WAIT_TIME = 1000;
    public static final int MIN_LOGIN_LENGTH = 3;
    public static final int MIN_PASSWORD_LENGTH = 6;

    public static final String IP_ADDRESS = "127.0.0.1";
    public static final String AUTH_FAIL = "Auth has failed. - ";
    public static final String REQUEST_MSG = "REQUEST: ";
    public static final String RESPONSE_MSG = "RESPONSE: ";
    public static final String DELIMITER_MSG = " : ";
    public static final String CHANGE_PASSWORD_ERROR_MSG = "Не удалось сменить пароль. ";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Такой аккаунт не найден.";
    public static final String NEW_PASSWORD_FAIL_MSG = "Не удалось сменить пароль. ";
    public static final String NEW_PASSWORD_MSG = "Ваш новый пароль: ";
    public static final String FILL_FIELDS_MSG = "Заполните все поля";
    public static final String DUPLICATE_LOGIN_MSG = "Логин занят. Придумайте другой.";
    public static final String SUCCESSFUL_REG_MSG = "Вы зарегистрировались.";
    public static final String PASSWORDS_NOT_MATCH = "Пароли не совпадают";
    public static final String MIN_LOGIN_LENGTH_MSG = "Логин менее " + MIN_LOGIN_LENGTH + " символов";
    public static final String MIN_PASSWORD_LENGTH_MSG = "Пароль менее " + MIN_PASSWORD_LENGTH + " символов";

}
