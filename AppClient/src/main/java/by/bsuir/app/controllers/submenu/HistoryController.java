package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Feedback;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import by.bsuir.app.util.constants.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.bsuir.app.util.constants.Constants.INCORRECT_DATA_MSG;

public class HistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label count_requests_field;

    @FXML
    private VBox adminBox;

    @FXML
    private Label count_outAnswers_field;

    @FXML
    private Label count_answers_field;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox hBox;

    @FXML
    private Label warning_label;

    @FXML
    private TextField text_field;

    @FXML
    private Button answer_button;


    @FXML
    void handleClose(MouseEvent event) {
        GeneralFuncWindow.closeApplication();
    }

    List<Feedback> feedbacks = null;

    @FXML
    void initialize() throws IOException {
        try {
            Role role = Role.valueOf(LocalStorage.getAccount().getRole());
            if (role == Role.ADMIN) {
                adminBox.setVisible(true);
                hBox.setVisible(true);
                feedbacks = (List<Feedback>) Phone.sendOrGetData(Commands.GET_ALL_USERS_FEEDBACKS, new Feedback());

            } else {
                feedbacks = (List<Feedback>) Phone.sendOrGetData(Commands.GET_ALL_FEEDBACKS_BY_USER_LOGIN,
                        LocalStorage.getAccount().getLogin());
            }

            LocalStorage.setFeedbacks(new ArrayList<>(feedbacks));

            int size = feedbacks.size();
            Node[] nodes = new Node[size];
            VBox vBox = new VBox();
            for (int i = 0; i < size; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource(Paths.WindowHistoryRow));
                vBox.getChildren().add(nodes[i]);
            }
            scrollPane.setContent(vBox);

            setLabelsData();

        } catch (ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }

        answer_button.setOnAction(actionEvent -> {
            warning_label.setVisible(false);

            String text = text_field.getText();
            try {
                Long id = Long.valueOf(text);

                List<Feedback> feedbackForFilter = new ArrayList<>(feedbacks);
                Optional<Feedback> first = feedbackForFilter.stream().filter(f -> f.getId().equals(id)).findFirst();

                if (first.isEmpty())
                    throw new NumberFormatException();

                LocalStorage.setFeedback(first.get());
                GeneralFuncWindow.openNewScene(Paths.WindowTextField);

            } catch (NumberFormatException e) {
                warning_label.setText(INCORRECT_DATA_MSG);
                warning_label.setVisible(true);
            }
        });
    }

    private void setLabelsData() {
        int count_request = feedbacks.size();
        count_requests_field.setText(String.valueOf(count_request));
        int count_answers = countAnswersField();
        count_answers_field.setText(String.valueOf(count_answers));
        count_outAnswers_field.setText(String.valueOf(count_request - count_answers));
    }

    private int countAnswersField() {
        int count = 0;
        for (Feedback f : feedbacks) {
            if (f.getAnswer() != null)
                count++;
        }

        return count;
    }
}
