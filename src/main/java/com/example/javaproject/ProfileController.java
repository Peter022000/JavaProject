package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

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



    @FXML
    public void initialize()
    {

    }

    public void setCredentials()
    {
        /*//INFO: nie da sie zrobić tak V wyskakuje błąd
        String url = "\""+profileUrl+"\"";
        System.out.println(url);

        //INFO: ale Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png") zadziała
        Image avatarUrl = new Image(profileUrl);
        profileAvatar.setImage(avatarUrl);*/

        //TODO: Naprawić, żeby czytało url z bazy danych
        //Rozwiązanie tymczasowe w związku z problemem z czytaniem ścieżki: "illegal char <"> at index 24 java..."
        switch (profileUrl)
        {
            case "1":
                Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png");
                profileAvatar.setImage(avatarUrl);
                break;
            case "2":
                Image avatarUrl2 = new Image("file:src/main/resources/assets/Avatars/av2.png");
                profileAvatar.setImage(avatarUrl2);
                break;
            case "3":
                Image avatarUrl3 = new Image("file:src/main/resources/assets/Avatars/av3.png");
                profileAvatar.setImage(avatarUrl3);
                break;
            case "4":
                Image avatarUrl4 = new Image("file:src/main/resources/assets/Avatars/av4.png");
                profileAvatar.setImage(avatarUrl4);
                break;
            case "5":
                Image avatarUrl5 = new Image("file:src/main/resources/assets/Avatars/av5.png");
                profileAvatar.setImage(avatarUrl5);
                break;
            case "6":
                Image avatarUrl6 = new Image("file:src/main/resources/assets/Avatars/av6.png");
                profileAvatar.setImage(avatarUrl6);
                break;
            default:
                Image avatarUrl0 = new Image("file:src/main/resources/assets/Avatars/av0.jpg");
                profileAvatar.setImage(avatarUrl0);
                break;
        }
        usernameLabel.setText(username);
        emailLabel.setText(email);
    }

    public void setAvatar1(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av1.png",username);
        DatabaseConnection.setNewAvatar("1",username);

    }

    public void setAvatar2(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av2.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av2.png",username);
        DatabaseConnection.setNewAvatar("2",username);
    }

    public void setAvatar3(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av3.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av3.png",username);
        DatabaseConnection.setNewAvatar("3",username);
    }

    public void setAvatar4(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av4.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av4.png",username);
        DatabaseConnection.setNewAvatar("4",username);
    }

    public void setAvatar5(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av5.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av5.png",username);
        DatabaseConnection.setNewAvatar("5",username);
    }

    public void setAvatar6(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av6.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av6.png",username);
        DatabaseConnection.setNewAvatar("6",username);
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

    public void switchToEquipment(MouseEvent event) throws IOException {
        SwitchScene.switchScene("equipment-view.fxml", event);
    }

    public void logout(ActionEvent event) throws IOException {
        SwitchScene.switchScene("login-view.fxml", event);
    }
}
