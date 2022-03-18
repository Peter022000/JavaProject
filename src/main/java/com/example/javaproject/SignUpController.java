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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    String questions[] = {"What is the name of your favorite pet?", "What primary school did you attend?",
            "In what town or city was your first full time job?", "What is your partner's mother's maiden name?",
            "What is the middle name of your oldest child?", "What is your favourite movie?",
            "What is your memorable date?", "What are the last three digits of your number?", "What is the name of your favorite pet?"};

    @FXML
    public void initialize()
    {
        securityQuestions.getItems().addAll(questions);
        securityQuestions.setValue("What is the name of your favorite pet?");
    }

    @FXML
    void login(MouseEvent event) throws IOException {
        SwitchScene.switchScene("login-view.fxml", event);
    }

    @FXML
    void createAccount(ActionEvent event){
        Validator.createAccountValidator(passwordField, passwordConfirmField, emailField, usernameField, securityAnswerField);
    }


    /*void createAccountValidator()
    {
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n");
        Matcher passwordMatcher = passwordPattern.matcher(passwordField.getText());

        Pattern emailPattern = Pattern.compile("\"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$\"");
        Matcher emailMatcher = emailPattern.matcher(emailField.getText());

        if(passwordField.getText().isEmpty() || passwordConfirmField.getText().isEmpty() || usernameField.getText().isEmpty() || emailField.getText().isEmpty() || securityAnswerField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Empty fields!");
            alert.setContentText("Some fields are empty! Fill them out.");
            alert.showAndWait();

            if(passwordMatcher.find())
            {
                System.out.println("password matched correct");
            }
            else
            {
                Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
                passwordAlert.setHeaderText(null);
                alert.setTitle("Password too weak!");
                passwordAlert.setContentText("Password must contain at least one digit [0-9].\n" +
                        "Password must contain at least one lowercase Latin character [a-z].\n" +
                        "Password must contain at least one uppercase Latin character [A-Z].\n" +
                        "Password must contain at least one special character like ! @ # & ( ).\n" +
                        "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
                passwordAlert.showAndWait();
            }

            if(emailMatcher.find())
            {
                System.out.println("email matched correct");
            }
            else
            {
                Alert emailAlert = new Alert(Alert.AlertType.ERROR);
                emailAlert.setHeaderText(null);
                alert.setTitle("Wrong email!");
                emailAlert.setContentText("That's a wrong email. Example email: username@domain.com");
                emailAlert.showAndWait();
            }
        }
    }*/

}