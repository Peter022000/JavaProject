package com.example.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnectv2 {
    static String url = "jdbc:postgresql://195.150.230.210:5436/2022_krol_marcin";
    static String userDB = "2022_krol_marcin";
    static String passwordDB = "34300";


    public static ObservableList<Item> getItems(int uid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection(url, userDB, passwordDB);
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
            connection = DriverManager.getConnection(url, userDB, passwordDB);
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
            connection = DriverManager.getConnection(url, userDB, passwordDB);
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

    public static float buyItemFromEquipment(int sid, int uid, int iid, float money) throws SQLException {
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, userDB, passwordDB);
            statement = connection.createStatement();

            ResultSet resultSet2 = statement.executeQuery( "SELECT value\n" +
                    "\tFROM \"VirtualMerchant\".items\n" +
                    "\tWHERE iid = " + iid + ";");

            resultSet2.next();
            float cost = resultSet2.getFloat("value");

            if(money >= cost)
            {
                resultSet = statement.executeQuery( "Select * FROM \"VirtualMerchant\".equipments\n" +
                        "WHERE uid = " + uid + " AND iid = " + iid + ";");

                if (!resultSet.next()) {
                    statement.executeUpdate("INSERT INTO \"VirtualMerchant\".equipments(\n" +
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
                statement.executeUpdate( "UPDATE \"VirtualMerchant\".users\n" +
                        "\tSET money=money-" + cost + "\n" +
                        "\tWHERE uid = " + uid + ";");
                money=money-cost;
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You dont have enough money");
                alert.show();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            assert connection != null;
            connection.close();
        }
        return money;
    }
}
