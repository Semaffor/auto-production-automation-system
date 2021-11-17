//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Employees;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import javafx.collections.transformation.SortedList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.input.MouseEvent;
//import javafx.util.converter.IntegerStringConverter;
//import sample.clientConnection.Phone;
//import sample.constansts.MigrateData;
//import sample.constansts.Windows;
//import sample.entities.EmployeesForTable;
//import sample.services.GeneralFuncWindow;
//
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//public class ManagementAccountantController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableView<Employees> employee_table;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> ID;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> login_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> mail_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> name_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> surname_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> thirdName_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> age_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> gender_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> position_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> address;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> phone;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> social;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> start_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> fire_column;
//
//    @FXML
//    private TextField filterField;
//
//    @FXML
//    private Button buttonAdd;
//
//    @FXML
//    private Button buttonDelete;
//
//    @FXML
//    private Label war_label;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        war_label.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//
//        bindColumns();
//        refreshTable();
//
//        buttonAdd.setOnAction(actionEvent -> {
//            GeneralFuncWindow.openNewScene(Windows.WindowAddEmployee);
//            employee_table.getItems().add(MigrateData.empl);
//            refreshTable();
//        });
//
//        buttonDelete.setOnAction(actionEvent -> {
//            Employees ep = employee_table.getSelectionModel().getSelectedItem();
//            if (ep == null) {
//                war_label.setText("Ошибка. Выберите сотрудника.");
//            } else {
//                if (!ep.getEndDate().equals(""))
//                    war_label.setText("Сотрудник уже уволен.");
//                else {
//                    Phone.writeLine("UPDATE Employees SET employment_end_date = getdate() WHERE id = " +
//                            ep.getId());
//                    if (Phone.readLine().equals("GOOD")) {
//                        war_label.setText("Сотрудник уволен. Данные обновлены.");
//                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                        Date date = new Date();
//                        ep.setEndDate(String.valueOf(date));
//                    } else
//                        war_label.setText("Ошибка.");
//                }
//            }
//            war_label.setVisible(true);
//            refreshTable();
//        });
//
//
//    }
//
//    private void filter() {
//        FilteredList<EmployeesForTable> filteredData = new FilteredList<>(observeValues(), b -> true);
//
//        System.out.println(filteredData);
//        // 2. Set the filter Predicate whenever the filter changes.
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(employees -> {
//                // If filter text is empty, display all persons.
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (employees.getName() != null && employees.getName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (employees.getSurname() != null && employees.getSurname().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (employees.getThirdname() != null && employees.getThirdname().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (employees.getStartDate() != null && employees.getStartDate().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (employees.getEndDate() != null && employees.getEndDate().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (employees.getGender().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (employees.getEmail().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (employees.getLogin().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (String.valueOf(employees.getAge()).contains(lowerCaseFilter))
//                    return true;
//                else
//                    return false; // Does not match.
//            });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<Employees> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind((ObservableValue<? extends Comparator<? super Employees>>) employee_table.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        employee_table.setItems(sortedData);
//    }
//
//    private void refreshTable() {
//        employee_table.setItems(observeValues());
//        filter();
//    }
//    private void bindColumns() {
//        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        login_column.setCellValueFactory(new PropertyValueFactory<>("login"));
//        login_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        mail_column.setCellValueFactory(new PropertyValueFactory<>("email"));
//        mail_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        gender_column.setCellValueFactory(new PropertyValueFactory<>("gender"));
//        gender_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        age_column.setCellValueFactory(new PropertyValueFactory<>("age"));
//        age_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        start_column.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//        start_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        fire_column.setCellValueFactory(new PropertyValueFactory<>("endDate"));
//        fire_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        position_column.setCellValueFactory(new PropertyValueFactory<>("position"));
//        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
//        name_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        surname_column.setCellValueFactory(new PropertyValueFactory<>("surname"));
//        surname_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        thirdName_column.setCellValueFactory(new PropertyValueFactory<>("thirdname"));
//        thirdName_column.setCellFactory(TextFieldTableCell.forTableColumn());
//        address.setCellValueFactory(new PropertyValueFactory<>("address"));
//        address.setCellFactory(TextFieldTableCell.forTableColumn());
//        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        social.setCellValueFactory(new PropertyValueFactory<>("social"));
//        social.setCellFactory(TextFieldTableCell.forTableColumn());
//    }
//
//    private ObservableList observeValues() {
//
//        ObservableList<? super Employees> obList = FXCollections.observableArrayList();
//
//        Phone.writeLine("SELECT * FROM Employees");
//        ArrayList<EmployeesForTable> l_list = (ArrayList<EmployeesForTable>) Phone.readObject();
//
//        {
//            for (Employees empl : l_list)
//                obList.add(new EmployeesForTable(empl));
//        }
//
//        return obList;
//    }
//
//
//    public void onEditMail(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setEmail(employeesForTableStringCellEditEvent.getNewValue());
//        editInDB("email", e.getEmail(), e.getId());
//    }
//
//    public void onEditName(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setName(employeesForTableStringCellEditEvent.getNewValue());
//        editInDB("name", e.getName(), e.getId());
//    }
//
//    public void onEditSurname(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setSurname(employeesForTableStringCellEditEvent.getNewValue());
//        editInDB("surname", e.getSurname(), e.getId());
//    }
//
//    public void inEditAge(TableColumn.CellEditEvent<EmployeesForTable, Integer> employeesForTableIntegerCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setAge(employeesForTableIntegerCellEditEvent.getNewValue());
//        editInDB("age", e.getAge(), e.getId());
//    }
//
//    public void onEditGender(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setGender(employeesForTableStringCellEditEvent.getNewValue());
//        editInDB("gender", e.getGender(), e.getId());
//    }
//
//
//    public void onEditPosition(TableColumn.CellEditEvent<EmployeesForTable, Integer> employeesForTableIntegerCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setPosition_id(employeesForTableIntegerCellEditEvent.getNewValue());
//        System.out.println(e.getPosition());
//        System.out.println(e.getGender());
//        editInDB("position_id", e.getGender(), e.getId());
//    }
//
//    public void onEditLogin(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable e = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        e.setLogin(employeesForTableStringCellEditEvent.getNewValue());
//        System.out.println(e.getLogin());
//        editInDB("login", e.getLogin(), e.getId());
//
//    }
//
//    private void editInDB(String msg, Object obj, int id) {
//        String update = "UPDATE Employees SET " + msg + " = '" + obj + "' WHERE id = " + id;
//        Phone.writeLine(update);
//        if (Phone.readLine().equals("GOOD"))
//            war_label.setText("Данные изменены.");
//        else
//            war_label.setText("Ошибка. Данные не изменились.");
//        war_label.setVisible(true);
//    }
//}
