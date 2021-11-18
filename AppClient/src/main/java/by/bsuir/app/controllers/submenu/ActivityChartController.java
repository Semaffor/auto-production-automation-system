//package by.bsuir.app.controllers.submenu;
//
//import by.bsuir.app.services.GeneralFuncWindow;
//import javafx.fxml.FXML;
//import javafx.scene.chart.AreaChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URL;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class ActivityChartController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private HBox hBox;
//
//    @FXML
//    private Label warning_label;
//
//    @FXML
//    private Label number_label;
//
//    @FXML
//    private AreaChart<Number, String> chart;
//
//    @FXML
//    private CategoryAxis y;
//
//    @FXML
//    private NumberAxis x;
//
//    @FXML
//    private Button reportButton;
//
//    @FXML
//    private Button openButton;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//
//        String request = "SELECT COUNT(employee_ID) AS count, entranceDate FROM Logging_history GROUP BY entranceDate";
//        by.bsuir.app.controllers.clientConnection.Phone.writeLine(request);
//
//        List<LogH> countOfUsersList = (List<LogH>) by.bsuir.app.controllers.clientConnection.Phone.readObject();
//        System.out.println(countOfUsersList);
//
//        XYChart.Series<Number, String> seriesActivity =
//                new XYChart.Series<>();
//        seriesActivity.setName("Запуски");
//
//        int countOfUsers = 0;
//        for (LogH i : countOfUsersList) {
//            seriesActivity.getData().add(new XYChart.Data(i.getDate(), i.getCount()));
//            countOfUsers += i.getCount();
//        }
//        number_label.setText(String.valueOf(countOfUsers));
//
//        openButton.setOnAction(actionEvent -> {
//            File file = new File("reportActivityLog.txt");
//            Desktop desktop = Desktop.getDesktop();
//            if(file.exists()) {
//                try {
//                    desktop.open(file);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        reportButton.setOnAction(actionEvent -> {
//            try(FileWriter writer = new FileWriter("reportActivityLog.txt", false))
//            {
//                LocalDateTime myDateObj = LocalDateTime.now();
//                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//                String formattedDate = myDateObj.format(myFormatObj);
//                writer.write("Дата создания отчета: " + formattedDate + "\n");
//
//                StringBuilder report = new StringBuilder();
//                report.append("\tid\tdate\n\n");
//                by.bsuir.app.controllers.clientConnection.Phone.writeLine("SELECT employee_ID AS count, entranceDateFull AS entranceDate FROM Logging_history");
//                List<LogH> log = (List<LogH>) by.bsuir.app.controllers.clientConnection.Phone.readObject();
//
//                for (LogH l: log)
//                    report.append("\t").append(l.getCount()).append("\t" + l.getDate() + "\n");
//
//                writer.write(String.valueOf(report));
//                openButton.setDisable(false);
//                writer.flush();
//            }
//            catch(IOException ex){
//                System.out.println(ex.getMessage());
//            }
//        });
//
//        chart.getData().add(seriesActivity);
//    }
//}
