//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Position;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//import sample.constansts.MigrateData;
//import sample.entities.EmployeesForTable;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class AddEmployeeController {
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
//    private ComboBox gender;
//
//    @FXML
//    private ComboBox position;
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
//    private Label war_label;
//
//    @FXML
//    private Label label;
//
//    @FXML
//    private Button addButton;
//
//    @FXML
//    void closeHandler(MouseEvent event) {
//        war_label.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//
//        Phone.writeLine("SELECT * FROM Positions");
//        ArrayList<Position> arrayList = (ArrayList<Position>) Phone.readObject();
//
//        ObservableList<String> positionOL = FXCollections.observableArrayList();
//        for (Position p : arrayList)
//            positionOL.add(p.getPositionName());
//        position.setItems(positionOL);
//
//        ObservableList<String> genderOL = FXCollections.observableArrayList();
//        genderOL.add("Мужской");
//        genderOL.add("Женский");
//        gender.setItems(genderOL);
//
//        addButton.setOnAction(actionEvent -> {
//            if (login.getText().equals("") || mail.getText().equals("")) {
//                war_label.setText("Заполните логин, почту, пол и должность.");
//                war_label.setVisible(true);
//            } else {
//                insert();
//                MigrateData.empl = new EmployeesForTable(
//                        0, login.getText(),
//                        mail.getText(),
//                        (String) gender.getValue(),
//                        Integer.parseInt(age.getText()),
//                        "0", "0", 0,
//                        name.getText(), surname.getText(), thirdname.getText(),
//                        (String) position.getValue());
//            }
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
//    private void insert() {
//        int ageI = 0;
//        if (!age.getText().equals(""))
//            Integer.parseInt(age.getText());
//
//        String pos = "SELECT * FROM Positions WHERE position_name = '" + position.getValue() + "'";
//        Phone.writeLine(pos);
//        ArrayList<Position> pp = (ArrayList<Position>) Phone.readObject();
//
//        String insert = "INSERT INTO Employees (login, email, gender, position_id, age, name, surname, thirdname) VALUES('" +
//                login.getText() + "', '" + mail.getText() + "', '" + (String) gender.getValue() + "', '" +
//                pp.get(0).getId() + "', " +
//                ageI + ", '" + name.getText() + "', '"+
//                surname.getText() + "', '" + thirdname.getText() + "')";
//        Phone.writeLine(insert);
//        if (Phone.readLine().equals("GOOD")) {
//            war_label.setText("Сотрудник добавлен.");
//            clearFields();
//        }
//        else
//            war_label.setText("Ошибка. Проверьте поля.");
//        war_label.setVisible(true);
//    }
//}
