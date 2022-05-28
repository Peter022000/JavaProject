package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Klasa odpowiedzialna za scene logowania.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Parent root;

    private DatabaseConnection databaseConnection;

    /**
     * Ustawia obiekt połączenia bazy danych.
     * @param databaseConnection obiekt połączenia
     */
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Główna funkcja kontrolera odpowiedzialna za sprawdzenie poprawności wprowadzonych danych.
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        if(databaseConnection.getConnection() == null) {
            databaseConnection.setConnection();
        }


        if(databaseConnection.getConnection() != null) {
            if (Validator.loginFieldsCheck(passwordField, usernameField)) {
                if (databaseConnection.loginCheck(event, usernameField.getText(), passwordField.getText())) {
                    FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource("menu-view.fxml"));
                    root = loader.load();

                    MenuController menuController = loader.getController();
                    String username;
                    username = usernameField.getText();

                    UserData userData = databaseConnection.setProfileData(username);

                    menuController.setUserData(userData, databaseConnection);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    root.requestFocus(); //Żeby nie oznaczało pierwszego buttona jako selected
                }
            }
        }
    }

    /**
     * Funkcja odpowiedzialna za przejście do sceny rejestracji.
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void register(MouseEvent event) throws IOException, SQLException {
       // SwitchScene.switchScene("signup-view.fxml", event);

        if(databaseConnection.getConnection() == null) {
            databaseConnection.setConnection();
        }

        if(databaseConnection.getConnection() != null) {
            FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource("signup-view.fxml"));
            root = loader.load();

            SignUpController signUpController = loader.getController();

            signUpController.setConnection(databaseConnection);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Funkcja odpowiedzialna za przejście do sceny resetowania hasła.
     * @param event zdarzenie
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void resetPassword(MouseEvent event) throws IOException, SQLException {

        if(databaseConnection.getConnection() == null) {
            databaseConnection.setConnection();
        }

        if(databaseConnection.getConnection() != null) {

            FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource("resetPassword-view.fxml"));
            root = loader.load();

            ResetEmailController resetEmailController = loader.getController();

            resetEmailController.setConnection(databaseConnection);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}