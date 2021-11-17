//package by.bsuir.app.controllers.submenu;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import com.bsuir.jdbc.TableNames;
//import javafx.fxml.FXML;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//import sample.constansts.MigrateData;
//
//public class TextFieldController {
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
//    private Label question_field;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        accept_button.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//        question_field.setText(MigrateData.question);
//
//        accept_button.setOnAction(actionEvent -> {
//
//            String answer = feedback_area.getText();
//
//            if (answer.length() < 10)
//                warning_label.setText("Минимальное кол-во символов - 10");
//            else {
//            String request = "UPDATE " + TableNames.FEEDBACK +
//                    " SET answer = '" + answer + "' " +
//                    " WHERE ID = " + MigrateData.num;
//
//            Phone.writeLine(request);
//            if (Phone.readLine().equals("GOOD"))
//                warning_label.setText("Ответ сохранен.");
//            else
//                warning_label.setText("Произошла ошибка.");
//            }
//
//            warning_label.setVisible(true);
//        });
//
//    }
//}
//
