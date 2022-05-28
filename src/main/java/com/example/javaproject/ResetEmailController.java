package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Klasa odpowiedzialna za widok resetowania hasła, widok 1.
 */
public class ResetEmailController {
    @FXML
    private TextField emailField;

    @FXML
    private Label securityQuestionField;
    @FXML
    private TextField securityQuestionAnswerField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label error;

    @FXML
    private Parent root;

    private DatabaseConnection databaseConnection;

    /**
     * Ustawia obiekt połączenia z bazą danych.
     * @param databaseConnection obiekt połączenia
     */
    public void setConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Funkcja realizuje przejście do sceny logowania.
     * @param event
     * @throws IOException
     */
    @FXML
    private void login(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setDatabaseConnection(databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funkcja realizuje przejście do sceny resetowania hasła.
     * @param event
     * @throws IOException
     */
    @FXML
    private void resetPassword(ActionEvent event) throws IOException, SQLException {
        if(Validator.emailValidator(emailField)) {
            if(databaseConnection.emailCheck(event,emailField.getText()))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("resetPassword2-view.fxml"));
                root = loader.load();
                ResetPasswordController resetPasswordController = loader.getController();
                resetPasswordController.setFields(emailField.getText(), event, databaseConnection);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
