package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Model;
import by.bsuir.app.entity.enums.BodyType;
import by.bsuir.app.entity.enums.FuelType;
import by.bsuir.app.entity.enums.GearBox;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

import static by.bsuir.app.util.constants.Constants.ADD_FAIL_MSG;
import static by.bsuir.app.util.constants.Constants.ADD_SUCCESS_MSG;

@Log4j2
public class AddCarController {

    @FXML
    private Label label;

    @FXML
    private TextField VIN_field;

    @FXML
    private ComboBox<String> model_box;

    @FXML
    private DatePicker issue_box_date;

    @FXML
    private ComboBox<String> gear_field;

    @FXML
    private ComboBox<String> bodyType_field;

    @FXML
    private ComboBox<String> fuelType_field;

    @FXML
    private ComboBox<Integer> rate_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextArea description_area;

    @FXML
    private Label war_label;

    @FXML
    private Button addButton;

    @FXML
    void closeHandler(MouseEvent event) {
        addButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        try {
            List<Model> models = (List<Model>) Phone.sendOrGetData(Commands.GET_ALL_MODELS, new Model());


            ObservableList<String> ol_model = FXCollections.observableArrayList();
            for (Model m : models) {
                ol_model.add(m.getName());
            }
            model_box.setItems(ol_model);

            ObservableList<String> ol_gear = FXCollections.observableArrayList();
            for (GearBox b : GearBox.values())
                ol_gear.add(b.getRusName());
            gear_field.setItems(ol_gear);

            ObservableList<String> ol_body = FXCollections.observableArrayList();
            for (BodyType b : BodyType.values())
                ol_body.add(b.getBodyType());
            bodyType_field.setItems(ol_body);

            ObservableList<String> fuel = FXCollections.observableArrayList();
            for (FuelType f : FuelType.values())
                fuel.add(f.getRusName());
            fuelType_field.setItems(fuel);

            ObservableList<Integer> ol_rate = FXCollections.observableArrayList();
            for (int i = 1; i < 6; i++) {
                ol_rate.add(i);
            }
            rate_field.setItems(ol_rate);
        } catch(IOException | ClassNotFoundException | GettingDataException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

            addButton.setOnAction(actionEvent -> {

            Car car = new Car();
            try {
                Phone.sendOrGetData(Commands.ADD_NEW_CAR, car);
                war_label.setText(ADD_SUCCESS_MSG);

            } catch (IOException | GettingDataException | ClassNotFoundException e) {
                log.error(e.getMessage());
                war_label.setText(ADD_FAIL_MSG);
                e.printStackTrace();
            }
                war_label.setVisible(true);
        });
    }
}
