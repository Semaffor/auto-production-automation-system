//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Employees;
//import com.bsuir.mail.JavaMailUtil;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//import sample.entities.EmployeesForTable;
//import sample.services.GeneralFuncWindow;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class MailSenderController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextArea text_field;
//
//    @FXML
//    private Button send_button;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    private TableView<EmployeesForTable> tableView;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> ID;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> firstName;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> secondName;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> thirdName;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> position;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> mail;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//
//        final EmployeesForTable[] eft = {null};
//
//        loadTableData();
//
//        tableView.setOnMouseClicked(mouseEvent -> {
//            eft[0] = tableView.getSelectionModel().getSelectedItem();
//        });
//
//        send_button.setOnAction(actionEvent -> {
//            String msg = text_field.getText();
//
//            if (msg.equals("") || msg.length() < 10) {
//                warning_label.setText("Нельзя отправить пустое письмо.");
//            } else {
//                if (eft[0] != null) {
//                    warning_label.setText("Отправка...");
//                    warning_label.setVisible(true);
//                    new Thread(() -> {
//                        JavaMailUtil.send(eft[0].getEmail(), "Уведомление", msg);
//                    }).start();
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    warning_label.setText("Успешно отправлено.");
//                }
//                else {
//                    warning_label.setText("Выберите, кому хотите отправить письмо.");
//                }
//            }
//            warning_label.setVisible(true);
//        });
//    }
//
//    private void loadTableData() {
//        ID.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, Integer>("id"));
//        firstName.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, String>("name"));
//        secondName.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, String>("surname"));
//        thirdName.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, String>("thirdname"));
//        position.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, String>("position"));
//        mail.setCellValueFactory(new PropertyValueFactory<EmployeesForTable, String>("email"));
//
//        tableView.setItems(observeValues());
//    }
//    private ObservableList observeValues() {
//
//        ObservableList<? super Employees> obList = FXCollections.observableArrayList();
//
//        Phone.writeLine("SELECT * FROM Employees");
//        ArrayList<Employees> l_list = (ArrayList<Employees>) Phone.readObject();
//
//        {
//            for (Employees empl: l_list)
//                obList.add(new EmployeesForTable(empl));
//        }
//
//        return obList;
//    }
//}
