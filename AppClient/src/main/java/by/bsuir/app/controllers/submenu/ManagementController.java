package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.entity.enums.*;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.DateHandler;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.Paths;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
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
    private TableColumn<Account, String> name_column;

    @FXML
    private TableColumn<Account, String> role_column;

    @FXML
    private TableColumn<Account, String> surname_column;

    @FXML
    private TableColumn<Account, String> thirdName_column;

    @FXML
    private TableColumn<Account, Integer> age_column;

    @FXML
    private TableColumn<Account, String> gender_column;

    @FXML
    private TableColumn<Account, String> phone;

    @FXML
    private TableColumn<Account, String> social;

    @FXML
    private TableColumn<Account, Boolean> account_ban;

    @FXML
    private Button delete_account_button;

    @FXML
    private Label warning_account_label;

    @FXML
    private TableView<Car> car_table;

    @FXML
    private TableColumn<?, String> VIN;

    @FXML
    private TableColumn<Car, String> model_table_car_field;

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
    private Label war_label;

    @FXML
    private Button add_car_button;

    @FXML
    private Button account_reset_button;

    @FXML
    private Label car_warning_label;

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

    private List<Account> accounts;
    @FXML
    void initialize() {

        updateAccountTable();
        bindDataInCarTable();
        fillCarTableWithFilteredData();

    }



    void bindDataInAccountTable() {
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        login_column.setCellValueFactory(new PropertyValueFactory<>("login"));
        login_column.setCellFactory(TextFieldTableCell.forTableColumn());
        mail_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        mail_column.setCellFactory(TextFieldTableCell.forTableColumn());
        gender_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getGender());
            }
        });
        gender_column.setCellFactory(TextFieldTableCell.forTableColumn());
        age_column.setCellValueFactory(new PropertyValueFactory<>("age"));
        age_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, Integer>,
                ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Account, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getAge());
            }
        });
        age_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        name_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getName());
            }
        });
        name_column.setCellFactory(TextFieldTableCell.forTableColumn());
        surname_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getSurname());
            }
        });
        surname_column.setCellFactory(TextFieldTableCell.forTableColumn());
        thirdName_column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getThirdName());
            }
        });
        thirdName_column.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getPhone());
            }
        });
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        social.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getData().getSocial());
            }
        });
        social.setCellFactory(TextFieldTableCell.forTableColumn());
        role_column.setCellValueFactory(new PropertyValueFactory<>("role"));
        role_column.setCellFactory(TextFieldTableCell.forTableColumn());
        account_ban.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isBlocked()));
        account_ban.setCellFactory(tc -> new CheckBoxTableCell<>());
    }

    void bindDataInCarTable() {
        VIN.setCellValueFactory(new PropertyValueFactory<>("VIN"));
        model_table_car_field.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getModel().getName());
            }
        });

        model_table_car_field.setCellFactory(TextFieldTableCell.forTableColumn());
        body_typeTableCar.setCellValueFactory(new PropertyValueFactory<>("bodyType"));
        body_typeTableCar.setCellFactory(TextFieldTableCell.forTableColumn());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


        issuer_date.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        issuer_date.setCellFactory(column -> {
            TableCell<Car, Date> cell = new TableCell<Car, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        issuer_date.setCellFactory(TextFieldTableCell.forTableColumn(new FormatStringConverter<>(format)));

        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        fuel_typeCarTable.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        fuel_typeCarTable.setCellFactory(TextFieldTableCell.forTableColumn());
        gearbox.setCellValueFactory(new PropertyValueFactory<>("gearbox"));
        gearbox.setCellFactory(TextFieldTableCell.forTableColumn());
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        rate.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

    }

    void fillAccountTableWithFilteredData() {

        try {
            ObservableList<Account> ol_accounts = FXCollections.observableArrayList();

            accounts = (List<Account>) Phone.sendOrGetData(Commands.GET_ALL_USERS, new Car());
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

    void fillCarTableWithFilteredData() {
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
            sortedData.comparatorProperty().bind(car_table.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            car_table.setItems(sortedData);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    void updateCarTable() {
        bindDataInCarTable();
        fillCarTableWithFilteredData();
    }

    void updateAccountTable() {
        bindDataInAccountTable();
        fillAccountTableWithFilteredData();
    }

    boolean sendEditedData(Account account) {
        try {
            Phone.sendOrGetData(Commands.USER_ADD_OR_UPDATE, account);
            updateAccountTable();
            warning_account_label.setText(EDITING_DATA_SUCCESS);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            warning_account_label.setText(EDITING_DATA_FAILURE);
            log.error(e);
            e.printStackTrace();
            return false;
        }
        warning_account_label.setVisible(true);
        return true;
    }

    boolean sendEditedData(Car car) {
        try {
            Phone.sendOrGetData(Commands.CAR_ADD_OR_UPDATE, car);
            updateCarTable();
            car_warning_label.setText(EDITING_DATA_SUCCESS);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            car_warning_label.setText(EDITING_DATA_FAILURE);
            log.error(e);
            e.printStackTrace();
            return false;
        }
        car_warning_label.setVisible(true);
        return true;
    }

    @FXML
    void onEditMail(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.setEmail(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditIssueDate(TableColumn.CellEditEvent<Account, String> event) {
        try {
            Car car = (Car) car_table.getSelectionModel().getSelectedItem();
            String oldDate = String.valueOf(event.getNewValue());
            Date newDateString = DateHandler.convertDateFormatInSqlDate(oldDate);

            car.setIssueDate(newDateString);
            sendEditedData(car);
        } catch (IllegalArgumentException | ParseException e) {
            car_warning_label.setText(INCORRECT_DATE_FORMAT_MSG);
            car_warning_label.setVisible(true);
        }
    }

    @FXML
    void onEditLogin(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.setLogin(newValue);
        sendEditedData(account);
    }


    @FXML
    void onMouseClickBackCar(MouseEvent event) {
        try {
            Phone.sendOrGetData(Commands.RESTORE_CAR_DATA_LOCAL_STORAGE, new Car());
            updateCarTable();
            car_warning_label.setText(LAST_RESTORE_POINT_MSG);
            car_warning_label.setVisible(true);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseClickDeleteAccount(MouseEvent event) {
        try {
            Account account = (Account) account_table.getSelectionModel().getSelectedItem();
            if (account != null) {
                Phone.sendOrGetData(Commands.DELETE_USER_BY_ID, account.getId());
                warning_account_label.setText(DELETE_SUCCESS_MSG);
                updateAccountTable();
            } else {
                warning_account_label.setText(ERROR_SELECT_FIELD_MSG);
            }
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            warning_account_label.setText(DELETE_FAIL_MSG);
            log.error(e);
            e.printStackTrace();
        }
        warning_account_label.setVisible(true);
    }

    @FXML
    void onMouseClickSaveCar(MouseEvent event) {
        try {
            Phone.sendOrGetData(Commands.SAVE_CAR_DATA_LOCAL_STORAGE, new Car());
            car_warning_label.setText(CREATED_RESTORE_POINT_MSG);
            car_warning_label.setVisible(true);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseClickResetAccount(MouseEvent event) {
        warning_account_label.setVisible(false);
        updateAccountTable();
    }

    @FXML
    void onMouseClickAddCar(MouseEvent event) {
        GeneralFuncWindow.openNewScene(Paths.WindowAddCar);
        fillCarTableWithFilteredData();
    }

    @FXML
    void onMouseClickDeleteCar(MouseEvent event) {
        try {
            Car car = (Car) car_table.getSelectionModel().getSelectedItem();
            if (car == null) {
                car_warning_label.setText(ERROR_SELECT_FIELD_MSG);
            } else {
                Phone.sendOrGetData(Commands.DELETE_CAR_BY_VIN, car);
                car_warning_label.setText(DELETE_SUCCESS_MSG);
                fillCarTableWithFilteredData();
            }
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            car_warning_label.setText(DELETE_FAIL_MSG);
            log.error(e);
            e.printStackTrace();
        }
        car_warning_label.setVisible(true);
    }

    @FXML
    void onMouseClickResetCar(MouseEvent event) {
        car_warning_label.setVisible(false);
        updateCarTable();
    }

    @FXML
    void onEditAge(TableColumn.CellEditEvent<Account, Integer> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        int newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setAge(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditGender(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();
        for (Gender p : Gender.values()) {
            if (newValue.equals(p.getGender())) {
                account.getData().setGender(p.getGender());
                sendEditedData(account);
                break;
            } else {
                warning_account_label.setText(EDITING_DATA_FAILURE);
                warning_account_label.setVisible(true);
            }
        }
    }

    @FXML
    void onEditName(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setName(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditPhone(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setPhone(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditRole(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();
        for (Role p : Role.values()) {
            if (newValue.equals(p.toString())) {
                account.setRole(p.toString());
                sendEditedData(account);
                break;
            } else {
                warning_account_label.setText(EDITING_DATA_FAILURE);
                warning_account_label.setVisible(true);
            }
        }
    }

    @FXML
    void onEditSurname(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setSurname(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditThirdName(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent) {
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setThirdName(newValue);
        sendEditedData(account);
    }

    @FXML
    void onEditVIN(TableColumn.CellEditEvent<Car, String> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        String newValue = carForTableStringCellEditEvent.getNewValue();

        car.setVIN(newValue);
        sendEditedData(car);
    }

    @FXML
    void onEditBodyType(TableColumn.CellEditEvent<Car, String> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        String newValue = carForTableStringCellEditEvent.getNewValue();

        for (BodyType p : BodyType.values()) {
            if (newValue.equals(p.getBodyType())) {
                car.setBodyType(p.getBodyType());
                sendEditedData(car);
                break;
            } else {
                warning_account_label.setText(EDITING_DATA_FAILURE);
                warning_account_label.setVisible(true);
            }
        }
    }

    @FXML
    void onEditFuelType(TableColumn.CellEditEvent<Car, String> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        String newValue = carForTableStringCellEditEvent.getNewValue();

        for (FuelType p : FuelType.values()) {
            if (newValue.equals(p.getRusName())) {
                car.setFuelType(p.getRusName());
                sendEditedData(car);
                break;
            } else {
                warning_account_label.setText(EDITING_DATA_FAILURE);
                warning_account_label.setVisible(true);
            }
        }
    }

    @FXML
    void onEditPrice(TableColumn.CellEditEvent<Car, BigDecimal> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        BigDecimal newValue = carForTableStringCellEditEvent.getNewValue();

        car.setPrice(newValue);
        sendEditedData(car);
    }
    
    @FXML
    void onEditGearbox(TableColumn.CellEditEvent<Car, String> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        String newValue = carForTableStringCellEditEvent.getNewValue();

        for (GearBox p : GearBox.values()) {
            if (newValue.equals(p.getRusName())) {
                car.setGearbox(p.getRusName());
                sendEditedData(car);
                break;
            } else {
                warning_account_label.setText(EDITING_DATA_FAILURE);
                warning_account_label.setVisible(true);
            }
        }
    }

    @FXML
    void onEditRate(TableColumn.CellEditEvent<Car, BigDecimal> carForTableStringCellEditEvent) {
        Car car = (Car) car_table.getSelectionModel().getSelectedItem();
        BigDecimal newValue = carForTableStringCellEditEvent.getNewValue();

        car.setRate(newValue);
        sendEditedData(car);
    }

    @FXML
    void onMouseClickBlockAccount(MouseEvent event) {
        Account account = account_table.getSelectionModel().getSelectedItem();

        account.setBlocked(!account.isBlocked());
        sendEditedData(account);
//        int i = 0;
//        for (Account bean: data) {
//            if (bean.isBlocked() != accounts.get(i).isBlocked()) {
//                bean.setBlocked(!bean.isBlocked());
//                sendEditedData(bean);
//            }
//            i++;
//        }
    }

    @FXML
    void onEditSocial(TableColumn.CellEditEvent<Account, String> employeesForTableStringCellEditEvent){
        Account account = (Account) account_table.getSelectionModel().getSelectedItem();
        String newValue = employeesForTableStringCellEditEvent.getNewValue();

        account.getData().setSocial(newValue);
        sendEditedData(account);
    }

}


