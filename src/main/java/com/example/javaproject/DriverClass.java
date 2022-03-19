package com.example.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverClass {

    String jdbcURL = "jdbc:postgres://gfyawits:0IqSFC65W83vkH8BeAYmSD0uasgQ09nf@balarama.db.elephantsql.com/gfyawits";
    String username = "gfyawits";
    String password = "0IqSFC65W83vkH8BeAYmSD0uasgQ09nf";

    Connection connection;
    {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to the database server.");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting with database. Server not found.");
            e.printStackTrace();
        }
    }

}
