//package by.bsuir.app.controllers.submenu;
//
//import by.bsuir.app.entity.enums.BodyType;
//import by.bsuir.app.entity.enums.FuelType;
//import by.bsuir.app.entity.enums.GearBox;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//
//import java.net.URL;
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
////        Phone.writeLine("SELECT * FROM Model");
////        List<Model> modelList = (ArrayList<Model>) Phone.readObject();
////        Phone.writeLine("SELECT * FROM Body_types");
////        List<Body_type> body_types = (ArrayList<Body_type>) Phone.readObject();
////        Phone.writeLine("SELECT * FROM Fuel_types");
////        List<Fuel_type> fuel_types = (ArrayList<Fuel_type>) Phone.readObject();
//
////        ObservableList<String> ol_model = FXCollections.observableArrayList();
////        for (Model m : modelList) {
////            ol_model.add(m.getModel());
////        }
////        model.setItems(ol_model);
//
//        ObservableList<String> ol_series = FXCollections.observableArrayList();
//        ol_series.add("100");
//        ol_series.add("200");
//        ol_series.add("300");
//        series.setItems(ol_series);
//
//        ObservableList<String> gearOL = FXCollections.observableArrayList();
//        for (GearBox b: GearBox.values())
//            gearOL.add(b.getRusName());
//        gear.setItems(gearOL);
//
//        ObservableList<String> body = FXCollections.observableArrayList();
//        for (BodyType b: BodyType.values())
//            body.add(b.getBodyType());
//        bodyType.setItems(body);
//
//        ObservableList<String> fuel = FXCollections.observableArrayList();
//        for (FuelType f: FuelType.values())
//            fuel.add(f.getRusName());
//        fuelType.setItems(fuel);
//
//        ObservableList<Integer> rateOL = FXCollections.observableArrayList();
//        for (int i = 1; i < 6; i++) {
//            rateOL.add(i);
//        }
//        rate.setItems(rateOL);
//
//        addButton.setOnAction(actionEvent -> {
//
////            if ()
////                war_label.setText("Ошибка. Введите дату в формате yyyy-MM-dd");
////
////            Car car = new Car();
////            try {
////                Phone.sendOrGetData(Commands.ADD_NEW_CAR, );
////                war_label.setText("Добавлено. Обновите табилицу.");
////                war_label.setVisible(false);
////
////            } catch () {
////            }
////            war_label.setVisible(true);
//        });
//    }
//}
