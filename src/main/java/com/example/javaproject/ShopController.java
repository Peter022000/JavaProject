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

public class ShopController {

    @FXML
    private Label shopLabel;

    @FXML
    private BorderPane borderPane;

    private String shop;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("navigation-view.fxml"));
        BorderPane topView = loader.load();
        
        borderPane.setTop(topView);
    }

    public void setShop(String shop) {
        this.shop = shop;
        shopLabel.setText(this.shop);
    }
}
