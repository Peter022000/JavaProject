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
    private Label usernameLabel;
    @FXML
    private Label emailLabel;

    String username;
    String email;
    String profileUrl;
    //TODO: zapis url avatara profilowego użytkowników w bazie danych i odczyt avatara.

    @FXML
    public void initialize()
    {

    }

    public void setCredentials()
    {
        usernameLabel.setText(username);
        emailLabel.setText(email);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
