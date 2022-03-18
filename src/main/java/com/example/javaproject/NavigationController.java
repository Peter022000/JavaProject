package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationController {

    @FXML
    private ComboBox<String> menu;

    @FXML
    private ComboBox<String> shopMenu;

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() throws IOException {
        menu.getItems().add("Ekwipunek");
        menu.getItems().add("Użytkownik");
        menu.getItems().add("Wyloguj się");

        shopMenu.getItems().add("Kowal");
        shopMenu.getItems().add("Zaopatrzenie");
        shopMenu.getItems().add("Krawiec");
    }

    @FXML
    private void menuAction(ActionEvent event) throws IOException {
        int selectedIndex = menu.getSelectionModel().getSelectedIndex();

        if(selectedIndex == 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("equipment-view.fxml"));
            Parent root = loader.load();

            EquipmentController equipmentController = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(selectedIndex == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view.fxml"));
            Parent root = loader.load();

            UserController userController = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(selectedIndex == 2) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void shopMenuAction(ActionEvent event) throws IOException {
        int selectedIndex = shopMenu.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop-view.fxml"));
        Parent root = loader.load();

        ShopController shopController = loader.getController();

        if(selectedIndex == 0) {
            shopController.setShop("Kowal");
        } else if(selectedIndex == 1) {
            shopController.setShop("Zaopatrzenie");
        } else if(selectedIndex == 2) {
            shopController.setShop("Krawiec");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
