package by.bsuir.app.controllers;

import by.bsuir.app.animation.Shake;
import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.AuthenticationException;
import by.bsuir.app.exception.RoleRecognitionException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.Status;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.Constants;
import by.bsuir.app.util.constants.WindowsPaths;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

import static by.bsuir.app.services.GeneralFuncWindow.openNewScene;
import static by.bsuir.app.util.constants.Constants.DELIMITER_MSG;

@Log4j2
public class SingInController {

    @FXML
    private Button singUpButton;

    @FXML
    private Button catalog_button;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private Label msg_label;

    @FXML
    private Hyperlink forgotPasswordButton;

    @FXML
    private Button singInButton;

    @FXML
    void initialize() {

        singInButton.setOnAction(actionEvent -> {
            msg_label.setVisible(false);
            String loginText = login_field.getText().trim();
            String passText = password_field.getText().trim();

            if (!loginText.equals("") && !passText.equals("")) {
                loginUser(loginText, passText);
            } else {
                msg_label.setText(Constants.FILL_FIELDS_MSG);
                msg_label.setVisible(true);
            }
        });

        catalog_button.setOnAction(actionEvent -> {
            openNewScene(WindowsPaths.WindowCatalog);
        });
        forgotPasswordButton.setOnAction(actionEvent -> {
            openNewScene(WindowsPaths.WindowForgotPassword);
        });
        singUpButton.setOnAction(actionEvent -> {
            openNewScene(WindowsPaths.WindowSignUp);
        });
    }


    public void handleClose(MouseEvent mouseEvent) {
        GeneralFuncWindow.closeApplication();
    }

    private void loginUser(String loginText, String passText) {

        try {
            Phone.send(Commands.AUTHORISATION.toString());
            Phone.sendObject(new Account(loginText, passText));
            log.info(Constants.REQUEST_MSG + loginText + DELIMITER_MSG + passText);

            String response = Phone.read();
            if (!response.equals(Status.OK.toString()))
                throw new AuthenticationException();

            Role accountRole = (Role) Phone.readObject();
            log.info(Constants.RESPONSE_MSG + response + DELIMITER_MSG + accountRole);

            switch (accountRole) {
                case UNDEFINED -> {
                    throw new AuthenticationException();
                }
                case ADMIN -> openNewScene(WindowsPaths.WindowAdminClient);
                case USER -> openNewScene(WindowsPaths.WindowAccountantClient);
                case GUEST -> openNewScene(WindowsPaths.WindowSimpleClient);
                default -> throw new RoleRecognitionException();
            }
        } catch (IOException | ClassNotFoundException | AuthenticationException | RoleRecognitionException e) {
            log.error(Constants.AUTH_FAIL + e.getMessage());
            msg_label.setVisible(true);
            Shake loginAnim = new Shake(login_field);
            Shake passwordAnim = new Shake(password_field);
            loginAnim.playAnim();
            passwordAnim.playAnim();
        }
    }
}