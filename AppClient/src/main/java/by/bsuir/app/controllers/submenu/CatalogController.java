package by.bsuir.app.controllers.submenu;

import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.enums.BodyType;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import by.bsuir.app.util.constants.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log4j2
public class CatalogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button sedan_filter;

    @FXML
    private Button coupe_filter;

    @FXML
    private Button hatchback_filter;

    @FXML
    private Button allFilter;

    @FXML
    private Button convertible_filter;


    @FXML
    void handleClose(MouseEvent event) {
        scrollPane.getScene().getWindow().hide();
    }

    private List<Car> carsWithFilter = null;
    private List<Car> carsAll = null;


    {
        try {
            List<Car> cars = (List<Car>) Phone.sendOrGetData(Commands.GET_ALL_CARS, new Car());
            carsAll = new ArrayList<>(cars);
            carsWithFilter = new ArrayList<>(cars);
            LocalStorage.setCars(cars);
        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        draw();

        allFilter.setOnAction(actionEvent -> setFilter(BodyType.NONE));

        sedan_filter.setOnAction(actionEvent -> setFilter(BodyType.SEDAN));

        coupe_filter.setOnAction(actionEvent -> setFilter(BodyType.COUPE));

        hatchback_filter.setOnAction(actionEvent -> setFilter(BodyType.HATCHBACK));

        convertible_filter.setOnAction(actionEvent -> setFilter(BodyType.CONVERTIBLE));

    }

    private void draw() {

        int size = carsWithFilter.size();
        Node[] nodes = new Node[size];
        VBox vBox = new VBox();
        for (int i = 0; i < size; i++) {
            try {
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource(Paths.WindowCatalogRow));
                vBox.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scrollPane.setContent(vBox);
    }

    private void resetFilter() {
        carsWithFilter = new ArrayList<>(carsAll);
    }

    private void serFilteredCarsInStorage() {
        LocalStorage.setCars(carsWithFilter);
    }

    private void setFilter(BodyType bodyType) {
        resetFilter();
        if (bodyType != BodyType.NONE)
            carsWithFilter = carsWithFilter.stream().filter(
                    c -> c.getBodyType().equals(bodyType.getBodyType())).collect(
                    Collectors.toList());
        serFilteredCarsInStorage();
        draw();
    }
}
