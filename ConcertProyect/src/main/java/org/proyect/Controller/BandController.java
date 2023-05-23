package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.proyect.App;
import org.proyect.Model.DAO.DAOBand;
import org.proyect.Model.Domain.Band;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BandController {
    @FXML
    private TableView<Band> bandTable;
    @FXML
    private TableColumn<Band, String> idColumn;
    @FXML
    private TableColumn<Band, String> nameColumn;
    @FXML
    private TableColumn<Band, String> componentsColumn;

    @FXML
    private Label resultLabel;
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField componentsField;
    @FXML
    private TextField idField3;

    @FXML
    private TextField nameField3;

    @FXML
    private TextField componentsField3;
    @FXML
    private TextField idField1;

    @FXML
    private TextField nameField1;

    @FXML
    private TextField componentsField1;
    DAOBand daoBand = new DAOBand();
    @FXML
    private Button menuButton;

    @FXML
    private VBox menu;
    private ScheduledExecutorService scheduler;
    private static final int UPDATE_INTERVAL = 1;
    public void initialize() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::searchAllBands, 0, UPDATE_INTERVAL, TimeUnit.SECONDS);
        menuButton.setOnAction(event -> toggleMenu());
    }


    @FXML
    private void toggleMenu() {
        menu.setVisible(!menu.isVisible());
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
    private Band insertBand(ActionEvent event) {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String components = componentsField.getText();

            Band band = new Band(id, name, components);
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

    @FXML
    private void deleteBand(ActionEvent event) {
        try {
            String id = idField3.getText();System.out.println(id);

            Band band = daoBand.searchById(id);

            if (band != null) {
                daoBand.delete(band.getId());
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
    private void updateBand() {
        String id = idField1.getText();
        String name = nameField1.getText();
        String components = componentsField1.getText();

        try {
            Band band = daoBand.searchById(id);
            if (band != null) {
                band.setName(name);
                band.setComponents(components);
                daoBand.update(band);
                resultLabel.setText("Banda actualizado.");
            } else {
                resultLabel.setText("Banda no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al actualizar la banda.");
        }
    }

    @FXML
    private void searchBand() throws SQLException {
        String id = idField1.getText();
        try {
            Band band = daoBand.searchById(id);
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

    public void searchAllBands() {
        try {
            List<Band> bands = daoBand.findAll();

            // Asignar los valores a las columnas de la tabla
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
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
