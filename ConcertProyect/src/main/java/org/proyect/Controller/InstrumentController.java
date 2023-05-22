package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.proyect.App;
import org.proyect.Model.DAO.DAOInstrument;
import org.proyect.Model.Domain.Instrument;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InstrumentController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField soundField;

    @FXML
    private TextField priceField;
    @FXML
    private TextField idField1;
    @FXML
    private TextField idField3;

    @FXML
    private TextField nameField1;

    @FXML
    private TextField soundField1;

    @FXML
    private TextField priceField1;
    @FXML
    private TableView<Instrument> instrumentTable;
    @FXML
    private TableColumn<Instrument, String> idColumn;
    @FXML
    private TableColumn<Instrument, String> nameColumn;
    @FXML
    private TableColumn<Instrument, String> soundColumn;
    @FXML
    private TableColumn<Instrument, String> priceColumn;
    @FXML
    private Label resultLabel;
    @FXML
    private Button menuButton;

    @FXML
    private VBox menu;
    private ScheduledExecutorService scheduler;
    private static final int UPDATE_INTERVAL = 1;
    public void initialize() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::searchAllInstruments, 0, UPDATE_INTERVAL, TimeUnit.SECONDS);
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


    DAOInstrument daoInstrument = new DAOInstrument();


    @FXML
    private Instrument insertInstrument(ActionEvent event) {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String sound = soundField.getText();
            String price = priceField.getText();

            Instrument instrument = new Instrument(id, name, sound, price);
            daoInstrument.insert(instrument);

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
    private void deleteInstrument(ActionEvent event) {
        try {
            String id = idField3.getText();System.out.println(id);

            Instrument instrument = daoInstrument.searchById(id);

            if (instrument != null) {
                daoInstrument.delete(instrument.getId());
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
    private void updateInstrument() {
        String id = idField1.getText();
        String name = nameField1.getText();
        String sound = soundField1.getText();
        String price = priceField1.getText();

        try {
            Instrument instrument = daoInstrument.searchById(id);
            if (instrument != null) {
                instrument.setName(name);
                instrument.setSound(sound);
                instrument.setPrice(price);
                daoInstrument.update(instrument);
                resultLabel.setText("Instrumento actualizado.");
            } else {
                resultLabel.setText("Instrumento no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al actualizar el instrumento.");
        }
    }

    @FXML
    private void searchInstrument() {
        String id = idField1.getText();
        try {
            Instrument instrument = daoInstrument.searchById(id);
            if (instrument != null) {
                nameField1.setText(instrument.getName());
                soundField1.setText(instrument.getSound());
                priceField1.setText(instrument.getPrice());
                resultLabel.setText("Instrumento encontrado.");
            } else {
                resultLabel.setText("Instrumento no encontrado.");
            }
        } catch (SQLException e) {
            resultLabel.setText("Error al buscar el instrumento.");
        }
    }
    public void searchAllInstruments() {
        try {
            List<Instrument> instruments = daoInstrument.findAll();

            // Asignar los valores a las columnas de la tabla
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            soundColumn.setCellValueFactory(new PropertyValueFactory<>("sound"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            // Agregar los instrumentos a la tabla
            instrumentTable.getItems().setAll(instruments);
        } catch (SQLException e) {
            // Manejar el error
        }
    }
    @FXML
    private void loginButton() throws IOException {
        App.setRoot("login");
    }
}

