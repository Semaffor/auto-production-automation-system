//package by.bsuir.app.controllers.others;
//
//import javafx.fxml.FXML;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class DiagramController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private Label number_label;
//
//    @FXML
//    private HBox hBox;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    private BarChart<Integer, String> chart;
//
//    @FXML
//    private CategoryAxis x;
//
//    @FXML
//    private NumberAxis y;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        by.bsuir.app.controllers.services.GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//
//        String request = "SELECT series, series AS model, SUM(amount) AS amount " +
//                " FROM Model " +
//                " GROUP BY series ";
//
//        by.bsuir.app.controllers.clientConnection.Phone.writeLine(request);
//        by.bsuir.app.controllers.constansts.Data.setModelList((ArrayList<Model>) by.bsuir.app.controllers.clientConnection.Phone.readObject());
//
//        XYChart.Series[] set = new XYChart.Series[by.bsuir.app.controllers.constansts.Data.getModelList().size()];
//
//        int sum = 0;
//        for (int i = 0; i < by.bsuir.app.controllers.constansts.Data.getModelList().size(); i++) {
//            set[i] = new XYChart.Series();
//            set[i].setName(by.bsuir.app.controllers.constansts.Data.getModelList().get(i).getSeries() + "' series");
//            set[i].getData().add(new XYChart.Data<>(by.bsuir.app.controllers.constansts.Data.getModelList().get(i).getSeries(),
//                    by.bsuir.app.controllers.constansts.Data.getModelList().get(i).getAmount()));
//            sum+= by.bsuir.app.controllers.constansts.Data.getModelList().get(i).getAmount();
//        }
//
//        number_label.setText(String.valueOf(sum));
//        chart.getData().addAll(set);
//    }
//}
