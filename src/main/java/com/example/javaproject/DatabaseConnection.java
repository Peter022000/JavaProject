package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnection {

    String url = "jdbc:postgresql://localhost/postgres";
    String userDB = "postgres";
    String passwordDB = "admin";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            conn = DriverManager.getConnection(url, "postgres", "admin");
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        Statement st = conn.createStatement();
        conn.close();
    }

    //TODO: Zmienić wywoływanie alertu

    boolean createAccount(String username, String email, String securityQuestion, String securityAnswer, String password, Label error, ActionEvent event)
    {
        String query = "INSERT INTO \"VirtualMerchant\".users(login, password, security_question, security_answer, email)\n" +
                "\tVALUES (?, ?, ?, ?, ?);";
        try {
            Connection createAcc = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", userDB, passwordDB);
            PreparedStatement pst = createAcc.prepareCall(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, securityQuestion);
            pst.setString(4, securityAnswer);
            pst.setString(5, email);
            pst.executeUpdate();
            System.out.println("Account created!");
            SwitchScene.switchScene("login-view.fxml",event);
            return true;
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            error.setVisible(true);
            e.printStackTrace();
            return false;
        }
    }

    public static void loginCheck(ActionEvent event, String username, String password)
    {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;
        Connection logIn = null;

        try {
            logIn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");

            psCheckLogin = logIn.prepareStatement("SELECT password FROM \"VirtualMerchant\".users WHERE login=?");
            psCheckLogin.setString(1, username);
            resultSet = psCheckLogin.executeQuery();
            if(!resultSet.isBeforeFirst())
            {
                System.out.println("User not found in the db!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login is incorrect!");
                alert.show();
            }
            else
            {
                while (resultSet!=null && resultSet.next())
                {
                    String typedText = resultSet.getString(1);

                    if(typedText.equals(password)){
                        SwitchScene.switchScene("equipment-view.fxml",event);
                    }
                    else
                    {
                        System.out.println("Password didn't match.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password is incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null)
            {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckLogin != null)
            {
                try {
                    psCheckLogin.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (logIn != null)
            {
                try {
                    logIn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
