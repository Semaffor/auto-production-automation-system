//package by.bsuir.app.controllers.submenu;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//import sample.constansts.Data;
//import sample.services.GeneralFuncWindow;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class FeedbackController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextArea feedback_area;
//
//    @FXML
//    private Button accept_button;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//        accept_button.setOnAction(actionEvent -> {
//
//            String feedback = feedback_area.getText();
//
//            if (feedback.length() < 20) {
//                warning_label.setText("Минимальное количество символов - 20. Опишите поподробнее.");
//            } else {
//                String request = "INSERT INTO Feedback (sender_id, question) " +
//                        "VALUES(" + Data.getEmpl().getId() + ", '" + feedback + "' )";
//
//                Phone.writeLine(request);
//                if (Phone.readLine().equals("GOOD")) {
//                    warning_label.setText("Ваше обращение отправлено.");
//                    feedback_area.setText("");
//                }
//                else
//                    warning_label.setText("Ошибка отправки сообщения. Повторите попытку позже.");
//            }
//            warning_label.setVisible(true);
//        });
//
//    }
//}
