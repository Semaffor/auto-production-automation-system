package by.bsuir.app.controllers;

import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.constants.WindowsPaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane;

    @FXML
    private Button profile_button;

    @FXML
    private Button catalog_button;

    @FXML
    private Button management_button;

    @FXML
    private Button task_button;

    @FXML
    private Button feedback_button;

    @FXML
    private Button settings_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button statisticButton;

    @FXML
    void handleClose(MouseEvent event) {
        GeneralFuncWindow.closeApplication();
    }

    @FXML
    void initialize() {
        final Node[] node = {null};

        profile_button.setOnAction(actionEvent -> {
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowProfile));
                pane.getChildren().add(node[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        management_button.setOnAction(actionEvent -> {
                GeneralFuncWindow.openNewScene(WindowsPaths.WindowManagement);
        });

        catalog_button.setOnAction(actionEvent -> {
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowCatalog));
                pane.getChildren().add(node[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        statisticButton.setOnAction(actionEvent -> {
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowActivityChart));
                pane.getChildren().add(node[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        feedback_button.setOnAction(actionEvent -> {

            pane.getChildren().remove(node[0]);
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowHistory));
                pane.getChildren().add(node[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exit_button.setOnAction(actionEvent -> {
            Stage stage = (Stage) exit_button.getScene().getWindow();
            stage.close();
        });
    }
}
