package by.bsuir.app.controllers;

import by.bsuir.app.services.GeneralFuncWindow;
import by.bsuir.app.util.constants.WindowsPaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimpleClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button profile_button;

    @FXML
    private Button catalog_button;

    @FXML
    private Button history_button;

    @FXML
    private Button settings_button;

    @FXML
    private Button feedback_button;

    @FXML
    private Button exit_button;

    @FXML
    private Pane pane;

    @FXML
    private Button statistic_button;

    @FXML
    private Label social_label;

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

        history_button.setOnAction(actionEvent -> {

            pane.getChildren().remove(node[0]);
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowHistory));
                pane.getChildren().add(node[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        catalog_button.setOnAction(actionEvent -> {
            GeneralFuncWindow.openNewScene(WindowsPaths.WindowCatalog);
        });

        feedback_button.setOnAction(actionEvent -> {
            try {
                    pane.getChildren().remove(node[0]);
                    node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowFeedback));
                    pane.getChildren().add(node[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        statistic_button.setOnAction(actionEvent -> {
            try {
                pane.getChildren().remove(node[0]);
                node[0] = (Node) FXMLLoader.load(getClass().getResource(WindowsPaths.WindowChart));
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
