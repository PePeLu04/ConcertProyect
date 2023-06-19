package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.proyect.App;
import org.proyect.Model.DAO.DAOUser;
import org.proyect.Model.Domain.Band;
import org.proyect.Model.Domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Este es el controlador principal después del Login
//Únicamente tiene métodos para cambiar a diferentes vistas
public class PrimaryController {
    @FXML
    private Button menuButton;

    @FXML
    private VBox menu;

    @FXML
    private TextField idField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField dniField;

    @FXML
    private TextField bandField;
    @FXML
    private TextField idField1;
    @FXML
    private TextField idField3;

    @FXML
    private TextField usernameField1;

    @FXML
    private TextField passwordField1;

    @FXML
    private TextField roleField1;

    @FXML
    private TextField dniField1;
    @FXML
    private TextField bandField1;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> dniColumn;
    @FXML
    private TableColumn<User, String> bandColumn;
    @FXML
    private Label resultLabel;

    @FXML
    private TableView<Band> bandTableView;
    @FXML
    private TextField userTextField;

    DAOUser daoUser = new DAOUser();

    private ScheduledExecutorService scheduler;
    private static final int UPDATE_INTERVAL = 1;


    public void initialize() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::searchAllUsers, 0, UPDATE_INTERVAL, TimeUnit.SECONDS);
        menuButton.setOnAction(event -> toggleMenu());
    }

    @FXML
    private User insertUser(ActionEvent event) {
        try {
            String id = idField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();
            String dni = dniField.getText();
            String name_band = bandField.getText();

            User user = new User(id, username, password, role, dni, name_band);
            //Llama al metodo insert del DAO para insertar Instrumento
            daoUser.insert(user);

            System.out.println("Instrument inserted successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a number.");
        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @FXML
    private void deleteUser(ActionEvent event) {
        try {
            String id = idField3.getText();

            User user = daoUser.searchById(id);

            if (user != null) {
                //Llama al metodo delete del DAO para eliminar Instrumento
                daoUser.delete(user.getId());
                System.out.println("Instrument deleted successfully.");
            } else {
                System.out.println("Instrument with ID " + id + " not found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        }
    }

    @FXML
    private void updateUser() {
        String id = idField1.getText();
        String username = usernameField1.getText();
        String password = passwordField1.getText();
        String role = roleField1.getText();
        String dni = dniField1.getText();
        String name_band = bandField1.getText();

        try {
            User user = daoUser.searchById(id);
            if (user != null) {
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setDni(dni);
                user.setName_band(name_band);
                //Llama al metodo update del DAO para actualizar Instrumento
                daoUser.update(user);
                resultLabel.setText("Instrumento actualizado.");
            } else {
                resultLabel.setText("Instrumento no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al actualizar el instrumento.");
        }
    }

    @FXML
    private void searchUser() {
        String id = idField1.getText();
        try {
            User user = daoUser.searchById(id);
            if (user != null) {
                usernameField1.setText(user.getUsername());
                passwordField1.setText(user.getPassword());
                roleField1.setText(user.getRole());
                dniField1.setText(user.getDni());
                bandField1.setText(user.getName_band());
                resultLabel.setText("Instrumento encontrado.");
            } else {
                resultLabel.setText("Instrumento no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al buscar el instrumento.");
        }
    }
    public void searchAllUsers() {
        try {
            List<User> users = daoUser.findAll();

            // Asignar los valores a las columnas de la tabla
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
            dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
            bandColumn.setCellValueFactory(new PropertyValueFactory<>("name_band"));

            // Agregar los instrumentos a la tabla
            userTable.getItems().setAll(users);
        } catch (SQLException e) {
            // Manejar el error
        }
    }

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

    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
    }

    @FXML
    private void loginButton() throws IOException {
        App.setRoot("login");
    }
}
