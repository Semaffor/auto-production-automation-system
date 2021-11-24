package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Feedback;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bsuir.app.util.constants.Constants.*;

@Log4j2
public class FeedbackController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea feedback_area;

    @FXML
    private Button accept_button;

    @FXML
    private Label warning_label;

    @FXML
    void handleClose(MouseEvent event) {
        GeneralFuncWindow.closeApplication();
    }

    @FXML
    void initialize() {
        accept_button.setOnAction(actionEvent -> sendQuestion());

    }

    private void sendQuestion() {

        String question = feedback_area.getText();

        if (question.length() < MIN_MESSAGE_LENGTH) {
            warning_label.setText(MIN_MESSAGE_LENGTH_MSG);
        } else {
            try {

                Account account = (Account) Phone.sendOrGetData(Commands.GET_USER_BY_LOGIN,
                        LocalStorage.getAccount().getLogin());

                Feedback feedback = new Feedback();
                feedback.setQuestion(question);
                feedback.setSender_id(account.getId());

                Phone.sendOrGetData(Commands.ADD_QUESTION_FROM_USER, feedback);
                warning_label.setText(MESSAGE_SUCCESS_MSG);
                feedback_area.setText("");
            } catch (IOException | GettingDataException | ClassNotFoundException e) {
                warning_label.setText(MESSAGE_FAIL_MSG);
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        warning_label.setVisible(true);
    }
}
