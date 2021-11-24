package by.bsuir.app.controllers.others;

import by.bsuir.app.entity.Car;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DiagramController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label number_label;

    @FXML
    private HBox hBox;

    @FXML
    private Label warning_label;

    @FXML
    private BarChart<Integer, String> chart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    void handleClose(MouseEvent event) {
        GeneralFuncWindow.closeApplication();
    }

    @FXML
    void initialize() {

        String request = "SELECT series, series AS model, SUM(amount) AS amount " +
                " FROM Model " +
                " GROUP BY series ";

        try {
            List<Object[]> models = (List<Object[]>)  Phone.sendOrGetData(Commands.GET_ALL_MODELS_GROUPED_BY_QUANTITY,
                    new Car());


            int size = models.size();
            XYChart.Series[] set = new XYChart.Series[size];

            int totalQuantity = 0;
            int i = 0;
            for(Object object : models) {
                set[i] = new XYChart.Series();

                Map row = (Map)object;
                String modelName = (String) row.get("model");
                BigDecimal quantityBig = (BigDecimal) row.get("quantity");
                BigInteger bigInteger = quantityBig.toBigInteger();
                Integer quantity = bigInteger.intValue();
                set[i].setName(modelName);
                set[i].getData().add(new XYChart.Data<>(modelName, quantity));
                i++;
                totalQuantity += quantity;
            }

            number_label.setText(String.valueOf(totalQuantity));
            chart.getData().addAll(set);

        } catch (IOException | ClassNotFoundException | GettingDataException e) {
            e.printStackTrace();
        }
    }
}
