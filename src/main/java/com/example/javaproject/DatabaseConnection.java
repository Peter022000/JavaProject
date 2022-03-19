package com.example.javaproject;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            conn = DriverManager.getConnection(url, "postgres", "admin");
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        Statement st = conn.createStatement();

//        String query = "SELECT test FROM \"user\".users";
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
}
