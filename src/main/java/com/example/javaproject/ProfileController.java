package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ProfileController {

    @FXML
    private ImageView profileAvatar;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private VBox avatarVbox;

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

        //INFO: nie da sie zrobić tak V wyskakuje błąd
        String url = "\""+profileUrl+"\"";
        System.out.println(url);

        //INFO: ale Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png") zadziała
        Image avatarUrl = new Image(profileUrl);
        profileAvatar.setImage(avatarUrl);

        usernameLabel.setText(username);
        emailLabel.setText(email);
    }

    public void setAvatar1(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av1.png",username);

    }

    public void setAvatar2(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av2.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av2.png",username);
    }

    public void setAvatar3(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av3.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av3.png",username);
    }

    public void setAvatar4(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av4.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av4.png",username);
    }

    public void setAvatar5(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av5.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av5.png",username);
    }

    public void setAvatar6(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av6.png");
        profileAvatar.setImage(avatarUrl);
        DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av6.png",username);
    }

    public void openAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(true);
    }

    public void closeAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(false);
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
