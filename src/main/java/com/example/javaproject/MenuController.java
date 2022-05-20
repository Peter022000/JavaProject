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

public class MenuController {

    private UserData userData;

    private DatabaseConnection databaseConnection;

    public void setUserData(UserData userData, DatabaseConnection databaseConnection) {
        this.userData = userData;
        this.databaseConnection = databaseConnection;
    }

    @FXML
    void GoToUserProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile-view.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();
        profileController.setUserData(userData, databaseConnection);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

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

    @FXML
    void goToLoginMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setConnection(databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //SwitchScene.switchScene("login-view.fxml", event);
    }

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
