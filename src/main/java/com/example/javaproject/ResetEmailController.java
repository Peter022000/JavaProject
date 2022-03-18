package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ResetEmailController {

    @FXML
    private void login(MouseEvent event) throws IOException {
        SwitchScene.switchScene("login-view.fxml", event);
    }

    @FXML
    private void resetPassword2(ActionEvent event) throws IOException {
        SwitchScene.switchScene("resetPassword2-view.fxml", event);
    }

}
