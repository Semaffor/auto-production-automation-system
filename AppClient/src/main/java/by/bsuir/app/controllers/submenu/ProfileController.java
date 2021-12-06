package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Position;
import by.bsuir.app.entity.enums.Gender;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import by.bsuir.app.util.constants.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

@Log4j2
public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image_box;

    @FXML
    private Label login_label;

    @FXML
    private Label mail_label;

    @FXML
    private Label sex_label;

    @FXML
    private Label age_label;

    @FXML
    private Label name_label;

    @FXML
    private Label surname_label;

    @FXML
    private Label third_name_label;

    @FXML
    private Label position_label;

    @FXML
    private Label start_date;

    @FXML
    private Label end_date;

    @FXML
    private Label adress_label;

    @FXML
    private Label phone_label;

    @FXML
    private Label social_label;

    private static final String DATE_PATTERN = "dd-MM-yyyy";

    @FXML
    void handleClose(MouseEvent event) {
        GeneralFuncWindow.closeApplication();
    }

    @FXML
    void initialize() {
        updateData();
    }

    void updateData() {
        try {
            Account responseAccount = (Account) Phone.sendOrGetData(Commands.GET_USER_BY_LOGIN,
                    LocalStorage.getAccount().getLogin());

            LocalStorage.setAccount(responseAccount);

            Account account = LocalStorage.getAccount();

            login_label.setText(account.getLogin());
            mail_label.setText(account.getEmail());

            String sex = account.getData().getGender();
            if (sex != null && sex.equals(Gender.MALE.getGender()))
                image_box.setImage(new Image(new File(Paths.MAN_AVATAR_PATH).toURI().toString()));
            else if (sex != null && sex.equals(Gender.FEMALE.getGender()))
                image_box.setImage(new Image(new File(Paths.WOMAN_AVATAR_PATH).toURI().toString()));
            else
                image_box.setImage(new Image(new File(Paths.UNDEFINED_AVATAR_PATH).toURI().toString()));

            if (sex != null)
                sex_label.setText(sex);

            Position position = account.getData().getPosition();
            if (position != null && position.getName() != null)
                position_label.setText(position.getName());

            String name = account.getData().getName();
            if (name != null) {
                name_label.setText(name);
            }

            String surname = account.getData().getSurname();
            if (surname != null) {
                surname_label.setText(surname);
            }

            String thirdName = account.getData().getThirdName();
            if (thirdName != null) {
                third_name_label.setText(thirdName);
            }

            String phone = account.getData().getPhone();
            if (phone != null)
                phone_label.setText(phone);

            String social = account.getData().getSocial();
            if (social != null)
                social_label.setText(social);

            int age = account.getData().getAge();
            if (age != 0)
                age_label.setText(String.valueOf(age));

            Date date = account.getData().getEmplStartDate();
            if (date != null) {
                String normalDate = new SimpleDateFormat(DATE_PATTERN).format(date);
                start_date.setText(normalDate);
            }

            Date endDate = account.getData().getEmplEndDate();
            if (endDate != null) {
                String normalDate = new SimpleDateFormat(DATE_PATTERN).format(date);
                end_date.setText(normalDate);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }

    public void iconHandler(MouseEvent mouseEvent) {
        GeneralFuncWindow.openNewScene(Paths.WindowEdit);
        updateData();
    }
}

