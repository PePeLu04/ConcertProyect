package com.example.loginsfx1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button adminButton;

    @FXML
    private Button userButton;

    @FXML
    private Label errorMessageLabel;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void handleAdminButton() {
        if (isValidUser() && isValidPassword()) {
            mainApp.showAdmin();
        } else {
            errorMessageLabel.setText("Usuario o contraseña incorrectos.");
        }
    }

    public void handleUserButton() {
        if (isValidUser() && isValidPassword()) {
            mainApp.showUser();
        } else {
            errorMessageLabel.setText("Usuario o contraseña incorrectos.");
        }
    }

    private boolean isValidUser() {
        // Validar el usuario ingresado
        return true;
    }

    private boolean isValidPassword() {
        // Validar la contraseña ingresada
        return true;
    }
}
