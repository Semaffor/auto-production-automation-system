module MyApp {
    requires java.naming;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.logging.log4j;
    requires static lombok;

    opens by.bsuir.app.controllers.others to javafx.fxml;
    opens by.bsuir.app.controllers to javafx.fxml;

    opens by.bsuir.app;
}
