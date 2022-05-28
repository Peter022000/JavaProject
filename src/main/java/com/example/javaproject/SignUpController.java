package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa odpowiedzialna za widok rejestracji użytkownika
 */
public class SignUpController {

    @FXML
    private ChoiceBox<String> securityQuestions;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField securityAnswerField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordConfirmField;

    @FXML
    private Label error;

    private DatabaseConnection databaseConnection;

    /**
     * Ustawia połączenie z bazą danych.
     * @param databaseConnection
     */
    public void setConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    String questions[] = {"What is the name of your favorite pet?", "What primary school did you attend?",
            "In what town or city was your first full time job?", "What is your partner's mother's maiden name?",
            "What is the middle name of your oldest child?", "What is your favourite movie?",
            "What is your memorable date?", "What are the last three digits of your number?", "What is the name of your favorite pet?"};

    /**
     * Przy starcie sceny dodaje pytania bezpieczeństwa.
     */
    @FXML
    public void initialize()
    {
        securityQuestions.getItems().addAll(questions);
        securityQuestions.setValue("What is the name of your favorite pet?");
    }

    /**
     * Funkcja realizuje przejście do sceny logowania.
     * @param event
     * @throws IOException
     */
    @FXML
    void login(MouseEvent event) throws IOException {
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
     * Funkcja odpowiedzialna za utworzenie użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void createAccount(ActionEvent event) throws SQLException, IOException {

        error.setVisible(false);

        if(passwordField.getText().equals(passwordConfirmField.getText()))
        {

        }
        else
        {
            passwordConfirmField.clear();
            passwordConfirmField.setPromptText("Different passwords!");
            passwordConfirmField.setStyle("-fx-prompt-text-fill: red;");
        }
        if(Validator.createAccountValidator(passwordField, passwordConfirmField, emailField, usernameField, securityAnswerField))
        {
            databaseConnection.createAccount(usernameField.getText(), emailField.getText(),
                    securityQuestions.getValue(), securityAnswerField.getText(), passwordField.getText(), error, event);

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