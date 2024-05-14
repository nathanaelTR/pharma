module fr.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens fr.app to javafx.fxml;
    exports fr.app;
}
