module org.proyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.proyect to javafx.fxml;
    exports org.proyect;
}
