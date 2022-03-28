package com.example.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnectv2 {
    public static ObservableList<Item> getItems(int uid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        //List<Item> items=new ArrayList<Item>();
        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
            //connection = DriverManager.getConnection(url, userDB, passwordDB);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "SELECT\n" +
                    "\ti.*, e.amount\n" +
                    "FROM\n" +
                    "\t\"VirtualMerchant\".equipments as e\n" +
                    "INNER JOIN \"VirtualMerchant\".users as u\n" +
                    "    ON e.uid = u.uid\n" +
                    "INNER JOIN \"VirtualMerchant\".items as i\n" +
                    "    ON e.iid = i.iid\n" +
                    "WHERE u.uid = "+uid+";");

            if (!resultSet.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    int iid = resultSet.getInt("iid");
                    String name = resultSet.getString("name");
                    float weight = resultSet.getFloat("weight");
                    float value = resultSet.getFloat("value");
                    String description = resultSet.getString("description");
                    int amount = resultSet.getInt("amount");

                    items.add(new Item(iid, name, description, weight, value, amount));
                } while (resultSet.next());
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            assert connection != null;
            connection.close();
        }
        return items;
    }
}
