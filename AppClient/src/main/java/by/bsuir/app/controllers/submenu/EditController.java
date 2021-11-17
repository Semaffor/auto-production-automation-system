//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Employees;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//import sample.constansts.Data;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class EditController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextField login;
//
//    @FXML
//    private TextField mail;
//
//    @FXML
//    private TextField name;
//
//    @FXML
//    private TextField surname;
//
//    @FXML
//    private TextField thirdname;
//
//    @FXML
//    private TextField age;
//
//    @FXML
//    private ComboBox<String> gender;
//
//    @FXML
//    private TextField address;
//
//    @FXML
//    private TextField phone;
//
//    @FXML
//    private TextField social;
//
//    @FXML
//    private Button editButton;
//
//    @FXML
//    private Label war_label;
//
//    @FXML
//    private  Button closeButton;
//
//    @FXML
//    void closeHandler(MouseEvent event) {
//        war_label.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.add("Мужской");
//        observableList.add("Женский");
//
//        gender.setItems(observableList);
//
//        closeButton.setOnAction(actionEvent -> {
//            war_label.getScene().getWindow().hide();
//        });
//
//        editButton.setOnAction(actionEvent -> {
//            if (!login.getText().equals(""))
//                update("login", login.getText());
//            if (!mail.getText().equals(""))
//                update("email", mail.getText());
//            if (!name.getText().equals(""))
//                update("name", name.getText());
//            if (!surname.getText().equals(""))
//                update("surname", surname.getText());
//            if (!thirdname.getText().equals(""))
//                update("thirdname", thirdname.getText());
//            if (!address.getText().equals(""))
//                update("address", address.getText());
//            if (!phone.getText().equals(""))
//                update("phone", phone.getText());
//            if (!social.getText().equals(""))
//                update("social", social.getText());
//            if (gender.getValue() != null)
//                update("gender", gender.getValue());
//            if (!age.getText().equals("")) {
//                int ageI = Integer.parseInt(age.getText());
//                if (ageI < 16 || ageI > 100)
//                    war_label.setText("Некорректный возраст.");
//                else
//                    update("age", age.getText());
//            }
//            war_label.setVisible(true);
//        });
//    }
//
//    private void clearFields() {
//        login.clear();
//        mail.clear();
//        name.clear();
//        surname.clear();
//        age.clear();
//        address.clear();
//        phone.clear();
//        social.clear();
//    }
//
//    void update(String field, Object value) {
//        String update = "UPDATE Employees SET " + field + " = '" + value +
//                "' WHERE id ="  + Data.getEmpl().getId();
//
//        Phone.writeLine(update);
//        if (Phone.readLine().equals("GOOD")) {
//            clearFields();
//            war_label.setText("Данные изменены. Обновите страницу.");
//            Phone.writeLine("SELECT * FROM Employees WHERE id = " + Data.getEmpl().getId());
//            ArrayList<Employees> answer = (ArrayList<Employees>) Phone.readObject();
//            Data.setEmpl(new Employees(answer.get(0)));
//        }
//        else
//            war_label.setText("Ошибка. Изменения отменены.");
//
//        war_label.setVisible(true);
//    }
//}
