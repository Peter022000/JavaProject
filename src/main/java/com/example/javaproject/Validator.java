package com.example.javaproject;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa odpowiedzialna za weryfikację poprawności wprowadzonych danych.
 */
public class Validator {

    /**
     * Sprawdza poprawność danych logowania.
     * @param passwordField hasło użytkownika, wprowadzone w textfield
     * @param usernameField nazwa użytkownika, wprowadzona w textfield
     * @return
     */
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

    /**
     * Sprawdza poprawność emaila.
     * @param emailField email użytkownika, wprowadzony w textfield
     * @return
     */
    static boolean emailValidator(TextField emailField)
    {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher emailMatcher = emailPattern.matcher(emailField.getText());

        if(emailField.getText().isEmpty() || emailField.equals(null)) {
            emailField.setPromptText("This field can't be empty!");
            emailField.setStyle("-fx-prompt-text-fill: red;");
            return false;
            //TODO: Sprawdzenie czy email występuje w bazie danych
        }
        else if(emailMatcher.find())
        {

        }
        else
        {
            emailField.clear();
            emailField.setPromptText("Email not correct!");
            emailField.setStyle("-fx-prompt-text-fill: red;");
            return false;
        }

        return true;
    }

    /**
     * Sprawdza poprawność hasła.
     * @param newPasswordField nowe hasło użytkownika
     * @param answerField odpowiedź na hasło bezpieczeństwa użytkownika
     * @param error
     * @return
     */
    static boolean resetPasswordValidator(PasswordField newPasswordField, TextField answerField, Label error)
    {
        Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})");
        Matcher passwordMatcher = passwordPattern.matcher(newPasswordField.getText());

        if(newPasswordField.getText().isEmpty() || newPasswordField.equals(null) || answerField.getText().isEmpty() || answerField.equals(null)) {
            error.setVisible(true);
            return false;
        }
        else if(passwordMatcher.find())
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
            return false;
        }
        return true;
    }

    /**
     * Sprawdza poprawność hasła.
     * @param newPasswordField nowe hasło użytkownika
     * @return
     */
    static boolean changePasswordValidator(TextField newPasswordField)
    {
        Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})");
        Matcher passwordMatcher = passwordPattern.matcher(newPasswordField.getText());

        if(newPasswordField.getText().isEmpty() || newPasswordField.equals(null)) {
            return false;
        }
        else if(passwordMatcher.find())
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
            return false;
        }
        return true;
    }

    /**
     * Sprawdza poprawność danych przy tworzeniu konta.
     * @param passwordField hasło użytkownika
     * @param passwordConfirmField potwierdzenie hasła użytkownika
     * @param emailField email użytkownika
     * @param usernameField nazwa użytkownika
     * @param securityAnswerField odpowiedź na pytanie bezpieczeństwa użytkownika
     * @return
     */
    static boolean createAccountValidator(PasswordField passwordField, PasswordField passwordConfirmField, TextField emailField, TextField usernameField, TextField securityAnswerField)
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
            return false;
        }

        if(emailMatcher.find())
        {
            System.out.println("Email matched");
        }
        else
        {
            emailField.clear();
            emailField.setPromptText("Email not correct.");
            emailField.setStyle("-fx-prompt-text-fill: red;");
            return false;
        }

        if(passwordField.getText().isEmpty() || passwordConfirmField.getText().isEmpty() || usernameField.getText().isEmpty() || emailField.getText().isEmpty() || securityAnswerField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Empty fields!");
            alert.setContentText("Some fields are empty! Fill them out.");
            alert.showAndWait();
            return false;
        }
        else
        {
            return true;
        }
    }

}
