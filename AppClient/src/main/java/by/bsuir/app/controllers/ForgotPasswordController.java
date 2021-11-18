package by.bsuir.app.controllers;

import by.bsuir.app.entity.Account;
import by.bsuir.app.util.Status;
import by.bsuir.app.util.connection.Phone;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bsuir.app.util.Commands.PASSWORD_RECOVERY;
import static by.bsuir.app.util.constants.Constants.FILL_FIELDS_MSG;
import static by.bsuir.app.util.constants.Constants.NEW_PASSWORD_MSG;

@Log4j2
public class ForgotPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private Button recoveryButton;

    @FXML
    private TextField email_field;

    @FXML
    private Button returnButton;

    @FXML
    private Label warning_label;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField date_field;

    @FXML
    void handleClose(MouseEvent event) {
        returnButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

        recoveryButton.setOnAction(actionEvent -> {
            recovery();
        });

        returnButton.setOnAction(actionEvent -> {
            returnButton.getScene().getWindow().hide();
        });
    }

    private void recovery() {
        String login = login_field.getText();
        String email = email_field.getText();

        if (login.isEmpty() || email.isEmpty()) {
            warning_label.setText(FILL_FIELDS_MSG);
            warning_label.setVisible(true);
        } else {
            try {
                Phone.send(PASSWORD_RECOVERY.toString());
                Phone.sendObject(new Account(login, "", email));

                String responseStatus = Phone.read();

                if (responseStatus.equals(Status.ACCOUNT_NOT_EXISTS.toString()) || responseStatus.equals(
                        Status.INCORRECT_EMAIL.toString())) {
                    warning_label.setText(responseStatus);
                } else {
                    warning_label.setText(NEW_PASSWORD_MSG + Phone.readObject().toString());
                }
            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        warning_label.setVisible(true);
    }
}

