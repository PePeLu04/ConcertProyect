module com.example.loginsfx1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.loginsfx1 to javafx.fxml;
    exports com.example.loginsfx1;
}