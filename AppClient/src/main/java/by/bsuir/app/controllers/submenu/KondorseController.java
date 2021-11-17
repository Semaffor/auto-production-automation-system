//package by.bsuir.app.controllers.submenu;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import sample.services.GeneralFuncWindow;
//
//import java.awt.*;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URL;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.ResourceBundle;
//
//public class KondorseController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextField cell_01;
//
//    @FXML
//    private TextField cell_02;
//
//    @FXML
//    private TextField cell_03;
//
//    @FXML
//    private TextField cell_04;
//
//    @FXML
//    private TextField cell_10;
//
//    @FXML
//    private TextField cell_12;
//
//    @FXML
//    private TextField cell_13;
//
//    @FXML
//    private TextField cell_14;
//
//    @FXML
//    private TextField cell_20;
//
//    @FXML
//    private TextField cell_21;
//
//    @FXML
//    private TextField cell_23;
//
//    @FXML
//    private TextField cell_24;
//
//    @FXML
//    private TextField cell_30;
//
//    @FXML
//    private TextField cell_31;
//
//    @FXML
//    private TextField cell_32;
//
//    @FXML
//    private TextField cell_34;
//
//    @FXML
//    private TextField cell_40;
//
//    @FXML
//    private TextField cell_41;
//
//    @FXML
//    private TextField cell_42;
//
//    @FXML
//    private TextField cell_43;
//
//    @FXML
//    private Button solverButton;
//
//    @FXML
//    private Button reportButton;
//
//    @FXML
//    private Button openButton;
//
//    @FXML
//    private Label war_label;
//
//    @FXML
//    private ComboBox<Integer> variantBox;
//
//    @FXML
//    private ComboBox<Integer> variantBox1;
//
//    @FXML
//    private ComboBox<Integer> variantBox2;
//
//    @FXML
//    private ComboBox<Integer> variantBox3;
//
//    @FXML
//    private ComboBox<Integer> variantBox4;
//
//    @FXML
//    void handleClose(MouseEvent event) {
//        GeneralFuncWindow.closeApplication();
//    }
//
//    @FXML
//    void initialize() {
//
//        StringBuilder report = new StringBuilder();
//
//        ObservableList<Integer> oa = FXCollections.observableArrayList();
//        oa.add(1);
//        oa.add(2);
//        oa.add(3);
//        oa.add(4);
//        oa.add(5);
//
//        variantBox.setItems(oa);
//        variantBox1.setItems(oa);
//        variantBox2.setItems(oa);
//        variantBox3.setItems(oa);
//        variantBox4.setItems(oa);
//
//        openButton.setOnAction(actionEvent -> {
//            File file = new File("reportCondorcet.txt");
//            Desktop desktop = Desktop.getDesktop();
//            if (file.exists()) {
//                try {
//                    desktop.open(file);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        reportButton.setOnAction(actionEvent -> {
//            try (FileWriter writer = new FileWriter("reportCondorcet.txt", true)) {
//                LocalDateTime myDateObj = LocalDateTime.now();
//                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//                String formattedDate = myDateObj.format(myFormatObj);
//
//                writer.write(formattedDate + " (GMT+3)\n");
//                writer.write(String.valueOf(report));
//                war_label.setText("Отчет сохранен в файле reportCondorcet.txt");
//                openButton.setDisable(false);
//                writer.flush();
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        });
//
//        solverButton.setOnAction(actionEvent -> {
//
//            if (variantBox.getValue() == null || variantBox1.getValue() == null || variantBox2.getValue() == null
//                    || variantBox3.getValue() == null)
//                war_label.setText("Укажите приоритетность.");
//            else if (isDiplicated()) {
//                war_label.setText("Дублирующиеся варианты не допускаются.");
//            } else {
//                int k, i, j;
//                int[][] p = {
//                        {0, 4, 3, 3, 2},
//                        {0, 1, 5, 5, 1},
//                        {0, 2, 4, 1, 3},
//                        {0, 5, 2, 2, 4},
//                        {0, 3, 1, 4, 5},
//                };
//
//                p[0][0] = variantBox.getValue();
//                p[1][0] = variantBox1.getValue();
//                p[2][0] = variantBox2.getValue();
//                p[3][0] = variantBox3.getValue();
//                p[4][0] = variantBox4.getValue();
//
//                System.out.println("------------------P-------------------");
//                report.append("------------МАТРИЦА РАНЖИРОВАНИЯ АЛЬТЕРНАТИВ ЭКСПЕРТАМИ---------\n\n");
//
//                for (i = 0; i < p.length; i++) {
//                    for (j = 0; j < p.length; j++) {
//                        report.append(p[i][j] + "\t");
//                    }
//                    report.append('\n');
//                }
//                System.out.println(Arrays.deepToString(p));
//
//                report.append("\n\n");
//
//                for (k = 0; k < p.length; k++) {
//                    for (i = 0; i < p.length; i++)
//                        for (j = 0; j < p.length; j++)
//                            if (p[j][i] == (k + 1))
//                                p[k][i] = j + 1;
//                }
//
//                int[][] m = new int[5][5];
//                for (k = 0; k < m.length; k++) {
//                    for (i = 0; i < m.length; i++) {
//                        for (j = 0; j < m.length; j++) {
//                            if (p[k][j] < p[i][j] && i != k)
//                                m[k][i]++;
//                        }
//                    }
//                }
//
//                System.out.println("------------------M-------------------");
//                System.out.println(Arrays.deepToString(m));
//                report.append("------Оценки, характеризующих предпочтение альтернатив в парных предпочтениях------\n\n");
//
//                for (i = 0; i < m.length; i++) {
//                    for (j = 0; j < m.length; j++) {
//                        report.append(m[i][j] + "\t");
//                    }
//                    report.append('\n');
//                }
//                report.append("\n\n");
//
//                cell_01.setText(String.valueOf(m[0][1]));
//                cell_02.setText(String.valueOf(m[0][2]));
//                cell_03.setText(String.valueOf(m[0][3]));
//                cell_04.setText(String.valueOf(m[0][4]));
//                cell_10.setText(String.valueOf(m[1][0]));
//                cell_12.setText(String.valueOf(m[1][2]));
//                cell_13.setText(String.valueOf(m[1][3]));
//                cell_14.setText(String.valueOf(m[1][4]));
//                cell_20.setText(String.valueOf(m[2][0]));
//                cell_21.setText(String.valueOf(m[2][1]));
//                cell_23.setText(String.valueOf(m[2][3]));
//                cell_24.setText(String.valueOf(m[2][4]));
//                cell_30.setText(String.valueOf(m[3][0]));
//                cell_31.setText(String.valueOf(m[3][1]));
//                cell_32.setText(String.valueOf(m[3][2]));
//                cell_34.setText(String.valueOf(m[3][4]));
//                cell_40.setText(String.valueOf(m[4][0]));
//                cell_41.setText(String.valueOf(m[4][1]));
//                cell_42.setText(String.valueOf(m[4][2]));
//                cell_43.setText(String.valueOf(m[4][3]));
//
//                // выбираем наилучшую альтернативу согласно принципу Кондерсе
//                int n = 0;
//                int bestVariant = 0;
//                for (i = 0; i < m.length; i++) {
//                    for (j = 0; j < m.length; j++) {
//                        if (m[i][j] >= m[j][i] && i != j)
//                            n++;
//                        if (j == 4) {
//                            if (n == 4) {
//                                System.out.println(i + 1);
//                                war_label.setText("Лучшим из вариантов считается: №" + (i + 1));
//                                bestVariant = i+1;
//                            } else
//                                n = 0;
//                        }
//                    }
//                }
//                report.append("Лучшим из вариантов считается: №" + bestVariant + "\n\n\n");
//                reportButton.setDisable(false);
//            }
//            war_label.setVisible(true);
//        });
//
//    }
//
//    boolean isDiplicated() {
//        boolean isDupl = false;
//
//        if (variantBox.getValue().equals(variantBox1.getValue()) ||
//                variantBox.getValue().equals(variantBox2.getValue()) ||
//                variantBox.getValue().equals(variantBox3.getValue()) ||
//                variantBox1.getValue().equals(variantBox2.getValue()) ||
//                variantBox2.getValue().equals(variantBox3.getValue()) ||
//                variantBox.getValue().equals(variantBox4.getValue()) ||
//                variantBox1.getValue().equals(variantBox4.getValue())
//        ) isDupl = true;
//
//        return isDupl;
//    }
//}
//
//
//
