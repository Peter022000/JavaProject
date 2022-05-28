package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa, która przełącza użytkownika do innych aktywności
 */

public class MenuController {

    private UserData userData;

    private DatabaseConnection databaseConnection;

    /**
     * Funkcja wywoływana w innej klasie w celu przekazania danych użytkownika i połączenia z bazą danych
     * @param userData dane użytkownika
     * @param databaseConnection połączenie z bazą danych
     */

    public void setUserData(UserData userData, DatabaseConnection databaseConnection) {
        this.userData = userData;
        this.databaseConnection = databaseConnection;
    }

    /**
     * Funkcja przekierowująca użytkownika do zarządzania profilem
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    void GoToUserProfile(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile-view.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();
        profileController.setUserData(userData, databaseConnection);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Funkcja przekierowująca użytkownika do ekwipunku
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    void goToEquipment(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("equipment-view.fxml"));
        Parent root = loader.load();

        EquipmentController equipmentController = loader.getController();
        equipmentController.setUserData(userData, databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funkcja wylogowująca użytkownika
     * @param event zdarzenie
     * @throws IOException
     */

    @FXML
    void goToLoginMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setDatabaseConnection(databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //SwitchScene.switchScene("login-view.fxml", event);
    }

    /**
     * Funkcja przekierowująca użytkownika do sklepu
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    void goToShop(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop-view.fxml"));
        Parent root = loader.load();

        ShopController shopController = loader.getController();
        shopController.setUserData(userData, databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
