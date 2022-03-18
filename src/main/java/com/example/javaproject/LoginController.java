package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private ChoiceBox securityQuestions;

    @FXML
    void initialize()
    {
        securityQuestions.getItems().clear();
        securityQuestions.getItems().add("What is the name of your favorite pet?");
        securityQuestions.getItems().add("What primary school did you attend?");
        securityQuestions.getItems().add("In what town or city was your first full time job?");
        securityQuestions.getItems().add("What is your spouse or partner's mother's maiden name?");
        securityQuestions.getItems().add("What is the middle name of your oldest child?");
        securityQuestions.getItems().add("What is your favourite movie?");
        securityQuestions.getItems().add("What is your memorable date?");
        securityQuestions.getItems().add("What are the last five digits of your driver's license number?");
        securityQuestions.setValue("What is the name of your favorite pet?");
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("equipment-view.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void register(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup-view.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}