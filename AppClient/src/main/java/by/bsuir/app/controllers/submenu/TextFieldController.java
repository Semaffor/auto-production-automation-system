package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Feedback;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bsuir.app.util.constants.Constants.*;

public class TextFieldController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea feedback_area;

    @FXML
    private Button accept_button;

    @FXML
    private Label question_field;

    @FXML
    private Label warning_label;

    @FXML
    void handleClose(MouseEvent event) {
        accept_button.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        question_field.setText(LocalStorage.getQuestion());

        accept_button.setOnAction(actionEvent -> { sendAnswer(); });

    }

    private void sendAnswer() {
        String answer = feedback_area.getText();

        try {
            if (answer.length() < MIN_MESSAGE_LENGTH)
                warning_label.setText(MIN_MESSAGE_LENGTH_MSG);
            else {
                Feedback feedback = LocalStorage.getFeedback();
                feedback.setAnswer(answer);
                Phone.sendOrGetData(Commands.ADD_ANSWER_ON_QUESTION, feedback);

                warning_label.setText(MESSAGE_SUCCESS_MSG);
            }
        } catch (IOException | GettingDataException | ClassNotFoundException e) {
            warning_label.setText(FAIL_MSG);
            e.printStackTrace();
        }
    }
}

