package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize()
    {
        
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if(Validator.loginFieldsCheck(passwordField, usernameField))
        {
            DatabaseConnection.loginCheck(event, usernameField.getText(), passwordField.getText());
        }
    }

    @FXML
    private void register(MouseEvent event) throws IOException {
        SwitchScene.switchScene("signup-view.fxml", event);
    }

    @FXML
    private void resetPassword(MouseEvent event) throws IOException {
        SwitchScene.switchScene("resetPassword-view.fxml", event);
    }
}