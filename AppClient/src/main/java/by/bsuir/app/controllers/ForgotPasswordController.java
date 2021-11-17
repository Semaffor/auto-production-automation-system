package by.bsuir.app.controllers;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Status;
import by.bsuir.app.util.connection.Phone;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.time.DateTimeException;
import java.util.Random;
import java.util.ResourceBundle;

import static by.bsuir.app.Commands.PASSWORD_RECOVERY;
import static by.bsuir.app.util.constants.Constants.*;

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
                Phone.sendObject(new Account(login));

                if () {
                    warning_label.setText("Такой аккаунт не найден.");
                } else {
                    Random rand = new Random();
                    int newPass = Math.abs(rand.nextInt() * Integer.parseInt(String.valueOf(login.hashCode())));

                    String mailMsg = NEW_PASSWORD_MSG + newPass;

                    JavaMailUtil.send(email,"Password recovery", mailMsg);

                    String request_2 = "UPDATE " + TEmployees.TABLE_NAME +
                            " SET password = '" + newPass + "' " +
                            " WHERE " + TEmployees.LOGIN + " = '" + login + "'";

                    System.out.println(request_2);
                    Phone.writeLine(request_2);
                    String response = Phone.read();

                    if (response.equals(Status.OK))
                        warning_label.setText(NEW_PASSWORD_FAIL_MSG);
                    else
                        warning_label.setText(CHANGE_PASSWORD_ERROR_MSG);
                }
            } catch (DateTimeException e) {
                warning_label.setText(e.getMessage());

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        warning_label.setVisible(true);
    }
}

