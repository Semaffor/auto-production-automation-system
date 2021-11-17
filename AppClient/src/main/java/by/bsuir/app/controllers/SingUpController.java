//package by.bsuir.app.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//import sample.animation.Shake;
//import sample.clientConnection.Phone;
//import sample.constansts.TEmployees;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class SingUpController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextField login_field;
//
//    @FXML
//    private PasswordField password_field;
//
//    @FXML
//    private PasswordField confirm_password_field;
//
//    @FXML
//    private Button registrationButton;
//
//    @FXML
//    private TextField email_field;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    private Button returnButton;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        registrationButton.getScene().getWindow().hide();
//    }
//
//    @FXML
//    private RadioButton boxMale;
//
//    @FXML
//    private RadioButton boxFemale;
//
//    @FXML
//    void initialize() {
//
//        registrationButton.setOnAction(actionEvent -> {
//            singUpNewUser();
//        });
//
//        returnButton.setOnAction(actionEvent -> {
//            returnButton.getScene().getWindow().hide();
//        });
//    }
//
//    private void singUpNewUser() {
//        //while (true) {
//            String login = login_field.getText();
//            String password = password_field.getText();
//            String confirmPassword = confirm_password_field.getText();
//            String email = email_field.getText();
//            String gender = "";
//
//            if (boxMale.isSelected())
//                gender = "Мужской";
//            else if (boxFemale.isSelected())
//                gender = "Женский";
//            else
//                gender = "";
//
//            if (login.equals("") || password.equals("") || confirmPassword.equals("") || email.equals("")
//                    || gender.equals("")) {
//                warning_label.setText("Заполните все поля");
//                warning_label.setVisible(true);
//
//                Shake loginAnim = new Shake(login_field);
//                Shake passwordAnim = new Shake(password_field);
//                Shake confirmPasswordAnim = new Shake(confirm_password_field);
//                Shake emailAnim = new Shake(email_field);
//                loginAnim.playAnim();
//                passwordAnim.playAnim();
//                confirmPasswordAnim.playAnim();
//                emailAnim.playAnim();
//
//            } else {
//                if (login.length() < 4)
//                    warning_label.setText("Логин менее 4 символов");
//                if (password.length() < 6)
//                    warning_label.setText("Пароль менее 6 символов");
//                if (password.equals(confirmPassword)) {
//                    String request = "INSERT INTO "
//                            + TEmployees.TABLE_NAME + " ("
//                            + TEmployees.LOGIN + ", "
//                            + TEmployees.PASSWORD + ", "
//                            + TEmployees.EMAIL + ", "
//                            + TEmployees.GENDER + ", "
//                            + TEmployees.POSITION + ") "
//                            + "VALUES ( '"
//                            + login + "', '"
//                            + password + "', '"
//                            + email + "', '"
//                            + gender + "', 2)";
//
//                    Phone.writeLine(request);
//                    String answer = Phone.readLine();
//
//                    if (answer.equals("GOOD")) {
//                        warning_label.setText("Вы зарегистрировались.");
//                        registrationButton.getScene().getWindow().hide();
//                    } else {
//                        warning_label.setText("Логин занят. Придумайте другой.");
//                    }
//                } else {
//                    warning_label.setText("Пароли не совпадают");
//                }
//            }
//        warning_label.setVisible(true);
//    }
//}