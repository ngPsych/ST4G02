module Javafxwoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;

    opens Controller to app.fxml, javafx.fxml;

    exports Controller;
}