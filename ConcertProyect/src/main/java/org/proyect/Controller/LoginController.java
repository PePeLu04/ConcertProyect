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
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        // Establecer la conexión a la base de datos
        connection = ConnectionMySql.getConnect();
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dni = dniField.getText();

        //Asigna los roles para llegar a una vista diferente creada posteriormente
            if (handleLogin(username, password, dni)) {
                showAlert("Login Correcto","Bienvenido" + username);
            }
    }

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

    @FXML
    private void switchToUser() throws IOException {
        App.setRoot("primaryuser");
    }
    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("primary");
    }

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

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        dniField.clear();
    }
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


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

