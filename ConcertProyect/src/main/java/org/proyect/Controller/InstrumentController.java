package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.proyect.App;
import org.proyect.Model.DAO.DAOInstrument;
import org.proyect.Model.Domain.Band;
import org.proyect.Model.Domain.Instrument;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private TextField bandField;
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
    private TextField bandField1;
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
    private TableColumn<Instrument, String> bandColumn;
    @FXML
    private Label resultLabel;
    @FXML
    private Button menuButton;

    @FXML
    private TableView<Band> bandTableView;
    @FXML
    private TextField instrumentTextField;

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

    @FXML
    private void switchToSecondaryUser() throws IOException {
        App.setRoot("bandUser");
    }

    @FXML
    private void switchToThirdUser() throws IOException {
        App.setRoot("centralUser");
    }

    DAOInstrument daoInstrument = new DAOInstrument();


    @FXML
    private Instrument insertInstrument(ActionEvent event) {
        try {
            String instr_id = idField.getText();
            String name = nameField.getText();
            String sound = soundField.getText();
            String price = priceField.getText();
            String name_band = bandField.getText();

            Instrument instrument = new Instrument(instr_id, name, sound, price, name_band);
            //Llama al metodo insert del DAO para insertar Instrumento
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
            String instr_id = idField3.getText();

            Instrument instrument = daoInstrument.searchById(instr_id);

            if (instrument != null) {
                //Llama al metodo delete del DAO para eliminar Instrumento
                daoInstrument.delete(instrument.getInstr_id());
                System.out.println("Instrument deleted successfully.");
            } else {
                System.out.println("Instrument with ID " + instr_id + " not found.");
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
        String instr_id = idField1.getText();
        String name = nameField1.getText();
        String sound = soundField1.getText();
        String price = priceField1.getText();
        String name_band = bandField1.getText();

        try {
            Instrument instrument = daoInstrument.searchById(instr_id);
            if (instrument != null) {
                instrument.setName(name);
                instrument.setSound(sound);
                instrument.setPrice(price);
                instrument.setName_band(name_band);
                //Llama al metodo update del DAO para actualizar Instrumento
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
        String instr_id = idField1.getText();
        try {
            Instrument instrument = daoInstrument.searchById(instr_id);
            if (instrument != null) {
                nameField1.setText(instrument.getName());
                soundField1.setText(instrument.getSound());
                priceField1.setText(instrument.getPrice());
                bandField1.setText(instrument.getName_band());
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
            idColumn.setCellValueFactory(new PropertyValueFactory<>("instr_id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            soundColumn.setCellValueFactory(new PropertyValueFactory<>("sound"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            bandColumn.setCellValueFactory(new PropertyValueFactory<>("name_band"));

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

