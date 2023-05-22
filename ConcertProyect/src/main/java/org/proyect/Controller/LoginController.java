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
    private TextField roleField;

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

        if (authenticateUser(username, password)) {
            String role = getRole(username);
            if (role.equals("admin")) {
                System.out.println("Admin login successful");
                switchToAdmin();
            } else if (role.equals("user")) {
                System.out.println("User login successful");
                switchToUser();
            }
        } else {
            System.out.println("Login failed");
        }
    }
    @FXML
    private void switchToUser() throws IOException {
        App.setRoot("primaryuser");
    }
    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void createAccount(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleField.getText();

        if (createUser(username, password, role)) {
            showAlert("Cuenta creada", "¡La cuenta se ha creado exitosamente!");
        } else {
            showAlert("Error al crear cuenta", "No se pudo crear la cuenta. Inténtalo de nuevo.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Si el usuario existe en la base de datos, el resultado tendrá al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getRole(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            } else {
                return "";
            }
    }

    private boolean createUser(String username, String password, String role) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0; // Si se insertó al menos una fila, se creó el usuario correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

