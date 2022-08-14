module edu.lawrence.mazedrawing {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.mazedrawing to javafx.fxml;
    exports edu.lawrence.mazedrawing;
}
