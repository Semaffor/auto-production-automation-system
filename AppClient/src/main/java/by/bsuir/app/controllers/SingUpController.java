package by.bsuir.app.controllers;

import by.bsuir.app.animation.Shake;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.enums.Gender;
import by.bsuir.app.exception.EmptyFieldsException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.Status;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bsuir.app.util.constants.Constants.*;

@Log4j2
public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private PasswordField confirm_password_field;

    @FXML
    private Button registrationButton;

    @FXML
    private TextField email_field;

    @FXML
    private Label warning_label;

    @FXML
    private Button returnButton;

    @FXML
    void handleClose(MouseEvent event) {
        registrationButton.getScene().getWindow().hide();
    }

    @FXML
    private RadioButton boxMale;

    @FXML
    private RadioButton boxFemale;

    @FXML
    void initialize() {

        registrationButton.setOnAction(actionEvent -> {
            singUpNewUser();
        });

        returnButton.setOnAction(actionEvent -> {
            returnButton.getScene().getWindow().hide();
        });
    }

    //TODO GENDERHANDLE
    private void singUpNewUser() {
        try {
            String login = login_field.getText();
            String password = password_field.getText();
            String confirmPassword = confirm_password_field.getText();
            String email = email_field.getText();
            Gender gender;

            if (login.equals("") || password.equals("") || confirmPassword.equals("") || email.equals(""))
                throw new EmptyFieldsException(Constants.FILL_FIELDS_MSG);

            if (boxMale.isSelected())
                gender = Gender.MALE;
            else if (boxFemale.isSelected())
                gender = Gender.FEMALE;
            else
                throw new EmptyFieldsException(Constants.FILL_FIELDS_MSG);

            if (login.length() < MIN_LOGIN_LENGTH)
                throw new IllegalArgumentException(MIN_LOGIN_LENGTH_MSG);
            if (password.length() < MIN_PASSWORD_LENGTH)
                throw new IllegalArgumentException(MIN_PASSWORD_LENGTH_MSG);
            if (!password.equals(confirmPassword))
                throw new IllegalArgumentException(PASSWORDS_NOT_MATCH);

            Phone.send(Commands.REGISTRATION.toString());
            Phone.sendObject(new Account(login, password, email));

            String answer = Phone.read();

            if (answer.equals(Status.DUPLICATE_LOGIN.toString()))
                throw new IllegalArgumentException(DUPLICATE_LOGIN_MSG);

            Object obj = Phone.readObject();

            warning_label.setText(SUCCESSFUL_REG_MSG);
            registrationButton.getScene().getWindow().hide();

        } catch (EmptyFieldsException e) {
            warning_label.setText(e.getMessage());
            warning_label.setVisible(true);
            runAnimation();
        } catch (IllegalArgumentException e) {
            warning_label.setText(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        warning_label.setVisible(true);

    }

    private void runAnimation() {
        Shake loginAnim = new Shake(login_field);
        Shake passwordAnim = new Shake(password_field);
        Shake confirmPasswordAnim = new Shake(confirm_password_field);
        Shake emailAnim = new Shake(email_field);
        loginAnim.playAnim();
        passwordAnim.playAnim();
        confirmPasswordAnim.playAnim();
        emailAnim.playAnim();
    }
}