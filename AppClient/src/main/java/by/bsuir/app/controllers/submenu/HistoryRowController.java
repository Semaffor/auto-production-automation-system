package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Feedback;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HistoryRowController {

    @FXML
    private Label id_label;

    @FXML
    private Label question_field;

    @FXML
    private Label answer_field;

    @FXML
    private Label request_date_field;

    @FXML
    private Label answer_date_field;

    @FXML
    void initialize() {
        initializeBlockWithData();
    }

    private void initializeBlockWithData() {

        Feedback feedback = LocalStorage.getFirstFeedback();

        if (feedback != null) {
            id_label.setText(feedback.getId().toString());
            question_field.setText(feedback.getQuestion());
            if (feedback.getAnswer() != null)
                answer_field.setText(feedback.getAnswer());
            request_date_field.setText(feedback.getQuestionDate().toString());
            if (feedback.getAnswerDate() != null)
                answer_date_field.setText(feedback.getAnswerDate().toString());
        }
    }
}


