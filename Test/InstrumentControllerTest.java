package org.proyect.Test;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.proyect.Controller.InstrumentController;
import org.proyect.Model.DAO.DAOInstrument;
import org.proyect.Model.Domain.Instrument;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InstrumentControllerTest {
    private InstrumentController instrumentController;

    @Mock
    private TextField idFieldMock;

    @Mock
    private TextField nameFieldMock;

    @Mock
    private TextField soundFieldMock;

    @Mock
    private TextField priceFieldMock;

    @Mock
    private TextField idField1Mock;

    @Mock
    private TextField idField3Mock;

    @Mock
    private TextField nameField1Mock;

    @Mock
    private TextField soundField1Mock;

    @Mock
    private TextField priceField1Mock;

    @Mock
    private TableView<Instrument> instrumentTableMock;

    @Mock
    private TableColumn<Instrument, String> idColumnMock;

    @Mock
    private TableColumn<Instrument, String> nameColumnMock;

    @Mock
    private TableColumn<Instrument, String> soundColumnMock;

    @Mock
    private TableColumn<Instrument, String> priceColumnMock;

    @Mock
    private Label resultLabelMock;

    @Mock
    private Button menuButtonMock;

    @Mock
    private VBox menuMock;

    @Mock
    private ScheduledExecutorService schedulerMock;

    @Mock
    private DAOInstrument daoInstrumentMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instrumentController = new InstrumentController();
        instrumentController.idField = idFieldMock;
        instrumentController.nameField = nameFieldMock;
        instrumentController.soundField = soundFieldMock;
        instrumentController.priceField = priceFieldMock;
        instrumentController.idField1 = idField1Mock;
        instrumentController.idField3 = idField3Mock;
        instrumentController.nameField1 = nameField1Mock;
        instrumentController.soundField1 = soundField1Mock;
        instrumentController.priceField1 = priceField1Mock;
        instrumentController.instrumentTable = instrumentTableMock;
        instrumentController.idColumn = idColumnMock;
        instrumentController.nameColumn = nameColumnMock;
        instrumentController.soundColumn = soundColumnMock;
        instrumentController.priceColumn = priceColumnMock;
        instrumentController.resultLabel = resultLabelMock;
        instrumentController.menuButton = menuButtonMock;
        instrumentController.menu = menuMock;
        instrumentController.scheduler = schedulerMock;
        instrumentController.daoInstrument = daoInstrumentMock;
    }

    @Test
    void testInsertInstrument() throws SQLException {
        // Arrange
        String id = "1";
        String name = "Instrument Name";
        String sound = "Instrument Sound";
        String price = "100.0";
        Instrument instrument = new Instrument(id, name, sound, price);

        // Act
        when(idFieldMock.getText()).thenReturn(id);
        when(nameFieldMock.getText()).thenReturn(name);
        when(soundFieldMock.getText()).thenReturn(sound);
        when(priceFieldMock.getText()).thenReturn(price);
        instrumentController.insertInstrument(null);

        // Assert
        verify(daoInstrumentMock, times(1)).insert(instrument);
    }

    @Test
    void testDeleteInstrument() throws SQLException {
        // Arrange
        String id = "1";
        Instrument instrument = new Instrument(id, "Instrument Name", "Instrument Sound", "100.0");
        when(idField3Mock.getText()).thenReturn(id);
        when(daoInstrumentMock.searchById(id)).thenReturn(instrument);

        // Act
        instrumentController.deleteInstrument(null);

        // Assert
        verify(daoInstrumentMock, times(1)).delete(id);
    }

    @Test
    void testUpdateInstrument() throws SQLException {
        // Arrange
        String id = "1";
        String name = "Updated Name";
        String sound = "Updated Sound";
        String price = "200.0";
        Instrument instrument = new Instrument(id, "Instrument Name", "Instrument Sound", "100.0");
        when(idField1Mock.getText()).thenReturn(id);
        when(nameField1Mock.getText()).thenReturn(name);
        when(soundField1Mock.getText()).thenReturn(sound);
        when(priceField1Mock.getText()).thenReturn(price);
        when(daoInstrumentMock.searchById(id)).thenReturn(instrument);

        // Act
        instrumentController.updateInstrument();

        // Assert
        assertEquals(name, instrument.getName());
        assertEquals(sound, instrument.getSound());
        assertEquals(price, instrument.getPrice());
        verify(daoInstrumentMock, times(1)).update(instrument);
    }

    @Test
    void testSearchInstrument() throws SQLException {
        // Arrange
        String id = "1";
        String name = "Instrument Name";
        String sound = "Instrument Sound";
        String price = "100.0";
        Instrument instrument = new Instrument(id, name, sound, price);
        when(idField1Mock.getText()).thenReturn(id);
        when(daoInstrumentMock.searchById(id)).thenReturn(instrument);

        // Act
        instrumentController.searchInstrument();

        // Assert
        verify(nameField1Mock, times(1)).setText(name);
        verify(soundField1Mock, times(1)).setText(sound);
        verify(priceField1Mock, times(1)).setText(price);
    }

    @Test
    void testSearchAllInstruments() throws SQLException {
        // Arrange
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument("1", "Instrument 1", "Sound 1", "100.0"));
        instruments.add(new Instrument("2", "Instrument 2", "Sound 2", "200.0"));
        when(daoInstrumentMock.findAll()).thenReturn(instruments);

        // Act
        instrumentController.searchAllInstruments();

        // Assert
        verify(idColumnMock, times(1)).setCellValueFactory(any());
        verify(nameColumnMock, times(1)).setCellValueFactory(any());
        verify(soundColumnMock, times(1)).setCellValueFactory(any());
        verify(priceColumnMock, times(1)).setCellValueFactory(any());
        verify(instrumentTableMock, times(1)).getItems().setAll(instruments);
    }

    // TODO: Add tests for other methods and edge cases

}
