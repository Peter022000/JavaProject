package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {

    @FXML
    private ChoiceBox<String> securityQuestions;

    @FXML
    public void initialize()
    {
        securityQuestions.getItems().add("What is the name of your favorite pet?");
        securityQuestions.getItems().add("What primary school did you attend?");
        securityQuestions.getItems().add("In what town or city was your first full time job?");
        securityQuestions.getItems().add("What is your partner's mother's maiden name?");
        securityQuestions.getItems().add("What is the middle name of your oldest child?");
        securityQuestions.getItems().add("What is your favourite movie?");
        securityQuestions.getItems().add("What is your memorable date?");
        securityQuestions.getItems().add("What are the last three digits of your number?");
        securityQuestions.setValue("What is the name of your favorite pet?");
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}