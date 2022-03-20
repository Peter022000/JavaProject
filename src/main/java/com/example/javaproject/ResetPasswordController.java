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

    String email;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void setFields(ActionEvent event, String email) throws IOException {
        System.out.println("przed security question");
        DatabaseConnection.setSecurityQuestionLabel(event, email, securityQuestionField);
    }

    @FXML
    private void resetPassword2(ActionEvent event) throws IOException {
        /*error.setVisible(false);
        if(Validator.resetPasswordValidator(newPasswordField, securityQuestionAnswerField, error)) {
            //DatabaseConnection.emailCheck(event,emailField.getText());
        }*/
    }

}
