package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileController {

    @FXML
    private ImageView profileAvatar;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private VBox avatarVbox;
    @FXML
    private VBox otherSettingsVbox;

    @FXML
    private Button changePassword;
    @FXML
    private Button changeLogin;
    @FXML
    private Button changeEmail;
    @FXML
    private Button confirmButton;

    @FXML
    private Label changeLabel;
    @FXML
    private Label changeLabel2;
    @FXML
    private Label changeLabel3;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Label type;
    @FXML
    private Label error;

    private UserData userData;

    String username;
    String email;
    String profileUrl;


    @FXML
    public void initialize()
    {
    }

    public void setUserData(UserData userData) {
        this.userData = userData;

        this.setCredentials();
    }

    @FXML
    void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();

        menuController.setUserData(userData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCredentials()
    {
        ArrayList<Integer> uid = new ArrayList<>();
        uid.add(userData.getUid());
        ArrayList<String> credentials = new ArrayList<>();

        credentials = DatabaseConnection.setProfileData(uid);
        this.username=String.valueOf(credentials.get(1));
        this.email = String.valueOf(credentials.get(2));
        this.profileUrl =String.valueOf(credentials.get(6));
        
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
        DatabaseConnection.setNewAvatar("1",userData.getUid());

    }

    public void setAvatar2(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av2.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av2.png",username);
        DatabaseConnection.setNewAvatar("2",userData.getUid());
    }

    public void setAvatar3(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av3.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av3.png",username);
        DatabaseConnection.setNewAvatar("3",userData.getUid());
    }

    public void setAvatar4(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av4.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av4.png",username);
        DatabaseConnection.setNewAvatar("4",userData.getUid());
    }

    public void setAvatar5(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av5.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av5.png",username);
        DatabaseConnection.setNewAvatar("5",userData.getUid());
    }

    public void setAvatar6(ActionEvent event)
    {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av6.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av6.png",username);
        DatabaseConnection.setNewAvatar("6",userData.getUid());
    }

    public void openAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(true);
    }

    public void closeAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(false);
    }

    public void openSettings(ActionEvent event)
    {
        otherSettingsVbox.setVisible(true);
    }

    public void closeSettings(MouseEvent event)
    {
        otherSettingsVbox.setVisible(false);
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

    public void changePassword(ActionEvent event)
    {
        changeLabel.setText("Change your password:");
        changeLabel2.setText("New password");
        changeLabel3.setText("Confirm new password");

        changeLabel.setVisible(true);
        changeLabel2.setVisible(true);
        changeLabel3.setVisible(true);
        textField1.setVisible(true);
        textField2.setVisible(true);
        confirmButton.setVisible(true);

        type.setText("password");
    }

    public void changeLogin(ActionEvent event)
    {
        changeLabel.setText("Change your login:");
        changeLabel2.setText("New login");
        changeLabel3.setText("Confirm new login");

        changeLabel.setVisible(true);
        changeLabel2.setVisible(true);
        changeLabel3.setVisible(true);
        textField1.setVisible(true);
        textField2.setVisible(true);
        confirmButton.setVisible(true);

        type.setText("login");
    }

    public void changeEmail(ActionEvent event)
    {
        changeLabel.setText("Change your email:");
        changeLabel2.setText("New email");
        changeLabel3.setText("Confirm new email password");

        changeLabel.setVisible(true);
        changeLabel2.setVisible(true);
        changeLabel3.setVisible(true);
        textField1.setVisible(true);
        textField2.setVisible(true);
        confirmButton.setVisible(true);

        type.setText("email");
    }

    public void confirmChanges(ActionEvent event)
    {
        switch (type.getText())
        {
            case "login":
                if (textField1.getText().isEmpty() || textField1.equals(null) || textField2.getText().isEmpty() || textField2.equals(null))
                {
                    error.setText("Some fields are empty!");
                    error.setVisible(true);
                }
                else
                {
                    if(DatabaseConnection.checkIfLoginExist(textField1.getText()))
                    {
                        DatabaseConnection.changeLogin(textField1.getText(), username);
                        System.out.println("Login changed successfully");
                    }
                    else
                    {
                        error.setText("Login already taken.");
                        error.setVisible(true);
                    }
                }
                break;
            case "password":
                if (textField1.getText().isEmpty() || textField1.equals(null) || textField2.getText().isEmpty() || textField2.equals(null))
                {
                    error.setText("Some fields are empty!");
                    error.setVisible(true);
                }
                else
                {
                    if(textField1.getText().equals(textField2.getText()))
                    {
                        if(Validator.changePasswordValidator(textField1))
                        {
                            DatabaseConnection.resetPassword(event,textField1.getText(),email);
                            Alert passwordAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            passwordAlert.setHeaderText(null);
                            passwordAlert.setTitle("Password changed successfully");
                            passwordAlert.showAndWait();
                        }
                    }
                    else
                    {
                        error.setText("Passwords doesn't match each other.");
                        error.setVisible(true);
                    }
                }
                break;
            case "email":
                //TODO: zmiana emailu
                System.out.println("Change email");
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }
}
