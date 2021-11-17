//package by.bsuir.app.controllers.submenu;
//
//import com.bsuir.entities.Car;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.VBox;
//import sample.clientConnection.Phone;
//import sample.constansts.Data;
//import sample.constansts.Windows;
//import sample.entities.CarForTable;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class CatalogController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private ScrollPane scrollPane;
//
//    @FXML
//    private Button sedan_filter;
//
//    @FXML
//    private Button coupe_filter;
//
//    @FXML
//    private Button hatch_filter;
//
//    @FXML
//    private Button allFilter;
//
//    @FXML
//    private Button universal_filter;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        sedan_filter.getScene().getWindow().hide();
//    }
//
//    private static String FILTER = null;
//    private static final ArrayList<CarForTable> local_list = new ArrayList<>();
//    @FXML
//    void initialize() {
//
//        load(FILTER);
//
//        allFilter.setOnAction(actionEvent -> {
//            FILTER = null;
//            load(FILTER);
//        });
//
//        sedan_filter.setOnAction(actionEvent -> {
//            FILTER = "2";
//            load(FILTER);
//        });
//        coupe_filter.setOnAction(actionEvent -> {
//            FILTER = "6";
//            load(FILTER);
//        });
//        universal_filter.setOnAction(actionEvent -> {
//            FILTER = "4";
//            load(FILTER);
//        });
//        hatch_filter.setOnAction(actionEvent -> {
//            FILTER = "3";
//            load(FILTER);
//        });
//    }
//
//    private void draw() {
////        if (FILTER != null)
////            Data.getCarList().removeIf(obj -> !obj.getBody_type().equals(FILTER));
//
//        Node[] nodes = new Node[Data.getCarList().size()];
//        VBox vBox = new VBox();
//        for (int i = 0; !Data.getCarList().isEmpty(); i++) {
//            try {
//                nodes[i] = (Node) FXMLLoader.load(getClass().getResource(Windows.WindowCatalogRow));
//                vBox.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        scrollPane.setContent(vBox);
//    }
//
//    private void load(String filter) {
//
//        if (FILTER == null)
//            Phone.writeLine("SELECT * FROM Car");
//        else
//            Phone.writeLine("SELECT * FROM Car WHERE body_type_id = " + filter);
//
//        ArrayList<Car> l_list = (ArrayList<Car>) Phone.readObject();
//
//        for (Car car : l_list)
//            local_list.add(new CarForTable(car));
//
//        Data.setCarList(local_list);
//        draw();
//    }
//}
