package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Model;
import by.bsuir.app.util.constants.Constants;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.List;

@Log4j2
public class CatalogRowController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label model_label;

    @FXML
    private Label body_type_label;

    @FXML
    private Label fuel_type_label;

    @FXML
    private Label issue_date_label;

    @FXML
    private Label price_label;

    @FXML
    private Label gearbox_label;

    @FXML
    private Label rate_label;

    @FXML
    private Label description_label;

    @FXML
    private Label quantity_label;

    private int index = 0;
    private List<Car> cars = null;

    {
        cars = LocalStorage.getCars();
    }

    //TODO iterator
    @FXML
    void initialize() {
        initializeRowsWithData();
    }

    private void initializeRowsWithData() {
        Car car = cars.get(0);
        Model model = car.getModel();

        model_label.setText(model.getName());
        description_label.setText(model.getDescription());

        body_type_label.setText(car.getBodyType());
        fuel_type_label.setText(car.getFuelType());
        issue_date_label.setText(car.getIssueDate().toString());
        price_label.setText(car.getPrice().toString() + Constants.CURRENCY_MSG);
        gearbox_label.setText(car.getGearbox());
        rate_label.setText(String.valueOf(car.getRate()));
        quantity_label.setText(car.getPrice().toString());

        String photoURL = car.getPhotoURL();
        if (photoURL != null)
            imageView.setImage(new Image(new File(photoURL).toURI().toString()));

        if (index == cars.size() - 1) {
            index = 0;
        } else {
            index++;
        }
    }
}
