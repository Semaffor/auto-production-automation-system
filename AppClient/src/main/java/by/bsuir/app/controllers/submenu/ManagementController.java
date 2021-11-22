package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.BigDecimalStringConverter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static by.bsuir.app.util.constants.Constants.*;

@Log4j2
public class ManagementController {

    @FXML
    private TableView<Account> account_table;

    @FXML
    private TableColumn<?, Integer> id_column;

    @FXML
    private TableColumn<?, String> login_column;

    @FXML
    private TableColumn<?, String> mail_column;

    @FXML
    private TableColumn<?, String> name_column;

    @FXML
    private TableColumn<?, String> role_column;

    @FXML
    private TableColumn<?, String> surname_column;

    @FXML
    private TableColumn<?, String> thirdName_column;

    @FXML
    private TableColumn<?, Integer> age_column;

    @FXML
    private TableColumn<?, String> gender_column;

    @FXML
    private TableColumn<?, String> position_column;

    @FXML
    private TableColumn<?, String> phone;

    @FXML
    private TableColumn<?, String> social;

    @FXML
    private TableColumn<?, String> start_column;

    @FXML
    private TableColumn<?, String> fire_column;

//    @FXML
//    private TextField delete_employee_field;

    @FXML
    private Button delete_account_button;

    @FXML
    private Label warning;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<?, String> VIN;

    @FXML
    private TableColumn<?, String> model_table_car_field;

    @FXML
    private TableColumn<?, String> body_typeTableCar;

    @FXML
    private TableColumn<?, String> fuel_typeCarTable;

    @FXML
    private TableColumn<?, BigDecimal> price;

    @FXML
    private TableColumn<?, String> gearbox;

    @FXML
    private TableColumn<?, BigDecimal> rate;

    @FXML
    private TableColumn<Car, Date> issuer_date;

    @FXML
    private TableColumn<?, Integer> car_quantity_column;

    @FXML
    private Button delete_car_button;

    @FXML
    private Label war_label;

    @FXML
    private Button add_car_button;

    @FXML
    private Label warningCar;

    @FXML
    private TableColumn<?, ?> seriesTableModel1;

    @FXML
    private TableColumn<?, ?> model_name_field;

    @FXML
    private TableColumn<?, ?> description_field;

    @FXML
    private TableColumn<?, ?> fuel_typeTableOthers;

    @FXML
    private TableColumn<?, ?> body_typeTableOthers;

    @FXML
    private TableColumn<?, ?> seats_numberTableOthers;

    @FXML
    private TextField filterField;

    @FXML
    void handleClose(MouseEvent event) {
        account_table.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

        bindDataInAccountTable();
        bindDataInCarTable();
        fillAccountTableWithFilteredData();
        fillCarTableWithFilteredData();

        add_car_button.setOnAction(actionEvent -> {
            GeneralFuncWindow.openNewScene(Paths.WindowAddCar);
            fillCarTableWithFilteredData();
        });
        delete_car_button.setOnAction(actionEvent -> {
            try {

                Car car = (Car) carTable.getSelectionModel().getSelectedItem();
                if (car == null) {
                    warningCar.setText(ERROR_SELECT_FIELD_MSG);
                } else {
                    Phone.sendOrGetData(Commands.DELETE_CAR_BY_VIN, car);
                    warningCar.setText(DELETE_SUCCESS_MSG);
                    fillCarTableWithFilteredData();
                }
            } catch (IOException | ClassNotFoundException | GettingDataException e) {
                warningCar.setText(DELETE_FAIL_MSG);
                log.error(e);
                e.printStackTrace();
            }
            warningCar.setVisible(true);
        });
        delete_account_button.setOnAction(actionEvent -> {
            try {
                Account account = (Account) account_table.getSelectionModel().getSelectedItem();
                if (account != null) {
                    Phone.sendOrGetData(Commands.DELETE_CAR_BY_VIN, account);
                    warningCar.setText(DELETE_SUCCESS_MSG);
                    fillCarTableWithFilteredData();
                } else {
                    warningCar.setText(ERROR_SELECT_FIELD_MSG);
                }
            } catch (IOException | ClassNotFoundException | GettingDataException e) {
                warningCar.setText(DELETE_FAIL_MSG);
                log.error(e);
                e.printStackTrace();
            }
            warningCar.setVisible(true);
        });
    }

