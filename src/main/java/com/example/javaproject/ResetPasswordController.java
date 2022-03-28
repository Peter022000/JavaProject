package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ResetPasswordController {

    @FXML
    private Label securityQuestionField;
    @FXML
    private TextField securityQuestionAnswerField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label error;

    @FXML
    String email;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void setFields(ActionEvent event, String email) throws IOException {
        DatabaseConnection.setSecurityQuestionLabel(event, email, securityQuestionField);
    }

    @FXML
    private void resetPassword(ActionEvent event) throws IOException {
        error.setVisible(false);
        if(Validator.resetPasswordValidator(newPasswordField, securityQuestionAnswerField, error)) {
            if(DatabaseConnection.securityAnswerCheck(securityQuestionAnswerField.getText(),email))
            {
                DatabaseConnection.resetPassword(event,newPasswordField.getText(),email);
            }
        }
    }

    @FXML
    public void setEmail(String email) {
        this.email = email;
    }

}
