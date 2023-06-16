package org.proyect.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.proyect.App;

import java.io.IOException;

//Este es el controlador principal después del Login
//Únicamente tiene métodos para cambiar a diferentes vistas
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
    private void switchToNM() throws IOException {
        App.setRoot("NM");
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
