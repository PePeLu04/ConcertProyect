package org.proyect.Test;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.proyect.Controller.BandController;
import org.proyect.Model.DAO.DAOBand;
import org.proyect.Model.Domain.Band;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BandControllerTest extends BandController {
    private BandController bandController;

    @Mock
    private TableView<Band> bandTableMock;

    @Mock
    private TableColumn<Band, String> idColumnMock;

    @Mock
    private TableColumn<Band, String> nameColumnMock;

    @Mock
    private TableColumn<Band, String> componentsColumnMock;

    @Mock
    private TextField idFieldMock;

    @Mock
    private TextField nameFieldMock;

    @Mock
    private TextField componentsFieldMock;

    @Mock
    private DAOBand daoBandMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bandController = new BandController();
        bandController.bandTable = bandTableMock;
        bandController.idColumn = idColumnMock;
        bandController.nameColumn = nameColumnMock;
        bandController.componentsColumn = componentsColumnMock;
        bandController.idField = idFieldMock;
        bandController.nameField = nameFieldMock;
        bandController.componentsField = componentsFieldMock;
        bandController.daoBand = daoBandMock;
    }

    @Test
    void testInsertBand() throws SQLException {
        // Arrange
        String id = "1";
        String name = "Band Name";
        String components = "Component 1, Component 2";
        Band band = new Band(id, name, components);

        // Act
        when(idFieldMock.getText()).thenReturn(id);
        when(nameFieldMock.getText()).thenReturn(name);
        when(componentsFieldMock.getText()).thenReturn(components);
        bandController.insertBand(null);

        // Assert
        verify(daoBandMock, times(1)).insert(band);
    }

    @Test
    void testDeleteBand() throws SQLException {
        // Arrange
        String id = "1";
        Band band = new Band(id, "Band Name", "Component 1, Component 2");
        when(idFieldMock.getText()).thenReturn(id);
        when(daoBandMock.searchById(id)).thenReturn(band);

        // Act
        bandController.deleteBand(null);

        // Assert
        verify(daoBandMock, times(1)).delete(id);
    }

    @Test
    void testUpdateBand() throws SQLException {
        // Arrange
        String id = "1";
        String newName = "New Band Name";
        String newComponents = "New Component 1, New Component 2";
        Band band = new Band(id, "Band Name", "Component 1, Component 2");
        when(daoBandMock.searchById(id)).thenReturn(band);

        // Act
        when(idFieldMock.getText()).thenReturn(id);
        when(nameFieldMock.getText()).thenReturn(newName);
        when(componentsFieldMock.getText()).thenReturn(newComponents);
        bandController.updateBand();

        // Assert
        assertEquals(newName, band.getName());
        assertEquals(newComponents, band.getComponents());
        verify(daoBandMock, times(1)).update(band);
    }

    @Test
    void testSearchBand() throws SQLException {
        // Arrange
        String id = "1";
        Band band = new Band(id, "Band Name", "Component 1, Component 2");
        when(idFieldMock.getText()).thenReturn(id);
        when(daoBandMock.searchById(id)).thenReturn(band);

        // Act
        bandController.searchBand();

        // Assert
        verify(nameFieldMock, times(1)).setText(band.getName());
        verify(componentsFieldMock, times(1)).setText(band.getComponents());
    }

    @Test
    void testSearchAllBands() throws SQLException {
        // Arrange
        List<Band> bands = new ArrayList<>();
        bands.add(new Band("1", "Band 1", "Component 1, Component 2"));
        bands.add(new Band("2", "Band 2", "Component 3, Component 4"));
        when(daoBandMock.findAll()).thenReturn(bands);

        // Act
        bandController.searchAllBands();

        // Assert
        verify(bandTableMock, times(1)).getItems().setAll(bands);
    }
}

