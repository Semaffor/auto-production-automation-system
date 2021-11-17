//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Car;
//import com.bsuir.entities.Employees;
//import com.bsuir.jdbc.TableNames;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import javafx.collections.transformation.SortedList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.input.MouseEvent;
//import javafx.util.converter.DoubleStringConverter;
//import javafx.util.converter.IntegerStringConverter;
//import sample.clientConnection.Phone;
//import sample.constansts.Windows;
//import sample.entities.CarForTable;
//import sample.entities.EmployeesForTable;
//import sample.services.GeneralFuncWindow;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class ManagementController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableView<EmployeesForTable> employee_table;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> id_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> login_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> mail_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> gender_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, Integer> age_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> start_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> fire_column;
//
//    @FXML
//    private TableColumn<EmployeesForTable, String> position_column;
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
//    private TableColumn<EmployeesForTable, String> email;
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
//    //CAR_TABLE
//    @FXML
//    private TableView<CarForTable> carTable;
//
//    @FXML
//    private TableColumn<CarForTable, String> VIN;
//
//    @FXML
//    private TextField delete_employee_field;
//
//    @FXML
//    private Button delete_employee_button;
//
//    @FXML
//    private TableColumn<CarForTable, String> modelTableCar;
//
//    @FXML
//    private TableColumn<CarForTable, String> seriesTableCar;
//
//    @FXML
//    private TableColumn<CarForTable, String> body_typeTableCar;
//
//    @FXML
//    private TableColumn<CarForTable, String> fuel_typeCarTable;
//    @FXML
//    private TableColumn<CarForTable, String> issuer_date;
//
//    @FXML
//    private TableColumn<CarForTable, Double> price;
//
//    @FXML
//    private TableColumn<CarForTable, String> responsible;
//
//    @FXML
//    private TableColumn<CarForTable, String> gearbox;
//
//    @FXML
//    private TableColumn<CarForTable, Double> rate;
//
//    @FXML
//    private TableColumn<CarForTable, String> description;
//
//
//    @FXML
//    private Button addButton;
//
//    @FXML
//    private Button deleteButton;
//
//    @FXML
//    private TextField vinField;
//
//    @FXML
//    private TableColumn<?, ?> series;
//
//    @FXML
//    private TableColumn<?, ?> fuel_type;
//
//    @FXML
//    private TableColumn<?, ?> seats_number;
//
//    @FXML
//    private Label warning;
//
//
//    @FXML
//    private Label warningCar;
//
//    @FXML
//    private TextField filterField;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        employee_table.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//
//        bindEmployeeTable();
//        bindCarTable();
//        employee_table();
//        car_table();
//
//        addButton.setOnAction(actionEvent -> {
//            GeneralFuncWindow.openNewScene(Windows.WindowAddCar);
//            car_table();
//        });
//
//        deleteButton.setOnAction(actionEvent -> {
//            CarForTable car = (CarForTable) carTable.getSelectionModel().getSelectedItem();
//            if (car == null) {
//                warningCar.setText("Ошибка. Выберите автомобиль.");
//            } else {
//                Phone.writeLine("DELETE " + TableNames.CAR +
//                        " WHERE VIN = '" + car.getVIN() + "' ");
//
//                if (Phone.readLine().equals("GOOD")) {
//                    warningCar.setText("Автомобиль удален.");
//                } else {
//                    warningCar.setText("Ошибка. Попробуйте позже.");
//                }
//                warningCar.setVisible(true);
//                car_table();
//            }
//        });
//        delete_employee_button.setOnAction(actionEvent -> {
//            int id;
//            try {
//                id = Integer.parseInt(delete_employee_field.getText());
//
//                Phone.writeLine("DELETE " + TableNames.EMPLOYEES +
//                        " WHERE id = " + id);
//
//                if (Phone.readLine().equals("GOOD")) {
//                    warning.setText("Учетная запись удалена.");
//                } else {
//                    warning.setText("Ошибка. Возможен некорректный ид.");
//                }
//                warning.setVisible(true);
//
//            } catch (NumberFormatException e) {
//                warning.setText("Введите ид работника.");
//            }
//            employee_table();
//        });
//    }
//
//    private void bindEmployeeTable() {
//        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
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
//        position_column.setCellFactory(TextFieldTableCell.forTableColumn());
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
//    private void employee_table() {
//
//        ObservableList<EmployeesForTable> list = FXCollections.observableArrayList();
//
//        Phone.writeLine("SELECT * FROM Employees");
//        ArrayList<Employees> l_list = (ArrayList<Employees>) Phone.readObject();
//
//        {
//            for (Employees empl: l_list)
//                list.add(new EmployeesForTable(empl));
//        }
//
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<EmployeesForTable> filteredData = new FilteredList<>(list, b -> true);
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
//        SortedList<EmployeesForTable> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(employee_table.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        employee_table.setItems(sortedData);
//    }
//
//    private void bindCarTable() {
//        VIN.setCellValueFactory(new PropertyValueFactory<>("VIN"));
//        modelTableCar.setCellValueFactory(new PropertyValueFactory<>("model"));
//        modelTableCar.setCellFactory(TextFieldTableCell.forTableColumn());
//        seriesTableCar.setCellValueFactory(new PropertyValueFactory<>("series"));
//        seriesTableCar.setCellFactory(TextFieldTableCell.forTableColumn());
//        body_typeTableCar.setCellValueFactory(new PropertyValueFactory<>("body_type"));
//        body_typeTableCar.setCellFactory(TextFieldTableCell.forTableColumn());
//        issuer_date.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
//        issuer_date.setCellFactory(TextFieldTableCell.forTableColumn());
//        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        fuel_typeCarTable.setCellValueFactory(new PropertyValueFactory<>("fuel_type"));
//        fuel_typeCarTable.setCellFactory(TextFieldTableCell.forTableColumn());
//        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//        responsible.setCellValueFactory(new PropertyValueFactory<>("responsible"));
//        responsible.setCellFactory(TextFieldTableCell.forTableColumn());
//        gearbox.setCellValueFactory(new PropertyValueFactory<>("gearbox"));
//        gearbox.setCellFactory(TextFieldTableCell.forTableColumn());
//        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
//        rate.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        description.setCellFactory(TextFieldTableCell.forTableColumn());
//    }
//    void car_table() {
//        ObservableList<CarForTable> list = FXCollections.observableArrayList();
//
//        Phone.writeLine("SELECT * FROM " + TableNames.CAR);
//
//        {
//            ArrayList<Car> l_list = (ArrayList<Car>) Phone.readObject();
//
//            for (Car car : l_list)
//                list.add(new CarForTable(car));
//        }
//
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<CarForTable> filteredData = new FilteredList<>(list, b -> true);
//
//        System.out.println(filteredData);
//        // 2. Set the filter Predicate whenever the filter changes.
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(obj -> {
//                // If filter text is empty, display all persons.
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (obj.getBody_type() != null && obj.getBody_type().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (obj.getFuel_type() != null && obj.getFuel_type().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                } else if (obj.getResponsible() != null && obj.getResponsible().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (obj.getModel() != null && obj.getModel().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (obj.getPrice() != 0 && String.valueOf(obj.getPrice()).contains(lowerCaseFilter)) {
//                    return true;
//                } else if (obj.getGearbox() != null && obj.getGearbox().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (obj.getIssue_date() != null && obj.getIssue_date().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (obj.getDescription() != null && obj.getDescription().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else
//                    return false; // Does not match.
//            });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<CarForTable> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(carTable.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        carTable.setItems(sortedData);
//    }
//
//    public void onEditPosition(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable employee = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        String newValue = employeesForTableStringCellEditEvent.getNewValue();
//        boolean isCorrect = false;
//        if (newValue.equals("Администратор")) {
//            employee.setPosition_id(2);
//            isCorrect = true;
//        }
//        else if (newValue.equals("Инженер")) {
//            employee.setPosition_id(4);
//            isCorrect = true;
//        }
//        else if (newValue.equals("Бухгалтер")) {
//            employee.setPosition_id(6);
//            isCorrect = true;
//        }
//        if (isCorrect)
//            Phone.writeLine("UPDATE " + TableNames.EMPLOYEES +
//                    " SET position_id = " + employee.getPosition_id() +
//                    " WHERE id = " + employee.getId());
//
//    }
//    public void onEditLogin(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable employee = (EmployeesForTable) employee_table.getSelectionModel().getSelectedItem();
//        employee.setLogin(employeesForTableStringCellEditEvent.getNewValue());
//        //Phone.writeLine("UPD");
//    }
//    public void onEditMail(TableColumn.CellEditEvent<EmployeesForTable, String> employeesForTableStringCellEditEvent) {
//    }
//}
//
//
