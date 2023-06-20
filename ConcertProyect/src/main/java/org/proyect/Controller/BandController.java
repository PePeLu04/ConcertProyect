package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.proyect.App;
import org.proyect.Model.DAO.DAOBand;
import org.proyect.Model.Domain.Band;
import org.proyect.Model.Domain.Instrument;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controlador para la vista de gestión de bandas.
 */
public class BandController {
    @FXML
    private TableView<Band> bandTable;
    @FXML
    private TableColumn<Band, String> idColumn;
    @FXML
    private TableColumn<Band, String> nameColumn;
    @FXML
    private TableColumn<Band, String> componentsColumn;

    // Otros componentes de la interfaz de usuario

    @FXML
    private Label resultLabel;

    // Declaración de otros campos de texto
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField componentsField;
    @FXML
    private TextField idField3;
    @FXML
    private TableColumn<Instrument, String> instrumentIdColumn;
    @FXML
    private TableColumn<Instrument, String> instrumentNameColumn;
    @FXML
    private TextField idField1;

    @FXML
    private TextField nameField1;

    @FXML
    private TextField componentsField1;
    DAOBand daoBand = new DAOBand(); // Instancia de DAOBand para interactuar con la base de datos
    @FXML
    private Button menuButton;

    @FXML
    private VBox menu;
    private ScheduledExecutorService scheduler;
    private static final int UPDATE_INTERVAL = 1;
    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     */
    public void initialize() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::searchAllBands, 0, UPDATE_INTERVAL, TimeUnit.SECONDS);
        // Inicialización del scheduler para ejecutar periódicamente searchAllBands()
        menuButton.setOnAction(event -> toggleMenu());  // Asignación de un controlador de eventos al botón menuButton
    }


    /**
     * Método para mostrar u ocultar el menú.
     */
    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
    }

    // Métodos para cambiar a diferentes vistas de la aplicación
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

    /**
     * Método para insertar una banda en la base de datos.
     *
     * @param event Evento que desencadena la acción.
     * @return La banda insertada, o null si no se pudo insertar.
     */
    @FXML
    private Band insertBand(ActionEvent event) {
        try {
            String band_id = idField.getText();
            String name = nameField.getText();
            String components = componentsField.getText();

            Band band = new Band(band_id, name, components);
            //Llama al metodo insert del DAO para insertar Banda
            daoBand.insert(band);

            System.out.println("Band inserted successfully.");

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
     * Método para eliminar una banda de la base de datos.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void deleteBand(ActionEvent event) {
        try {
            String band_id = idField3.getText();System.out.println(band_id);

            Band band = daoBand.searchById(band_id);

            if (band != null) {
                //Llama al metodo delete del DAO para eliminar Banda
                daoBand.delete(band.getBand_id());
                System.out.println("Instrument deleted successfully.");
            } else {
                System.out.println("Instrument with ID " + band_id + " not found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        }
    }

    /**
     * Método para actualizar la información de una banda en la base de datos.
     */
    @FXML
    private void updateBand() {
        String band_id = idField1.getText();
        String name = nameField1.getText();
        String components = componentsField1.getText();

        try {
            Band band = daoBand.searchById(band_id);
            if (band != null) {
                band.setName(name);
                band.setComponents(components);
                //Llama al metodo update del DAO para actualizar Banda
                daoBand.update(band);
                resultLabel.setText("Banda actualizado.");
            } else {
                resultLabel.setText("Banda no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al actualizar la banda.");
        }
    }

    /**
     * Método para buscar y mostrar la información de una banda en la interfaz de usuario.
     *
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    @FXML
    private void searchBand() throws SQLException {
        String band_id = idField1.getText();
        try {
            Band band = daoBand.searchById(band_id);
            if (band != null) {
                nameField1.setText(band.getName());
                componentsField1.setText(band.getComponents());
                resultLabel.setText("Banda encontrada.");
            } else {
                resultLabel.setText("Banda no encontrada.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al buscar la banda.");
        }
    }

    /**
     * Método para buscar y mostrar la información de todas las bandas en la interfaz de usuario.
     */
    public void searchAllBands() {
        try {
            List<Band> bands = daoBand.findAll();

            // Asignar los valores a las columnas de la tabla
            idColumn.setCellValueFactory(new PropertyValueFactory<>("band_id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            componentsColumn.setCellValueFactory(new PropertyValueFactory<>("components"));

            // Agregar las bandas a la tabla
            bandTable.getItems().setAll(bands);
        } catch (SQLException e) {
            // Manejar el error
        }
    }

    @FXML
    private void loginButton() throws IOException {
        App.setRoot("login");
    }
}
