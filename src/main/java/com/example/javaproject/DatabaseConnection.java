package com.example.javaproject;

import javafx.scene.control.Alert;

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

//        String query = "SELECT * FROM \"VirtualMerchant\".users";
//
//        ResultSet rs = st.executeQuery(query);
//
//        while (rs.next()) {
//            System.out.print("Column 1 returned ");
//            System.out.println(rs.getString(1));
//        }
//
//        rs.close();
        
        conn.close();
    }


    boolean createAccount(String username, String email, String securityQuestion, String securityAnswer, String password)
    {
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
            return true;
        } catch (SQLException e) {
            Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
            passwordAlert.setHeaderText(null);
            passwordAlert.setTitle("Login or email already exists.");
            passwordAlert.setContentText("There was an error, cannot create account: "+e);
            e.printStackTrace();
            return false;
        }

    }


}
