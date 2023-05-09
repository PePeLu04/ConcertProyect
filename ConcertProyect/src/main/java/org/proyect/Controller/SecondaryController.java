package org.proyect.Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.proyect.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}