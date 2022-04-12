package com.example.javaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            //Connection db = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "admin");
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

    public static boolean checkIfLoginExist(String username) {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;
        Connection logIn = null;

        try {
            logIn = DriverManager.getConnection(url, userDB, passwordDB);

            psCheckLogin = logIn.prepareStatement("SELECT password FROM \"VirtualMerchant\".users WHERE login=?");
            psCheckLogin.setString(1, username);
            resultSet = psCheckLogin.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                //Nie ma takiego loginu, login nie jest zajęty
                return true;
            }
            else
            {
                System.out.println("Error, login already taken.");
                return false;
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
    }

    public static void changeLogin(String newLogin, String oldLogin) {
        PreparedStatement psResetLogin = null;
        Connection resetLoginConnection = null;
        try {
            resetLoginConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psResetLogin = resetLoginConnection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET login=?\n" +
                    "\tWHERE login=?;");
            psResetLogin.setString(1, newLogin);
            psResetLogin.setString(2, oldLogin);
            psResetLogin.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> setProfileData(ArrayList<Integer> UID) {
        PreparedStatement psCheckProfilData = null;
        ResultSet resultSet = null;
        Connection emailCheckConnection = null;
        String uid = null;
        String login = null;
        String email = null;
        String password = null;
        String securityQuestion = null;
        String securityAnswer = null;
        String profileImageUrl = null;
        ArrayList<String> credentials = new ArrayList<String>();

        try {
            emailCheckConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psCheckProfilData = emailCheckConnection.prepareStatement("SELECT uid, login, email, password, security_question, security_answer, profile_image_url\n" +
                    "\tFROM \"VirtualMerchant\".users WHERE uid=?");
            psCheckProfilData.setInt(1, UID.get(0));
            resultSet = psCheckProfilData.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Uid not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error!");
                alert.show();
                return credentials;
            }
            else
            {
                while (resultSet != null && resultSet.next()) {
                    uid = resultSet.getString(1);
                    login = resultSet.getString(2);
                    email = resultSet.getString(3);
                    password = resultSet.getString(4);
                    securityQuestion = resultSet.getString(5);
                    securityAnswer = resultSet.getString(6);
                    profileImageUrl = resultSet.getString(7);
                    //TODO: chyba do poprawy, rozwiązanie tymczasowe
                    credentials.add(uid);
                    credentials.add(login);
                    credentials.add(email);
                    credentials.add(password);
                    credentials.add(securityQuestion);
                    credentials.add(securityAnswer);
                    credentials.add(profileImageUrl);
                    System.out.println("Uid: "+uid+" Login: "+login+" Email: "+email+" Password: "+password+" SecurityQ: "+
                            securityQuestion+" SecurityA: "+securityAnswer+" ProfileImageUrl: "+profileImageUrl);
                    return credentials;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return credentials;
        }
        return credentials;
    }

        public static UserData setProfileData(String username) {
        PreparedStatement psCheckProfilData = null;
        ResultSet resultSet = null;
        Connection emailCheckConnection = null;
        int uid;
        float money;
        UserData userData = null;

        try {
            emailCheckConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psCheckProfilData = emailCheckConnection.prepareStatement("SELECT uid, money\n" +
                    "\tFROM \"VirtualMerchant\".users WHERE login=?");
            psCheckProfilData.setString(1, username);
            resultSet = psCheckProfilData.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Login not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login not found!");
                alert.show();
                return userData;
            }
            else
            {
                resultSet.next();
                uid = resultSet.getInt("uid");
                money = resultSet.getFloat("money");
                userData = new UserData(uid, money);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }


    public static void setNewAvatar(String avatarUrl, int uid) {
        PreparedStatement psResetPassword = null;
        Connection resetPasswordConnection = null;
        try {
            resetPasswordConnection = DriverManager.getConnection(url, userDB, passwordDB);
            psResetPassword = resetPasswordConnection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET profile_image_url=?\n" +
                    "\tWHERE uid=?;");
            psResetPassword.setString(1, avatarUrl);
            psResetPassword.setInt(2, uid);
            psResetPassword.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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

    //---------------------------------------------Equipment&Shop-----------------------------------//

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


            while ( resultSet.next() ) {
                int iid = resultSet.getInt("iid");
                String name = resultSet.getString("name");
                float weight  = resultSet.getFloat("weight");
                float value  = resultSet.getFloat("value");
                String description  = resultSet.getString("description");
                int amount  = resultSet.getInt("amount");

                items.add(new Item(iid, name, description, weight, value, amount));
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
