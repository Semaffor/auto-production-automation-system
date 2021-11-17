//package by.bsuir.app.controllers.submenu;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import com.bsuir.entities.Feedback;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import sample.clientConnection.Phone;
//import sample.constansts.MigrateData;
//import sample.constansts.Positions;
//import sample.constansts.Windows;
//import sample.constansts.Data;
//import sample.services.GeneralFuncWindow;
//
//public class HistoryController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private Label count_requests_field;
//
//    @FXML
//    private VBox adminBox;
//
//    @FXML
//    private Label count_outAnswers_field;
//
//    @FXML
//    private Label count_answers_field;
//
//    @FXML
//    private ScrollPane scrollPane;
//
//    @FXML
//    private HBox hBox;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    private TextField text_field;
//
//    @FXML
//    private Button answer_button;
//
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() throws IOException {
//        String request;
//        if (Data.getPosition().getPositionName().equals(Positions.ADMIN.getTitle())) {
//            adminBox.setVisible(true);
//            hBox.setVisible(true);
//            request = "SELECT * FROM Feedback ";
//        } else {
//            request = "SELECT * FROM Feedback " +
//                    "WHERE sender_id = " + Data.getEmpl().getId();
//        }
//        Phone.writeLine(request);
//        Data.setFeedbackList((ArrayList<Feedback>)  Phone.readObject());
//
//        ArrayList<Feedback> localFeedback = new ArrayList<Feedback>(Data.getFeedbackList());
//        setLabelsData();
//
//        Node[] nodes = new Node[Data.getFeedbackList().size()];
//        VBox vBox = new VBox();
//        for (int i = 0; !Data.getFeedbackList().isEmpty(); i++) {
//            nodes[i] = (Node) FXMLLoader.load(getClass().getResource(Windows.WindowHistoryRow));
//            vBox.getChildren().add(nodes[i]);
//        }
//        scrollPane.setContent(vBox);
//
//        answer_button.setOnAction(actionEvent -> {
//            try {
//                MigrateData.num = Integer.parseInt(text_field.getText());
//                System.out.println(localFeedback.size());
//                for (int i = 0; i < localFeedback.size(); i++) {
//                    if (localFeedback.get(i).getId() == MigrateData.num) {
//                        MigrateData.num = localFeedback.get(i).getId();
//                        MigrateData.question = localFeedback.get(i).getQuestion();
//                        GeneralFuncWindow.openNewScene(Windows.WindowTextField);
//                        break;
//                    }
//                    if (i == localFeedback.size() - 1) {
//                        warning_label.setText("Неверное значение.");
//                        warning_label.setVisible(true);
//                    }
//                }
//            } catch (NumberFormatException e) {
//                warning_label.setText("Неверное значение");
//                warning_label.setVisible(true);
//            }
//        });
//    }
//
//    private void setLabelsData() {
//        int count_request = Data.getFeedbackList().size();
//        count_requests_field.setText(String.valueOf(count_request));
//        int count_answers = countAnswersField();
//        count_answers_field.setText(String.valueOf(count_answers));
//        count_outAnswers_field.setText(String.valueOf(count_request-count_answers));
//    }
//    private int countAnswersField() {
//        int count = 0;
//        for (Feedback f: Data.getFeedbackList()) {
//            if (f.getAnswer() != null)
//                count++;
//        }
//
//        return count;
//    }
//}
