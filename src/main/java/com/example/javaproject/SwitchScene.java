package com.example.javaproject;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Klasa zmieniająca scenę.
 */
public class SwitchScene {

    /**
     * Funkcja realizuje przejście do nowej sceny.
     * @param fxmlFile plik fxml, scena
     * @param event zdarzenie
     * @throws IOException
     */
    @FXML
    static void switchScene(String fxmlFile, Event event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource(fxmlFile)));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
