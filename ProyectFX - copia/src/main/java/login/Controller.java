package login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private static final String ADMIN_FXML = "/admin.fxml";
    private static final String USER_FXML = "/user.fxml";
    private static final String ADMIN_ROLE = "Admin";
    private static final String USER_ROLE = "Usuario";
    private static final String ERROR_TITLE = "Error";
    private static final String ERROR_HEADER = "Credenciales incorrectas";
    private static final String ERROR_CONTENT_TEXT = "Usuario o contraseña incorrectos";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User(username, password);

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ERROR_TITLE, ERROR_HEADER, ERROR_CONTENT_TEXT);
            return;
        }

        if (user.getRole().equals(ADMIN_ROLE)) {
            showAdminWindow();
        } else if (user.getRole().equals(USER_ROLE)) {
            showUserWindow();
        } else {
            showAlert(Alert.AlertType.ERROR, ERROR_TITLE, ERROR_HEADER, ERROR_CONTENT_TEXT);
        }
    }

    private void showAdminWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ADMIN_FXML));
            primaryStage.setTitle("Admin");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUserWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(USER_FXML));
            primaryStage.setTitle("Usuario");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void handleExitButton() {
        primaryStage.close();
    }

}