module org.proyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens org.proyect to javafx.fxml;
    exports org.proyect;
    exports org.proyect.Controller;
    opens org.proyect.Controller to javafx.fxml;
}
