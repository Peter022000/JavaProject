package com.example.javaproject;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static boolean loginFieldsCheck(PasswordField passwordField, TextField usernameField)
    {
        if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty())
        {
            usernameField.setPromptText("This field can't be empty!");
            usernameField.setStyle("-fx-prompt-text-fill: red;");

            passwordField.setPromptText("This field can't be empty!");
            passwordField.setStyle("-fx-prompt-text-fill: red;");
            return false;
        }else if(passwordField.getText().isEmpty())
        {
            passwordField.setPromptText("This field can't be empty!");
            passwordField.setStyle("-fx-prompt-text-fill: red;");
            return false;
        }
        else if(usernameField.getText().isEmpty())
        {
            usernameField.setPromptText("This field can't be empty!");
            usernameField.setStyle("-fx-prompt-text-fill: red;");
            return false;
        }

        return true;


    }

    static void createAccountValidator(PasswordField passwordField, PasswordField passwordConfirmField, TextField emailField, TextField usernameField, TextField securityAnswerField)
    {
        Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})");
        Matcher passwordMatcher = passwordPattern.matcher(passwordField.getText());

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher emailMatcher = emailPattern.matcher(emailField.getText());

        if(passwordMatcher.find())
        {
            //System.out.println("password matched correct");
        }
        else
        {
            Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
            passwordAlert.setHeaderText(null);
            passwordAlert.setTitle("Password too weak!");
            passwordAlert.setContentText("Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
            passwordAlert.showAndWait();
        }

        if(emailMatcher.find())
        {
            //System.out.println("email matched correct");
        }
        else
        {
            Alert emailAlert = new Alert(Alert.AlertType.ERROR);
            emailAlert.setHeaderText(null);
            emailAlert.setTitle("Wrong email!");
            emailAlert.setContentText("That's a wrong email. Example email: username@domain.com");
            emailAlert.showAndWait();
        }

        if(passwordField.getText().isEmpty() || passwordConfirmField.getText().isEmpty() || usernameField.getText().isEmpty() || emailField.getText().isEmpty() || securityAnswerField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Empty fields!");
            alert.setContentText("Some fields are empty! Fill them out.");
            alert.showAndWait();
        }
    }

}
