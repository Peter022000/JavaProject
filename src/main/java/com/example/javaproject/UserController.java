package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserController {

    @FXML
    private ComboBox<String> menu;

    @FXML
    public void initialize() {
        menu.getItems().add("Ekwipunek");
        menu.getItems().add("Sklepy");
        menu.getItems().add("Użytkownik");
        menu.getItems().add("Wyloguj się");
    }

    @FXML
    void menuAction(ActionEvent event) throws IOException {
        int selectedIndex = menu.getSelectionModel().getSelectedIndex();

        if(selectedIndex == 0) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("equipment-view.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(selectedIndex == 1) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shop-view.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(selectedIndex == 2) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-view.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(selectedIndex == 3) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


}