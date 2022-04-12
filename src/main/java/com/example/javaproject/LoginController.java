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
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Parent root;

    @FXML
    public void initialize()
    {
        
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if(Validator.loginFieldsCheck(passwordField, usernameField))
        {
            if(DatabaseConnection.loginCheck(event, usernameField.getText(), passwordField.getText()))
            {
                FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource("menu-view.fxml"));
                root = loader.load();

                MenuController menuController = loader.getController();
                String username;
                username = usernameField.getText();

                UserData userData = DatabaseConnection.setProfileData(username);

                menuController.setUserData(userData);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.requestFocus(); //Żeby nie oznaczało pierwszego buttona jako selected
            }
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