    private void bindDataInAccountTable() {
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        login_column.setCellValueFactory(new PropertyValueFactory<>("login"));
        login_column.setCellFactory(TextFieldTableCell.forTableColumn());
        mail_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        mail_column.setCellFactory(TextFieldTableCell.forTableColumn());
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
//        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        phone.setCellFactory(TextFieldTableCell.forTableColumn());
//        social.setCellValueFactory(new PropertyValueFactory<>("social"));
//        social.setCellFactory(TextFieldTableCell.forTableColumn());
        role_column.setCellValueFactory(new PropertyValueFactory<>("role"));
        role_column.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void bindDataInCarTable() {
        VIN.setCellValueFactory(new PropertyValueFactory<>("VIN"));
//        model_table_car_field.setCellValueFactory(new PropertyValueFactory<>("model.name"));
//        model_table_car_field.setCellFactory(TextFieldTableCell.forTableColumn());
        body_typeTableCar.setCellValueFactory(new PropertyValueFactory<>("bodyType"));
        body_typeTableCar.setCellFactory(TextFieldTableCell.forTableColumn());
        issuer_date.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        issuer_date.setCellFactory(column -> {
            TableCell<Car, Date> cell = new TableCell<Car, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fuel_typeCarTable.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        fuel_typeCarTable.setCellFactory(TextFieldTableCell.forTableColumn());
        gearbox.setCellValueFactory(new PropertyValueFactory<>("gearbox"));
        gearbox.setCellFactory(TextFieldTableCell.forTableColumn());
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rate.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
//        car_quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        car_quantity_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void fillAccountTableWithFilteredData() {

        try {
            ObservableList<Account> ol_accounts = FXCollections.observableArrayList();

            List<Account> accounts = (List<Account>) Phone.sendOrGetData(Commands.GET_ALL_USERS, new Car());
            ol_accounts.addAll(accounts);

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Account> filteredData = new FilteredList<>(ol_accounts, b -> true);

            System.out.println(filteredData);
            // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(e -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    PersonalData data = e.getData();

                    if (e.getEmail() != null && e.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (e.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (e.getRole().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        if (data != null) {
                            if (data.getName() != null && data.getName().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (data.getSurname() != null && data.getSurname().toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getThirdName() != null && data.getThirdName().toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getGender() != null && data.getGender().toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getPhone() != null && data.getPhone().toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getPosition() != null && data.getPosition().getName() != null && data.getPosition().getName().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getEmplEndDate() != null && String.valueOf(
                                    data.getEmplEndDate()).toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else if (data.getEmplStartDate() != null && String.valueOf(
                                    data.getEmplStartDate()).toLowerCase().contains(
                                    lowerCaseFilter)) {
                                return true;
                            } else
                                return false; // Does not match.
                        }
                    }
                    return false;
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Account> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(account_table.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            account_table.setItems(sortedData);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    private void fillCarTableWithFilteredData() {
        try {
            ObservableList<Car> ol_cars = FXCollections.observableArrayList();

            List<Car> cars = (List<Car>) Phone.sendOrGetData(Commands.GET_ALL_CARS, new Car());
            ol_cars.addAll(cars);

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Car> filteredData = new FilteredList<>(ol_cars, b -> true);

            System.out.println(filteredData);
            // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(obj -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (obj.getBodyType() != null && obj.getBodyType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (obj.getFuelType() != null && obj.getFuelType().toLowerCase().contains(
                            lowerCaseFilter)) {
                        return true;
                    } else if (obj.getModel() != null && obj.getModel().getName() != null && obj.getModel().getName().toLowerCase().contains(
                            lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(obj.getPrice()).contains(lowerCaseFilter)) {
                        return true;
                    } else if (obj.getGearbox() != null && obj.getGearbox().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (obj.getIssueDate() != null && obj.getIssueDate().toString().toLowerCase().contains(
                            lowerCaseFilter)) {
                        return true;
                    } else if (obj.getModel() != null && obj.getModel().getQuantity() != 0 && String.valueOf(
                            obj.getModel().getQuantity()).toLowerCase().contains(
                            lowerCaseFilter)) {
                        return true;
                    } else
                        return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Car> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(carTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            carTable.setItems(sortedData);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    public void onEditPosition(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable employee = (EmployeesForTable) fillAccountTableWithFilteredData.getSelectionModel().getSelectedItem();
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

    }

    public void onEditLogin(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
//        EmployeesForTable employee = (EmployeesForTable) fillAccountTableWithFilteredData.getSelectionModel().getSelectedItem();
//        employee.setLogin(employeesForTableStringCellEditEvent.getNewValue());
//        //Phone.writeLine("UPD");
    }

    public void onEditMail(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
    }
}


