package login;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserController {
    private Login login;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    public UserController(Login login) {
        this.login = login;
    }

    @FXML
    public void handleSaveButton() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            login.showError("Por favor, completa todos los campos.");
            return;
        }

        // Aquí se podría guardar la información del usuario en una base de datos o en un archivo, por ejemplo.
        // En este ejemplo simplemente imprimimos los datos por consola.
        System.out.println("Nombre: " + name);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + phone);

        login.showInfo("Perfil guardado correctamente.");
    }

    @FXML
    public void handleCssButton() {
        login.setCssPath("user-style.css");
    }

    @FXML
    public void handleExitButton() {
        login.handleExitButton();
    }
}
