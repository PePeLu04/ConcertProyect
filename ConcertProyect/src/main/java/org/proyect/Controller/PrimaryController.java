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

// Importaciones necesarias

public class PrimaryController {

    // Declaración de otros componentes de la interfaz de usuario
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

    DAOUser daoUser = new DAOUser(); // Instancia de DAOUser para interactuar con la base de datos

    private ScheduledExecutorService scheduler;
    private static final int UPDATE_INTERVAL = 1; // Intervalo de actualización para el método searchAllUsers()


    public void initialize() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::searchAllUsers, 0, UPDATE_INTERVAL, TimeUnit.SECONDS);
        // Inicialización del scheduler para ejecutar periódicamente searchAllUsers()
        menuButton.setOnAction(event -> toggleMenu()); // Asignación de un controlador de eventos al botón menuButton
    }

    // Métodos de acción para interactuar con la interfaz de usuario y la base de datos

    /**
     * Método invocado al hacer clic en el botón de insertar usuario.
     * Inserta un nuevo usuario en la base de datos utilizando los valores proporcionados en los campos de texto.
     *
     * @param event El evento de acción
     * @return null
     */
    @FXML
    private User insertUser(ActionEvent event) {
        // Método para insertar un nuevo usuario en la base de datos
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

    /**
     * Método invocado al hacer clic en el botón de eliminar usuario.
     * Elimina un usuario de la base de datos utilizando el ID proporcionado en el campo de texto.
     *
     * @param event El evento de acción
     */
    @FXML
    private void deleteUser(ActionEvent event) {
        // Método para eliminar un usuario de la base de datos
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

    /**
     * Método invocado al hacer clic en el botón de actualizar usuario.
     * Actualiza la información de un usuario en la base de datos utilizando los valores proporcionados en los campos de texto.
     */
    @FXML
    private void updateUser() {
        // Método para actualizar la información de un usuario en la base de datos
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
                //Llama al metodo update del DAO para actualizar Usuario
                daoUser.update(user);
                resultLabel.setText("Instrumento actualizado.");
            } else {
                resultLabel.setText("Instrumento no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al actualizar el instrumento.");
        }
    }

    /**
     * Método invocado al hacer clic en el botón de buscar usuario.
     * Busca y muestra la información de un usuario en la interfaz de usuario utilizando el ID proporcionado en el campo de texto.
     */
    @FXML
    private void searchUser() {
        // Método para buscar y mostrar la información de un usuario en la interfaz de usuario
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

    /**
     * Método que busca y muestra la información de todos los usuarios en la interfaz de usuario.
     * Se invoca periódicamente utilizando un scheduler para actualizar la tabla de usuarios.
     */
    public void searchAllUsers() {
        // Método para buscar y mostrar la información de todos los usuarios en la interfaz de usuario
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

    // Métodos para cambiar a diferentes vistas de la aplicación

    /**
     * Método invocado al hacer clic en el botón para cambiar a la vista de bandas.
     * Cambia la vista actual a "band".
     *
     * @throws IOException Si ocurre un error al cambiar la vista
     */
    @FXML
    private void switchToSecondary() throws IOException {
        // Método para cambiar a la vista "bandas"
        App.setRoot("band");
    }

    /**
     * Método invocado al hacer clic en el botón para cambiar a la vista de instrumentos.
     * Cambia la vista actual a "central".
     *
     * @throws IOException Si ocurre un error al cambiar la vista
     */
    @FXML
    private void switchToThird() throws IOException {
        // Método para cambiar a la vista "instrumentos"
        App.setRoot("central");
    }

    @FXML
    private void switchToSecondaryUser() throws IOException {
        // Método para cambiar a la vista "bandUser"
        App.setRoot("bandUser");
    }

    @FXML
    private void switchToThirdUser() throws IOException {
        // Método para cambiar a la vista "centralUser"
        App.setRoot("centralUser");
    }

    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
    } // Método para mostrar u ocultar el menú

    /**
     * Método invocado al hacer clic en el botón de inicio de sesión.
     * Cambia la vista actual a "login".
     *
     * @throws IOException Si ocurre un error al cambiar la vista
     */
    @FXML
    private void loginButton() throws IOException {
        // Método para cambiar a la vista "login"
        App.setRoot("login");
    }
}
