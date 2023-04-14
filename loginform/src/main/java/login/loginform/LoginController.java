package login.loginform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;

    public LoginController(Stage stage) {
        this.stage = stage;
    }

    public void handleLoginButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        FXMLLoader loader;
        Parent root = null;

        showError("Por favor, introduce nombre de usuario y contraseña.");

            if (username.equals("admin") && password.equals("admin")) {
                loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                stage.setTitle("Admin");
                stage.setScene(new Scene(root, 300, 200));
                stage.show();

                root = loader.load();
                stage.setScene(new Scene(root));

            } else if (username.equals("usuario") && password.equals("usuario")) {
                loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                stage.setTitle("Usuario");
                stage.setScene(new Scene(root, 300, 200));
                stage.show();

                root = loader.load();
                stage.setScene(new Scene(root));
            }else{
                showError("Error");
            }


    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


}
