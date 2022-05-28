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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za widok profilu.
 */
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

    private DatabaseConnection databaseConnection;

    String username;
    String email;
    String profileUrl;

    /**
     * Funkcja ustawia dane użytkownika.
     * @param userData obiekt przechowuje dane użytkownika.
     * @param databaseConnection obiekt połączenia
     * @throws SQLException
     */
    public void setUserData(UserData userData, DatabaseConnection databaseConnection) throws SQLException {
        this.userData = userData;
        this.databaseConnection = databaseConnection;

        this.setCredentials();
    }

    /**
     * Funkcja odpowiedzialna za przejście do sceny menu.
     * @param event zdarzenie
     * @throws IOException
     */
    @FXML
    void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();

        MenuController menuController = loader.getController();

        menuController.setUserData(userData, databaseConnection);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funkcja ustawiająca/drukująca dane użytkownika w widoku aplikacji.
     * @throws SQLException
     */
    public void setCredentials() throws SQLException {
        ArrayList<Integer> uid = new ArrayList<>();
        uid.add(userData.getUid());
        ArrayList<String> credentials = new ArrayList<>();

        credentials = databaseConnection.setProfileData(uid);
        this.username=String.valueOf(credentials.get(1));
        this.email = String.valueOf(credentials.get(2));
        this.profileUrl =String.valueOf(credentials.get(6));

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

    /**
     * Funkcja ustawia avatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar1(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av1.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av1.png",username);
        databaseConnection.setNewAvatar("1",userData.getUid());

    }

    /**
     * Funkcja ustawia avatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar2(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av2.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av2.png",username);
        databaseConnection.setNewAvatar("2",userData.getUid());
    }

    /**
     * Funkcja ustawia avatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar3(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av3.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av3.png",username);
        databaseConnection.setNewAvatar("3",userData.getUid());
    }

    /**
     * Funkcja ustawia avatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar4(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av4.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av4.png",username);
        databaseConnection.setNewAvatar("4",userData.getUid());
    }

    /**
     * Funkcja ustawia avatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar5(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av5.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av5.png",username);
        databaseConnection.setNewAvatar("5",userData.getUid());
    }

    /**
     * Funkcja ustawia awatar użytkownika.
     * @param event zdarzenie
     * @throws SQLException
     */
    public void setAvatar6(ActionEvent event) throws SQLException {
        Image avatarUrl = new Image("file:src/main/resources/assets/Avatars/av6.png");
        profileAvatar.setImage(avatarUrl);
        //DatabaseConnection.setNewAvatar("file:src/main/resources/assets/Avatars/av6.png",username);
        databaseConnection.setNewAvatar("6",userData.getUid());
    }

    /**
     * Funkcja pokazuje listę awatarów.
     * @param event
     */
    public void openAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(true);
        otherSettingsVbox.setVisible(false);
    }

    /**
     * Funkcja zamyka liste awatarów
     * @param event
     */
    public void closeAvatarList(MouseEvent event)
    {
        avatarVbox.setVisible(false);
    }

    /**
     * Otwiera okno ustawień.
     * @param event
     */
    public void openSettings(ActionEvent event)
    {
        otherSettingsVbox.setVisible(true);
        avatarVbox.setVisible(false);
    }

    /**
     * Zamyka okno ustawień.
     * @param event
     */
    public void closeSettings(MouseEvent event)
    {
        otherSettingsVbox.setVisible(false);
    }

    /**
     * Funkcja ustawia treść etykiet oraz flagę dla zmiany hasła.
     * @param event
     */
    public void changePassword(ActionEvent event)
    {
        changeLabel.setText("Change your password:");
        changeLabel2.setText("New password");
        changeLabel3.setText("Confirm new password");
        enableChange();

        type.setText("password");
    }

    /**
     * Funkcja ustawia treść etykiet oraz flagę dla zmiany loginu.
     * @param event
     */
    public void changeLogin(ActionEvent event)
    {
        changeLabel.setText("Change your login:");
        changeLabel2.setText("New login");
        changeLabel3.setText("Confirm new login");
        enableChange();

        type.setText("login");
    }

    /**
     * Funkcja ustawia treść etykiet oraz flagę dla zmiany emaila.
     * @param event
     */
    public void changeEmail(ActionEvent event)
    {
        changeLabel.setText("Change your email:");
        changeLabel2.setText("New email");
        changeLabel3.setText("Confirm new email password");
        enableChange();

        type.setText("email");
    }

    /**
     * Funkcja realizuje walidację i weryfikację danych oraz zapytanie do bazy danych o zmianę danych użytkownika.
     * @param event
     * @throws SQLException
     */
    public void confirmChanges(ActionEvent event) throws SQLException {
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
                    if(textField1.getText().equals(textField2.getText()))
                    {
                        if (databaseConnection.checkIfLoginExist(textField1.getText())) {
                            databaseConnection.changeLogin(textField1.getText(), username);
                            Alert passwordAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            passwordAlert.setHeaderText(null);
                            passwordAlert.setTitle("Success");
                            passwordAlert.setContentText("Login changed successfully");
                            passwordAlert.showAndWait();
                            usernameLabel.setText(textField1.getText());
                            disableChange();
                        } else {
                            error.setText("Login already taken.");
                            error.setVisible(true);
                        }
                    }
                    else
                    {
                        error.setText("Logins doesn't match each other.");
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
                            databaseConnection.resetPassword(event,textField1.getText(),email);
                            Alert passwordAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            passwordAlert.setHeaderText(null);
                            passwordAlert.setTitle("Success");
                            passwordAlert.setContentText("Password changed successfully");
                            passwordAlert.showAndWait();
                            disableChange();
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
                if (textField1.getText().isEmpty() || textField1.equals(null) || textField2.getText().isEmpty() || textField2.equals(null))
                {
                    error.setText("Some fields are empty!");
                    error.setVisible(true);
                }
                else
                {
                    if(textField1.getText().equals(textField2.getText()))
                    {
                        if(Validator.emailValidator(textField1))
                        {
                            if (databaseConnection.emailCheck(event, email)) {
                                databaseConnection.changeEmail(textField1.getText(), email);
                                Alert passwordAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                passwordAlert.setHeaderText(null);
                                passwordAlert.setTitle("Success");
                                passwordAlert.setContentText("Email changed successfully");
                                passwordAlert.showAndWait();
                                emailLabel.setText(textField1.getText());
                                disableChange();
                            }
                        }
                    }
                    else
                    {
                        error.setText("Emails doesn't match each other.");
                        error.setVisible(true);
                    }
                }
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    /**
     * Włącza etykiety.
     */
    public void enableChange()
    {
        changeLabel.setVisible(true);
        changeLabel2.setVisible(true);
        changeLabel3.setVisible(true);
        textField1.setVisible(true);
        textField2.setVisible(true);
        confirmButton.setVisible(true);
    }

    /**
     * Wyłącza etykiety.
     */
    public void disableChange()
    {
        changeLabel.setVisible(false);
        changeLabel2.setVisible(false);
        changeLabel3.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        confirmButton.setVisible(false);
        textField1.clear();
        textField2.clear();
    }
}
