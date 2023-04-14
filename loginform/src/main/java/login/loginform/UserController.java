package login.loginform;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserController {

    @FXML
    private Label usernameLabel;


    public void initialize() {
        // TODO: Load user data and update the UI accordingly        usernameLabel.setText("user");
    }

}
