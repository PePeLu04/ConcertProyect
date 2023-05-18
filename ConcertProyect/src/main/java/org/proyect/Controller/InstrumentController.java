package org.proyect.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import org.proyect.Model.DAO.DAOInstrument;
import org.proyect.Model.Domain.Instrument;

import java.sql.SQLException;
import java.util.List;


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
    private TableView<Instrument> instrumentTable;
    @FXML
    private TableColumn<Instrument, Integer> idColumn;
    @FXML
    private TableColumn<Instrument, String> nameColumn;
    @FXML
    private TableColumn<Instrument, String> soundColumn;
    @FXML
    private TableColumn<Instrument, Double> priceColumn;

    DAOInstrument daoInstrument = new DAOInstrument();

    @FXML
    private Instrument insertInstrument(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getId());
            String name = nameField.getText();
            String sound = soundField.getText();
            double price = Double.parseDouble(priceField.getText());

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
            int id = Integer.parseInt(idField.getText());

            Instrument entity = daoInstrument.searchById(id);

            if (entity != null) {
                daoInstrument.delete(entity.getId());
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
    public void updateInstrument(int id, String name, String sound, double price) throws SQLException {
        Instrument instrument = daoInstrument.searchById(id);
        instrument.setName(name);
        instrument.setSound(sound);
        instrument.setPrice(price);
        daoInstrument.update(instrument);
    }

    @FXML
    public Instrument searchInstrumentById(int id) throws SQLException {
        return daoInstrument.searchById(id);
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
}

