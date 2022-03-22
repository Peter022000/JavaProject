package com.example.javaproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProfileController {

    @FXML
    private ImageView profileAvatar;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;

    String username;
    String email;
    //TODO: zapis url avatara profilowego użytkowników w bazie danych i odczyt avatara.

    @FXML
    public void initialize()
    {
        usernameField.setText(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
