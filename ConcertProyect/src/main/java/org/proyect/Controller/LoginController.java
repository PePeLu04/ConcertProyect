package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyect.App;
import org.proyect.Model.Connections.ConnectionMySql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField dniField;

    private Connection connection;
    private static Scene scene;
    /**
     * Establece la ventana principal de la aplicación.
     *
     * @param stage La ventana principal
     */
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método inicializador del controlador.
     * Se ejecuta automáticamente al cargar el controlador y establece la conexión a la base de datos.
     */
    public void initialize() {
        // Establecer la conexión a la base de datos
        connection = ConnectionMySql.getConnect();
    }

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param event El evento de acción del botón de inicio de sesión
     * @throws IOException  Si ocurre un error de E/S durante la redirección de la vista
     * @throws SQLException Si ocurre un error de SQL durante la verificación de las credenciales
     */
    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dni = dniField.getText();

        // Verificar las credenciales de inicio de sesión
            if (handleLogin(username, password, dni)) {
                showAlert("Login Correcto","Bienvenido" + username);
            }
    }

    /**
     * Verifica las credenciales de inicio de sesión en la base de datos.
     *
     * @param username El nombre de usuario
     * @param password La contraseña
     * @param dni      El DNI
     * @return true si las credenciales son válidas y se cambia a la interfaz correspondiente, false en caso contrario
     */
    private boolean handleLogin(String username, String password, String dni) {

        // Verificar las credenciales en la base de datos
        try {
            String query = "SELECT role FROM users WHERE username = ? AND password = ? AND dni = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                switch (role) {
                    case "admin":
                        switchToAdmin();
                        break;
                    case "user":
                        switchToUser();
                        break;
                    default:
                        showAlert("Rol desconocido", "No se reconoce el rol del usuario.");
                        break;
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error de autenticación", "No se pudo autenticar al usuario.");
        }
        return false;
    }

    /**
     * Cambia a la interfaz de usuario.
     *
     * @throws IOException Si ocurre un error de E/S durante la redirección de la vista
     */
    @FXML
    private void switchToUser() throws IOException {
        App.setRoot("primaryuser");
    }
    /**
     * Cambia a la interfaz de administrador.
     *
     * @throws IOException Si ocurre un error de E/S durante la redirección de la vista
     */
    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("primary");
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param username El nombre de usuario
     * @param password La contraseña
     * @param dni      El DNI
     * @return true si el usuario se crea correctamente, false en caso contrario
     */

    private boolean handleCreateUser(String username, String password, String dni) {

        // Crear un nuevo usuario en la base de datos
        try {
            String query = "INSERT INTO users (username, password, role, dni) VALUES (?, ?, 'user', ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, dni);
            statement.executeUpdate();
            statement.close();

            showAlert("Usuario creado", "Se ha creado un nuevo usuario con éxito.");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error al crear usuario", "No se pudo crear el usuario. Por favor, inténtelo nuevamente.");
        }
        return false;
    }

    /**
     * Limpia los campos de texto.
     */

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        dniField.clear();
    }

    /**
     * Maneja la creación de una nueva cuenta de usuario.
     *
     * @param event El evento de acción del botón de creación de cuenta
     */
    @FXML
    private void createAccount(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dni = dniField.getText();

        //Si se insertan los campo se crea la cuenta
        if (handleCreateUser(username, password, dni)) {
            showAlert("Cuenta creada", "¡La cuenta se ha creado exitosamente!");
        }
    }

    /**
     * Muestra una ventana emergente de alerta con el título y el mensaje especificados.
     *
     * @param title   El título de la alerta
     * @param message El mensaje de la alerta
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

