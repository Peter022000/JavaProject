package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Klasa odpowiedzialna za widok resetowania hasła, widok 2.
 */
public class ResetPasswordController {

    @FXML
    private Label securityQuestionField;
    @FXML
    private TextField securityQuestionAnswerField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label error;

    @FXML
    String email;

    private DatabaseConnection databaseConnection;

    /**
     * Funkcja realizuje walidację, weryfikację oraz wysłanie zapytania o zmianę hasła
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void resetPassword(ActionEvent event) throws IOException, SQLException {
        error.setVisible(false);
        if(Validator.resetPasswordValidator(newPasswordField, securityQuestionAnswerField, error)) {
            if(databaseConnection.securityAnswerCheck(securityQuestionAnswerField.getText(),email))
            {
                databaseConnection.resetPassword(event,newPasswordField.getText(),email);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
                Parent root = loader.load();

                LoginController loginController = loader.getController();
                loginController.setDatabaseConnection(databaseConnection);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Ustawia m.in. email użytkownika.
     * @param email email użytkownika
     * @param event zdarzenie
     * @param databaseConnection obiekt połaczenia
     * @throws SQLException
     */
    @FXML
    public void setFields(String email, ActionEvent event, DatabaseConnection databaseConnection) throws SQLException {
        this.email = email;
        databaseConnection.setSecurityQuestionLabel(event, email, securityQuestionField);
        this.databaseConnection = databaseConnection;
    }

    /**
     * Funkcja realizuje przejście do sceny z logowaniem.
     * @param event
     * @throws IOException
     */
    @FXML
    void goBackToLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setDatabaseConnection(databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
