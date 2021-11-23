package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Model;
import by.bsuir.app.entity.enums.BodyType;
import by.bsuir.app.entity.enums.FuelType;
import by.bsuir.app.entity.enums.GearBox;
import by.bsuir.app.exception.EmptyFieldsException;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static by.bsuir.app.util.constants.Constants.*;

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
    private ComboBox<String> gear_box;

    @FXML
    private ComboBox<String> bodyType_box;

    @FXML
    private ComboBox<String> fuelType_box;

    @FXML
    private ComboBox<Double> rate_field;

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
            gear_box.setItems(ol_gear);

            ObservableList<String> ol_body = FXCollections.observableArrayList();
            for (BodyType b : BodyType.values())
                ol_body.add(b.getBodyType());
            bodyType_box.setItems(ol_body);

            ObservableList<String> fuel = FXCollections.observableArrayList();
            for (FuelType f : FuelType.values())
                fuel.add(f.getRusName());
            fuelType_box.setItems(fuel);

            ObservableList<Double> ol_rate = FXCollections.observableArrayList();
            for (int i = 1; i <= 5; i++) {
                ol_rate.add((double) i);
            }
            rate_field.setItems(ol_rate);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }


        addButton.setOnAction(actionEvent -> {

            try {
                Car car = new Car();
                String VIN = VIN_field.getText().toUpperCase(Locale.ROOT);
                String model = model_box.getValue();
                String bodyType = bodyType_box.getValue();
                String fuel = fuelType_box.getValue();
                String gear = gear_box.getValue();
                if (rate_field.getValue() == null)
                    throw new EmptyFieldsException(FILL_FIELDS_MSG);

                System.out.println(rate_field.getValue());
                Double rate =  Double.parseDouble(String.valueOf(rate_field.getValue()).replace(",", "."));

                if (issue_box_date.getValue() == null)
                    throw new EmptyFieldsException(FILL_FIELDS_MSG);

                LocalDate date = issue_box_date.getValue();
                if (price_field.getText() == null)
                    throw new EmptyFieldsException(FILL_FIELDS_MSG);

                String priceString = price_field.getText().replace(",", ".");

                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
                if (VIN.isEmpty() || bodyType.isEmpty() || fuel.isEmpty() || gear.isEmpty())
                    throw new EmptyFieldsException(FILL_FIELDS_MSG);

//                if (rate <= 0.0 || rate >= 5.0)
//                    throw new EmptyFieldsException(INCORRECT_RATE_MSG);

                car.setVIN(VIN);
                car.getModel().setName(model.toString());
                car.setBodyType(bodyType);
                car.setFuelType(fuel);
                car.setGearbox(gear);
                car.setRate(BigDecimal.valueOf(rate));
                car.setIssueDate(Date.valueOf(date));
                car.setPrice(price);

                Phone.sendOrGetData(Commands.ADD_NEW_CAR, car);
                war_label.setText(ADD_SUCCESS_MSG);

            } catch (IOException | GettingDataException | ClassNotFoundException e) {
                log.error(e.getMessage());
                war_label.setText(ADD_FAIL_MSG);
                e.printStackTrace();
            } catch (EmptyFieldsException e) {
                war_label.setText(e.getMessage());
            } catch (NumberFormatException e) {
                war_label.setText(USE_COMA_INSTEADOF_POINT);
            }
            war_label.setVisible(true);
        });
    }
}
