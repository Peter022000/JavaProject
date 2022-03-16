package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private ComboBox<String> menu;

    @FXML
    private ComboBox<String> shopMenu;


    @FXML
    private AnchorPane navBody;

    @FXML
    private AnchorPane sceneChange;

    private FXMLLoader loader;

    @FXML
    public void initialize() throws IOException {
        menu.getItems().add("Ekwipunek");
        menu.getItems().add("Użytkownik");
        menu.getItems().add("Wyloguj się");

        shopMenu.getItems().add("Kowal");
        shopMenu.getItems().add("Zaopatrzenie");
        shopMenu.getItems().add("Krawiec");


        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("equipment-view.fxml"));

        BorderPane centerView1 = loader.load();

        borderPane.setCenter(centerView1);
    }

    @FXML
    void menuAction(ActionEvent event) throws IOException {
        int selectedIndex = menu.getSelectionModel().getSelectedIndex();

        if(selectedIndex == 0) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("equipment-view.fxml"));

            BorderPane centerView1 = loader.load();

            borderPane.setCenter(centerView1);

        } else if(selectedIndex == 1) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("user-view.fxml"));

            BorderPane centerView1 = loader.load();

            borderPane.setCenter(centerView1);
        } else if(selectedIndex == 2) {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login-view.fxml"));

            BorderPane centerView1 = loader.load();

            borderPane.setTop(null);
            borderPane.setCenter(centerView1);
        }
    }

    @FXML
    void shopMenuAction(ActionEvent event) throws IOException {
        int selectedIndex = shopMenu.getSelectionModel().getSelectedIndex();

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("shop-view.fxml"));


        if(selectedIndex == 0) {
            ShopController.saveSelectedShop("Kowal");
        } else if(selectedIndex == 1) {
            ShopController.saveSelectedShop("Zaopatrzenie");
        } else if(selectedIndex == 2) {
            ShopController.saveSelectedShop("Krawiec");
        }

        BorderPane centerView1 = loader.load();
        borderPane.setCenter(centerView1);
    }
}
