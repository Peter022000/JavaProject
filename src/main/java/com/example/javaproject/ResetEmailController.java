package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ResetEmailController {
    @FXML
    private TextField emailField;

    @FXML
    private Label securityQuestionField;
    @FXML
    private TextField securityQuestionAnswerField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label error;

    @FXML
    private Parent root;

    @FXML
    private void login(MouseEvent event) throws IOException {
        SwitchScene.switchScene("login-view.fxml", event);
    }

    @FXML
    private void resetPassword(ActionEvent event) throws IOException {
        if(Validator.emailValidator(emailField)) {
            if(DatabaseConnection.emailCheck(event,emailField.getText()))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("resetPassword2-view.fxml"));
                root = loader.load();
                ResetPasswordController resetPasswordController = loader.getController();
                resetPasswordController.setFields(event, emailField.getText());
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
