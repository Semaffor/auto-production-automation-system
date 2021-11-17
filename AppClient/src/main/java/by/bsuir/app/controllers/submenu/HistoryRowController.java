//package by.bsuir.app.controllers.submenu;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import sample.constansts.Data;
//public class HistoryRowController extends Data{
//
//    @FXML
//    private Label id_label;
//
//    @FXML
//    private Label question_field;
//
//    @FXML
//    private Label answer_field;
//
//    @FXML
//    private Label request_date_field;
//
//    @FXML
//    private Label answer_date_field;
//
//    @FXML
//    void initialize() {
//
//        id_label.setText(String.valueOf(Data.getFeedbackList().get(0).getId()));
//        question_field.setText(Data.getFeedbackList().get(0).getQuestion());
//        answer_field.setText(Data.getFeedbackList().get(0).getAnswer());
//        request_date_field.setText(Data.getFeedbackList().get(0).getQuestion_date());
//        answer_date_field.setText(Data.getFeedbackList().get(0).getAnswer_date());
//        Data.getFeedbackList().remove(0);
//        System.out.println(Data.getFeedbackList());
//    }
//
//}
//
//
