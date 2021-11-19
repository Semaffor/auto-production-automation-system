module MyApp {
    requires java.naming;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.logging.log4j;
    requires static lombok;
    requires java.sql;
    requires java.desktop;

    opens by.bsuir.app.controllers.submenu to javafx.fxml;
    opens by.bsuir.app.controllers.others to javafx.fxml;
    opens by.bsuir.app.controllers to javafx.fxml;

    opens by.bsuir.app;
    opens by.bsuir.app.util;
}
