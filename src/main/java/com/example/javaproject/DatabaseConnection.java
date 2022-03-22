package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnection {

    static String url = "jdbc:postgresql://195.150.230.210:5436/2022_krol_marcin";
    static String userDB = "2022_krol_marcin";
    static String passwordDB = "34300";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://195.150.230.210:5436/2022_krol_marcin";
        String username = "2022_krol_marcin";
        String password = "34300";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            db.close();
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: Zmienić wywoływanie alertu

    boolean createAccount(String username, String email, String securityQuestion, String securityAnswer, String password, Label error, ActionEvent event) {
        String query = "INSERT INTO \"VirtualMerchant\".users(login, password, security_question, security_answer, email)\n" +
                "\tVALUES (?, ?, ?, ?, ?);";
        try {
            Connection createAcc = DriverManager.getConnection(url, userDB, passwordDB);
            PreparedStatement pst = createAcc.prepareCall(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, securityQuestion);
            pst.setString(4, securityAnswer);
            pst.setString(5, email);
            pst.executeUpdate();
            System.out.println("Account created!");
            SwitchScene.switchScene("login-view.fxml", event);
            return true;
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            error.setVisible(true);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginCheck(ActionEvent event, String username, String password) {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;
        Connection logIn = null;

        try {
            logIn = DriverManager.getConnection(url, userDB, passwordDB);

            psCheckLogin = logIn.prepareStatement("SELECT password FROM \"VirtualMerchant\".users WHERE login=?");
            psCheckLogin.setString(1, username);
            resultSet = psCheckLogin.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the db!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login is incorrect!");
                alert.show();
            } else {
                while (resultSet != null && resultSet.next()) {
                    String typedText = resultSet.getString(1);

                    if (typedText.equals(password)) {
                        return true;
                    } else {
                        System.out.println("Password didn't match.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password is incorrect!");
                        alert.show();
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (psCheckLogin != null) {
                try {
                    psCheckLogin.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (logIn != null) {
                try {
                    logIn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean emailCheck(ActionEvent event, String email) {
        PreparedStatement psCheckEmail = null;
        ResultSet resultSet = null;
        Connection emailCheckConnection = null;

        try {
            emailCheckConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psCheckEmail = emailCheckConnection.prepareStatement("SELECT email FROM \"VirtualMerchant\".users WHERE email=?");
            psCheckEmail.setString(1, email);
            resultSet = psCheckEmail.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Email not found in the db!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email is incorrect!");
                alert.show();
                return false;
            }
            else
            {
                while (resultSet != null && resultSet.next()) {
                    String typedText = resultSet.getString(1);
                    if (typedText.equals(email)) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean securityAnswerCheck(String answer, String email) {
        PreparedStatement psCheckSecurityAnswer = null;
        ResultSet resultSet = null;
        Connection securityAnswerCheckConnection = null;

        try {
            securityAnswerCheckConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psCheckSecurityAnswer = securityAnswerCheckConnection.prepareStatement("SELECT security_answer FROM \"VirtualMerchant\".users WHERE email=?");
            psCheckSecurityAnswer.setString(1, email);
            resultSet = psCheckSecurityAnswer.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Security answer doesn't match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Security answer doesn't match");
                alert.show();
                return false;
            }
            else
            {
                while (resultSet != null && resultSet.next()) {
                    String typedText = resultSet.getString(1);
                    if (typedText.equals(answer)) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void setSecurityQuestionLabel(ActionEvent event, String email, Label securityQuestion) {

        PreparedStatement psCheckSecurityQuestion = null;
        ResultSet resultSet = null;
        Connection securityQuestionCheckConnection = null;
        try {
            securityQuestionCheckConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psCheckSecurityQuestion = securityQuestionCheckConnection.prepareStatement("SELECT security_question\n" +
                    "\tFROM \"VirtualMerchant\".users WHERE email=?");
            psCheckSecurityQuestion.setString(1, email);
            resultSet = psCheckSecurityQuestion.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Security question not found.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Security question not found.");
                alert.show();
            }
            else
            {
                while (resultSet != null && resultSet.next()) {
                    String typedText = resultSet.getString(1);
                    securityQuestion.setText(typedText);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetPassword(ActionEvent event, String newPassword, String email) {
        PreparedStatement psResetPassword = null;
        //ResultSet resultSet = null;
        Connection resetPasswordConnection = null;
        try {
            resetPasswordConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psResetPassword = resetPasswordConnection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET password=?\n" +
                    "\tWHERE email=?;");
            psResetPassword.setString(1, newPassword);
            psResetPassword.setString(2, email);
            psResetPassword.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
