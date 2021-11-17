//package by.bsuir.app.controllers.submenu;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import sample.constansts.Data;
//import sample.constansts.Windows;
//import sample.services.GeneralFuncWindow;
//
//import java.io.File;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class ProfileController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private ImageView image_box;
//
//    @FXML
//    private Label login_label;
//
//    @FXML
//    private Label mail_label;
//
//    @FXML
//    private Label sex_label;
//
//    @FXML
//    private Label age_label;
//
//    @FXML
//    private Label name_label;
//
//    @FXML
//    private Label surname_label;
//
//    @FXML
//    private Label third_name_label;
//
//    @FXML
//    private Label position_label;
//
//    @FXML
//    private Label start_date;
//
//    @FXML
//    private Label end_date;
//
//    @FXML
//    private Label adress_label;
//
//    @FXML
//    private Label phone_label;
//
//    @FXML
//    private Label social_label;
//
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//        setData();
//    }
//
//    void setData() {
//        String gender = Data.getEmpl().getGender();
//        if (gender.equals("Мужской"))
//            image_box.setImage(new Image(new File("src/sample/assets/man-avatar.png").toURI().toString()));
//        else if (gender.equals("Женский"))
//            image_box.setImage(new Image(new File("src/sample/assets/woman-avatar.png").toURI().toString()));
//
//        position_label.setText(Data.getPosition().getPositionName());
//        login_label.setText(Data.getEmpl().getLogin());
//        mail_label.setText(Data.getEmpl().getEmail());
//        sex_label.setText(Data.getEmpl().getGender());
//
//        if (Data.getEmpl().getName() != null) {
//            name_label.setText(Data.getEmpl().getName());
//        }
//
//        if (Data.getEmpl().getSurname() != null) {
//            surname_label.setText(Data.getEmpl().getSurname());
//        }
//        if (Data.getEmpl().getThirdname() != null) {
//            third_name_label.setText(Data.getEmpl().getThirdname());
//        }
//
//        if (Data.getContacts().getAddress() != null)
//            adress_label.setText(Data.getContacts().getAddress());
//
//        String phone = "";
//        if (Data.getContacts().getPhone_code() != null)
//            phone += Data.getContacts().getPhone_code() + " - ";
//        if (Data.getContacts().getPhone() != 0)
//            phone += Data.getContacts().getPhone();
//        if (!phone.equals(""))
//            phone_label.setText(phone);
//
//        if (Data.getContacts().getAddress() != null)
//            social_label.setText(Data.getContacts().getSocial());
//
//        if (Data.getEmpl().getAge() != 0)
//            age_label.setText(String.valueOf(Data.getEmpl().getAge()));
//
//        start_date.setText(Data.getEmpl().getStartDate());
//
//        if (Data.getEmpl().getEndDate() != null)
//            end_date.setText(Data.getEmpl().getEndDate());
//    }
//
//    public void iconHandler(MouseEvent mouseEvent) {
//        GeneralFuncWindow.openNewScene(Windows.WindowEdit);
//    }
//}
//
