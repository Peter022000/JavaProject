package com.example.javaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Klasa odpowiedzialna za start aplikacji.
 */
public class HelloApplication extends Application {

    /**
     * Funkcja generująca obiekt stage. Wyświetla interfejs graficzny.
     * @param stage obiekt stage
     * @throws IOException
     * @throws SQLException
     */
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("file:src/main/resources/assets/appIcon.png");

        DatabaseConnection databaseConnection = new DatabaseConnection();

        LoginController loginController = fxmlLoader.getController();
        loginController.setDatabaseConnection(databaseConnection);

        stage.getIcons().add(icon);
        stage.setTitle("Virtual Merchant");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funkcja startowa. Uruchamia program.
     * @param args argumenty wywołania
     */
    public static void main(String[] args) {
        launch();
    }
}