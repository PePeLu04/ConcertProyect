package org.proyect.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.proyect.App;

public class PrimaryController {
    @FXML
    private Button menuButton;

    @FXML
    private VBox menu;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("band");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("central");
    }
    @FXML
    private void switchToSecondaryUser() throws IOException {
        App.setRoot("bandUser");
    }

    @FXML
    private void switchToThirdUser() throws IOException {
        App.setRoot("centralUser");
    }
    public void initialize() {
        menuButton.setOnAction(event -> toggleMenu());
    }

    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
    }

    @FXML
    private void loginButton() throws IOException {
        App.setRoot("login");
    }
}
