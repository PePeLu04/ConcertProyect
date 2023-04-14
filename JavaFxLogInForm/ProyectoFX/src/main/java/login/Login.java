import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    private static final String LOGIN_FXML = "login.fxml";
    private static final String ADMIN_FXML = "admin.fxml";
    private static final String USER_FXML = "user.fxml";

    private Stage stage;
    private String cssPath;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setTitle("Login");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML));
        loader.setController(this);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleLoginButton() throws Exception {
        FXMLLoader loader;
        Parent root;

        String username = ((TextField) stage.getScene().lookup("#username")).getText();
        String password = ((TextField) stage.getScene().lookup("#password")).getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Por favor, introduce nombre de usuario y contraseña.");
            return;
        }

        if (username.equals("admin") && password.equals("admin")) {
            loader = new FXMLLoader(getClass().getResource(ADMIN_FXML));
            loader.setController(new AdminController(this));

            root = loader.load();
            stage.setScene(new Scene(root));
        } else if (username.equals("usuario") && password.equals("usuario")) {
            loader = new FXMLLoader(getClass().getResource(USER_FXML));
            loader.setController(new UserController(this));

            root = loader.load();
            stage.setScene(new Scene(root));
        } else {
            showError("Usuario o contraseña incorrectos.");
            return;
        }

        stage.setTitle("Bienvenido, " + username + "!");
        stage.centerOnScreen();
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(cssPath);
    }

    public String getCssPath() {
        return cssPath;
    }

    public void handleExitButton() {
        stage.close();
    }
}
