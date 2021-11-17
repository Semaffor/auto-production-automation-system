//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Body_type;
//import com.bsuir.entities.Fuel_type;
//import com.bsuir.entities.Model;
//import com.bsuir.jdbc.TableNames;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//import sample.clientConnection.Phone;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class AddCarController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private Label label;
//
//    @FXML
//    private TextField VIN;
//
//    @FXML
//    private TextField costField;
//
//    @FXML
//    private TextField description;
//
//    @FXML
//    private ComboBox<String> series;
//
//    @FXML
//    private ComboBox<String> model;
//
//    @FXML
//    private DatePicker date;
//
//    @FXML
//    private ComboBox<String> gear;
//
//    @FXML
//    private ComboBox<String> bodyType;
//
//    @FXML
//    private ComboBox<String> fuelType;
//
//    @FXML
//    private ComboBox<Integer> rate;
//
//    @FXML
//    private TextField responsible;
//
//    @FXML
//    private Label war_label;
//
//    @FXML
//    private Button addButton;
//
//    @FXML
//    void closeHandler(MouseEvent event) {
//        addButton.getScene().getWindow().hide();
//    }
//
//    @FXML
//    void initialize() {
//
//        Phone.writeLine("SELECT * FROM Model");
//        List<Model> modelList = (ArrayList<Model>) Phone.readObject();
//        Phone.writeLine("SELECT * FROM Body_types");
//        List<Body_type> body_types = (ArrayList<Body_type>) Phone.readObject();
//        Phone.writeLine("SELECT * FROM Fuel_types");
//        List<Fuel_type> fuel_types = (ArrayList<Fuel_type>) Phone.readObject();
//
//        ObservableList<String> ol_model = FXCollections.observableArrayList();
//        ObservableList<String> ol_series = FXCollections.observableArrayList();
//        ol_series.add("100");
//        ol_series.add("200");
//        ol_series.add("300");
//        ObservableList<String> gearOL = FXCollections.observableArrayList();
//        gearOL.add("АКПП");
//        gearOL.add("МКПП");
//        gear.setItems(gearOL);
//
//        ObservableList<String> body = FXCollections.observableArrayList();
//        for (Body_type b: body_types)
//            body.add(b.getBody_type());
//        bodyType.setItems(body);
//
//        ObservableList<String> fuel = FXCollections.observableArrayList();
//        for (Fuel_type f: fuel_types)
//            fuel.add(f.getFuel_type());
//        fuelType.setItems(fuel);
//
//        ObservableList<Integer> rateOL = FXCollections.observableArrayList();
//        for (int i = 1; i < 6; i++) {
//            rateOL.add(i);
//        }
//        rate.setItems(rateOL);
//
//        for (Model m : modelList) {
//            ol_model.add(m.getModel());
//        }
//        model.setItems(ol_model);
//        series.setItems(ol_series);
//
//        addButton.setOnAction(actionEvent -> {
//            System.out.println(date.getValue().toString());
//            Phone.writeLine("INSERT INTO " + TableNames.CAR +
//                    " VALUES('" + VIN.getText().toUpperCase() + "', 1,2,1, '" +
//                    date.getValue().toString() + "', '" +
//                    costField.getText() + "', 1, '" +
//                    gear.getValue() + "', '" +
//                    description.getText() + "', 'USD', " +
//                    rate.getValue() + ")");
//            if (Phone.readLine().equals("GOOD"))
//                war_label.setText("Добавлено. Обновите табилицу.");
//            else
//                war_label.setText("Ошибка. Введите дату в формате yyyy-MM-dd");
//            war_label.setVisible(true);
//        });
//    }
//}
