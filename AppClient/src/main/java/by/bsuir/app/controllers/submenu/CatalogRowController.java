//package by.bsuir.app.controllers.submenu;
//
//import java.io.File;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import com.bsuir.entities.Car_photo;
//import com.bsuir.jdbc.TableNames;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import sample.clientConnection.Phone;
//import sample.constansts.Data;
//
//public class CatalogRowController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private ImageView imageView;
//
//    @FXML
//    private Label series;
//
//    @FXML
//    private Label model;
//
//    @FXML
//    private Label body_type;
//
//    @FXML
//    private Label fuel_type;
//
//    @FXML
//    private Label issue_date;
//
//    @FXML
//    private Label price;
//
//    @FXML
//    private Label seats;
//
//    @FXML
//    private Label quantity;
//
//    @FXML
//    private Label gearbox;
//
//    @FXML
//    private Label rate;
//
//    @FXML
//    private Label description;
//
//    @FXML
//    void initialize() {
//        series.setText(Data.getCarList().get(0).getSeries());
//        model.setText(Data.getCarList().get(0).getModel());
//        body_type.setText(Data.getCarList().get(0).getBody_type());
//        fuel_type.setText(Data.getCarList().get(0).getFuel_type());
//        issue_date.setText(Data.getCarList().get(0).getIssue_date());
//        price.setText(String.valueOf(Data.getCarList().get(0).getPrice()) + "$");
//        gearbox.setText(Data.getCarList().get(0).getGearbox());
//        seats.setText(String.valueOf(Data.getCarList().get(0).getSeats()));
//        rate.setText(String.valueOf(Data.getCarList().get(0).getRate()));
//        description.setText(Data.getCarList().get(0).getDescription());
//        quantity.setText("10000");      //TODO ДОДЕЛАТЬ ПОЛЕ
//        String photoURL = findPhoto(Data.getCarList().get(0).getVIN());
//        System.out.println(photoURL);
//        if (!photoURL.equals(""))
//            imageView.setImage(new Image(new File(photoURL).toURI().toString()));
//        Data.getCarList().remove(0);
//    }
//
//    private String findPhoto(String VIN) {
//        Phone.writeLine("SELECT url FROM " + TableNames.CAR_PHOTO +
//                " WHERE VIN = '" + VIN + "'");
//        ArrayList<Car_photo> photos = (ArrayList<Car_photo>) Phone.readObject();
//        return (!photos.isEmpty()) ? photos.get(0).getUrl() : "";
//    }
//}
