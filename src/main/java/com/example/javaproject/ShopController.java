package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopController {

    @FXML
    private ComboBox<String> shopMenu;

    @FXML
    private Label shopLabel;

    private int uid;

    public void setUid(int uid) {
        this.uid = uid;
    }

    @FXML
    public void initialize() throws IOException {
        shopMenu.getItems().add("Kowal");
        shopMenu.getItems().add("Zaopatrzenie");
        shopMenu.getItems().add("Krawiec");
    }

    public void setShop(String shop) {
        shopLabel.setText(shop);
    }

    @FXML
    void shopMenuAction(ActionEvent event) throws IOException {
        int selectedIndex = shopMenu.getSelectionModel().getSelectedIndex();
        if(selectedIndex == 0) {
            this.setShop("Kowal");
        } else if(selectedIndex == 1) {
            this.setShop("Zaopatrzenie");
        } else if(selectedIndex == 2) {
            this.setShop("Krawiec");
        }
    }

    @FXML
    void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();

        menuController.setUid(uid);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
