package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.enums.Gender;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

import static by.bsuir.app.util.constants.Constants.*;

@Log4j2
public class EditController {

    @FXML
    private TextField login_field;

    @FXML
    private TextField mail_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField thirdname_field;

    @FXML
    private ComboBox<Integer> age_box;

    @FXML
    private ComboBox<String> gender_box;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField social_field;

    @FXML
    private TextField email_field;

    @FXML
    private Button editButton;

    @FXML
    private Label war_label;

    @FXML
    private Button closeButton;


    @FXML
    void closeHandler(MouseEvent event) {
        war_label.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

        fillFieldsWithData();

        closeButton.setOnAction(actionEvent -> {
            war_label.getScene().getWindow().hide();
        });

        editButton.setOnAction(actionEvent -> {
            try {

                updateData();

            } catch (IOException | ClassNotFoundException | GettingDataException e) {
                log.error(e.getMessage());
                war_label.setText(EDITING_DATA_FAILURE);
                e.printStackTrace();
            }
            war_label.setVisible(true);
        });
    }

    void updateData() throws IOException, ClassNotFoundException, GettingDataException {

        Account account = LocalStorage.getAccount();

        if (!login_field.getText().isEmpty())
            account.setLogin(login_field.getText());
        if (!mail_field.getText().isEmpty())
            account.setEmail(mail_field.getText());
        if (!name_field.getText().isEmpty())
            account.getData().setName(name_field.getText());
        if (!surname_field.getText().isEmpty())
            account.getData().setSurname(surname_field.getText());
        if (!thirdname_field.getText().isEmpty())
            account.getData().setThirdName(thirdname_field.getText());
        if (!phone_field.getText().isEmpty())
            account.getData().setPhone(phone_field.getText());
        if (!social_field.getText().isEmpty())
            account.getData().setSocial(social_field.getText());
        if (gender_box.getValue() != null)
            account.getData().setGender(gender_box.getValue());
        if (age_box.getValue() != null)
            account.getData().setAge(age_box.getValue());

        System.out.println(account);
        Phone.sendOrGetData(Commands.USER_ADD_OR_UPDATE, account);
        System.out.println(account);
        war_label.setText(EDITING_DATA_SUCCESS_UPDATE);
    }

    void fillFieldsWithData() {
        ObservableList<String> genderBox = FXCollections.observableArrayList();
        for (Gender g : Gender.values())
            genderBox.add(g.toString());
        gender_box.setItems(genderBox);

        ObservableList<Integer> ageBox = FXCollections.observableArrayList();
        for (int i = 18; i < 100; i++)
            ageBox.add(i);
        age_box.setItems(ageBox);

        login_field.setText(LocalStorage.getAccount().getLogin());

        try {
            Account account = (Account) Phone.sendOrGetData(Commands.GET_USER_BY_LOGIN,
                    LocalStorage.getAccount().getLogin());

            LocalStorage.setAccount(account);

            String sex = account.getData().getGender();
            if (sex != null && sex.equals(Gender.MALE.toString()))
                gender_box.getSelectionModel().select(Gender.MALE.ordinal());
            else if (sex != null && sex.equals(Gender.FEMALE.toString()))
                gender_box.getSelectionModel().select(Gender.FEMALE.ordinal());
            else
                gender_box.getSelectionModel().select(Gender.UNDEFINED.ordinal());

            String emailString = account.getEmail();
            if (emailString != null) {
                mail_field.setText(emailString);
            }

            String firstName = account.getData().getName();
            if (firstName != null) {
                name_field.setText(firstName);
            }

            String surnameString = account.getData().getSurname();
            if (surnameString != null) {
                surname_field.setText(surnameString);
            }

            String thirdName = account.getData().getThirdName();
            if (thirdName != null) {
                thirdname_field.setText(thirdName);
            }

            String phone = account.getData().getPhone();
            if (phone != null)
                phone_field.setText(phone);

            String social = account.getData().getSocial();
            if (social != null)
                social_field.setText(social);

            int age = account.getData().getAge();
            if (age != 0)
                age_box.getSelectionModel().select(age - 18);

        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            log.error(e.getMessage());
            war_label.setText(e.getMessage());
            war_label.setVisible(true);
            e.printStackTrace();
        }
    }
}
