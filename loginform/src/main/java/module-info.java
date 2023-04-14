module login.loginform {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens login.loginform to javafx.fxml;
    exports login.loginform;
}
