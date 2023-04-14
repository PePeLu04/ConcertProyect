package com.example.loginsfx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;

    private BorderPane rootLayout;

    private LoginController loginController;

    private Object adminController;

    private UserController userController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");

        initRootLayout();

        showLogin();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);

            loginController = loader.getController();
            loginController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Admin.fxml"));
            AnchorPane admin = (AnchorPane) loader.load();

            rootLayout.setCenter(admin);

            adminController = loader.getController();
            adminController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUser() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/User.fxml"));
            AnchorPane user = (AnchorPane) loader.load();

            rootLayout.setCenter(user);

            userController = loader.getController();
            userController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
