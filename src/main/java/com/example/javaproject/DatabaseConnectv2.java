package com.example.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnectv2 {
    public static ObservableList<Item> getItems(int uid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
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
                    "WHERE u.uid = "+uid+"" +
                    "AND amount > 0" +
                    "ORDER BY i.iid;");

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

    public static ObservableList<Item> getShopItems(int sid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
            //connection = DriverManager.getConnection(url, userDB, passwordDB);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "SELECT s.sid, i.*, s.amount\n" +
                    "FROM \"VirtualMerchant\".shops as s\n" +
                    "INNER JOIN \"VirtualMerchant\".items as i\n" +
                    "ON i.iid = s.iid\n" +
                    "WHERE s.sid = " + sid +"\n" +
                    "ORDER BY i.iid;");

            if (!resultSet.next()) {
                //System.out.println("ResultSet in empty in Java");
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


    public static void deleteItemFromEquipment(int uid, int iid) throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            //connection = DriverManager.getConnection(url, userDB, passwordDB);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");
            statement = connection.createStatement();

            statement.executeUpdate( "UPDATE \"VirtualMerchant\".equipments\n" +
                    "\tSET amount=amount-1\n" +
                    "\tWHERE uid = " + uid + " AND iid = " + iid + ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            assert connection != null;
            connection.close();
        }
    }

    public static void buyItemFromEquipment(int sid, int uid, int iid) throws SQLException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "Select * FROM \"VirtualMerchant\".equipments\n" +
                    "WHERE uid = " + uid + " AND iid = " + iid + ";");

            if (!resultSet.next()) {
                statement.executeQuery("INSERT INTO \"VirtualMerchant\".equipments(\n" +
                        "\tuid, iid, amount)\n" +
                        "\tVALUES (" + uid + ", " + iid + ", 1);");
            } else {
                statement.executeUpdate( "UPDATE \"VirtualMerchant\".equipments\n" +
                        "\tSET amount=amount+1\n" +
                        "\tWHERE uid = " + uid + " AND iid = " + iid + ";");
            }
            statement.executeUpdate( "UPDATE \"VirtualMerchant\".shops\n" +
                    "SET amount=amount-1\n" +
                    "WHERE sid = " + sid + " AND iid = " + iid + ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            assert connection != null;
            connection.close();
        }
    }
}
