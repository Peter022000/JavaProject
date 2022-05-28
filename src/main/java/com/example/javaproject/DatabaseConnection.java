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

/**
 * Klasa obsługująca połączenia z bazą danych
 */

public class DatabaseConnection {

    private final static String url = "jdbc:postgresql://195.150.230.210:5436/2022_krol_marcin";
    private final static String userDB = "2022_krol_marcin";
    private final static String passwordDB = "34300";

    private Connection connection;

    /**
     * Konstruktor nawiązujący połączenie z bazą danych
     * @throws SQLException
     */

    public DatabaseConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, userDB, passwordDB);
        }
        catch (SQLException e) {
            showErrorMessage(e.getErrorCode()+": "+e.getMessage());
        }
    }

    /**
     * Funkcja wykonuje zapytanie do bazy i tworzy nowego użytkownika.
     * @param username nazwa użytkownika
     * @param email email użytkownika
     * @param securityQuestion pytanie bezpieczeństwa użytkownika
     * @param securityAnswer odpowiedź na pytanie bezpieczeństwa użytkownika
     * @param password hasło użytkownika
     * @param error kod błędu
     * @param event zdarzenie
     * @return
     * @throws SQLException
     */
    boolean createAccount(String username, String email, String securityQuestion, String securityAnswer, String password, Label error, ActionEvent event) throws SQLException {
        String query = "INSERT INTO \"VirtualMerchant\".users(login, password, security_question, security_answer, email)\n" +
                "\tVALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = connection.prepareCall(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, securityQuestion);
            pst.setString(4, securityAnswer);
            pst.setString(5, email);
            pst.executeUpdate();
            System.out.println("Account created!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            error.setVisible(true);
            sqlException(e);
            return false;
        }
    }

    /**
     * Funkcja wykonuje zapytanie do bazy danych i sprawdza, czy dane użytkownika się zgadzają, jeżeli tak zaloguje użytkownika do aplikacji.
     * @param event
     * @param username nazwa użytkownika
     * @param password hasło użytkownika
     * @return
     * @throws SQLException
     */
    public boolean loginCheck(ActionEvent event, String username, String password) throws SQLException {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;
        try {
            psCheckLogin = connection.prepareStatement("SELECT password FROM \"VirtualMerchant\".users WHERE login=?");
            psCheckLogin.setString(1, username);
            resultSet = psCheckLogin.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the db!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login is incorrect!");
                alert.show();
                return false;
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
            sqlException(e);
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    sqlException(e);
                    return false;
                }
            }
            if (psCheckLogin != null) {
                try {
                    psCheckLogin.close();
                } catch (SQLException e) {
                    sqlException(e);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Funkcja sprawdza czy dany użytkownik już istnieje w bazie danych.
     * @param username nazwa użytkownika
     * @return
     * @throws SQLException
     */
    public boolean checkIfLoginExist(String username) throws SQLException {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;

        try {
            psCheckLogin = connection.prepareStatement("SELECT password FROM \"VirtualMerchant\".users WHERE login=?");
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
            sqlException(e);
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    sqlException(e);
                    return false;
                }
            }
            if (psCheckLogin != null) {
                try {
                    psCheckLogin.close();
                } catch (SQLException e) {
                    sqlException(e);
                    return false;
                }
            }
        }
    }

    /**
     * Funkcja pobiera login na podstawie podanego uid z bazy danych.
     * @param UID uid użytkownika
     * @return zwraca login
     * @throws SQLException
     */
    public String getLogin(int UID) throws SQLException {
        PreparedStatement psCheckLogin = null;
        ResultSet resultSet = null;

        try {
            psCheckLogin = connection.prepareStatement("SELECT login FROM \"VirtualMerchant\".users WHERE uid=?");
            psCheckLogin.setInt(1, UID);
            resultSet = psCheckLogin.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Error, no such uid");
            } else {
                resultSet.next();
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage(e.getErrorCode()+": "+e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    sqlException(e);
                }
            }
            if (psCheckLogin != null) {
                try {
                    psCheckLogin.close();
                } catch (SQLException e) {
                    sqlException(e);
                }
            }
        }
        return "Error";
    }

    /**
     * Funkcja pozwala na zmianę loginu.
     * @param newLogin nowa nazwa użytkownika
     * @param oldLogin stary login użytkownika
     * @throws SQLException
     */
    public void changeLogin(String newLogin, String oldLogin) throws SQLException {
        PreparedStatement psResetLogin = null;
        try {
            psResetLogin = connection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET login=?\n" +
                    "\tWHERE login=?;");
            psResetLogin.setString(1, newLogin);
            psResetLogin.setString(2, oldLogin);
            psResetLogin.executeUpdate();
        }
        catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Funkcja pozwala na zmianę emaila.
     * @param newEmail nowy email
     * @param oldEmail stary email
     * @throws SQLException
     */
    public void changeEmail(String newEmail, String oldEmail) throws SQLException {
        PreparedStatement psResetLogin = null;
        try {
            psResetLogin = connection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET email=?\n" +
                    "\tWHERE email=?;");
            psResetLogin.setString(1, newEmail);
            psResetLogin.setString(2, oldEmail);
            psResetLogin.executeUpdate();
        }
        catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Funkcja pozwala na zwrócenie wszystkich danych danego użytkownika.
     * @param UID uid użytkownika
     * @return zwraca dane użytkownika
     * @throws SQLException
     */
    public ArrayList<String> setProfileData(ArrayList<Integer> UID) throws SQLException {
        PreparedStatement psCheckProfilData = null;
        ResultSet resultSet = null;
        String uid = null;
        String login = null;
        String email = null;
        String password = null;
        String securityQuestion = null;
        String securityAnswer = null;
        String profileImageUrl = null;
        ArrayList<String> credentials = new ArrayList<String>();

        try {
            psCheckProfilData = connection.prepareStatement("SELECT uid, login, email, password, security_question, security_answer, profile_image_url\n" +
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
                    credentials.add(uid);
                    credentials.add(login);
                    credentials.add(email);
                    credentials.add(password);
                    credentials.add(securityQuestion);
                    credentials.add(securityAnswer);
                    credentials.add(profileImageUrl);
                    return credentials;
                }
            }

        } catch (SQLException e) {
            sqlException(e);
            return credentials;
        }
        return credentials;
    }

    /**
     * Funkcja zwraca uid oraz liczbę pieniędzy użytkownika.
     * @param username nazwa użytkownika
     * @return zwraca dane użytkownika
     * @throws SQLException
     */
    public UserData setProfileData(String username) throws SQLException {
        PreparedStatement psCheckProfilData = null;
        ResultSet resultSet = null;
        int uid;
        float money;
        UserData userData = null;

        try {
            psCheckProfilData = connection.prepareStatement("SELECT uid, money\n" +
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
            sqlException(e);
        }
        return userData;
    }


    /**
     * Funkcja zmienia avatar użytkownika.
     * @param avatarUrl avatar
     * @param uid uid użytkownika
     * @throws SQLException
     */
    public void setNewAvatar(String avatarUrl, int uid) throws SQLException {
        PreparedStatement psResetPassword = null;
        try {
            psResetPassword = connection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET profile_image_url=?\n" +
                    "\tWHERE uid=?;");
            psResetPassword.setString(1, avatarUrl);
            psResetPassword.setInt(2, uid);
            psResetPassword.executeUpdate();
        }
        catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Funkcja sprawdzająca, czy podany email znajduje się w bazie danych
     * @param event zdarzenie
     * @param email email użytkownika
     * @return prawda lub fałsz
     * @throws SQLException
     */
    public boolean emailCheck(ActionEvent event, String email) throws SQLException {
        PreparedStatement psCheckEmail = null;
        ResultSet resultSet = null;

        try {
            psCheckEmail = connection.prepareStatement("SELECT email FROM \"VirtualMerchant\".users WHERE email=?");
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
            sqlException(e);
            return false;
        }
        return true;
    }

    /**
     * Funkcja sprawdzająca poprawność odpowiedzi na pytanie bezpieczeństwa
     * @param answer odpowiedź
     * @param email email użytkownika
     * @return
     * @throws SQLException
     */
    public boolean securityAnswerCheck(String answer, String email) throws SQLException {
        PreparedStatement psCheckSecurityAnswer = null;
        ResultSet resultSet = null;

        try {
            psCheckSecurityAnswer = connection.prepareStatement("SELECT security_answer FROM \"VirtualMerchant\".users WHERE email=?");
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
            sqlException(e);
            return false;
        }
        return true;
    }

    /**
     * Pobiera z bazy danych pytanie bezpieczeństwa użytkownika
     * @param event zdarzenie
     * @param email email użytkownika
     * @param securityQuestion pytanie bezpieczeństwa
     * @throws SQLException
     */
    public void setSecurityQuestionLabel(ActionEvent event, String email, Label securityQuestion) throws SQLException {

        PreparedStatement psCheckSecurityQuestion = null;
        ResultSet resultSet = null;
        try {
            psCheckSecurityQuestion = connection.prepareStatement("SELECT security_question\n" +
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
            sqlException(e);
        }
    }

    /**
     * Funkcja zmieniająca hasło użytkownika w bazie danych
     * @param event zdarzenie
     * @param newPassword nowe hasło
     * @param email email użytkownika
     * @throws SQLException
     */
    public void resetPassword(ActionEvent event, String newPassword, String email) throws SQLException {
        PreparedStatement psResetPassword = null;
        //ResultSet resultSet = null;
        try {
            psResetPassword = connection.prepareStatement("UPDATE \"VirtualMerchant\".users\n" +
                    "\tSET password=?\n" +
                    "\tWHERE email=?;");
            psResetPassword.setString(1, newPassword);
            psResetPassword.setString(2, email);
            psResetPassword.executeUpdate();
        }
        catch (SQLException e) {
            sqlException(e);
        }
    }

    //---------------------------------------------Equipment&Shop-----------------------------------//

    /**
     * Funkcja pobierająca przedmioty użytkownika z bazy danych
     * @param uid id użytkownika
     * @return przedmioty
     * @throws SQLException
     */

    public ObservableList<Item> getItems(int uid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
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
            sqlException(e);
        }
        return items;
    }

    /**
     * Funkcja pobierająca przedmioty z wybranego sklepu z bazy danych
     * @param sid id sklepu
     * @return przedmioty
     * @throws SQLException
     */

    public ObservableList<Item> getShopItems(int sid) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery( "SELECT s.sid, i.*, s.amount\n" +
                    "FROM \"VirtualMerchant\".shops as s\n" +
                    "INNER JOIN \"VirtualMerchant\".items as i\n" +
                    "ON i.iid = s.iid\n" +
                    "WHERE s.sid = " + sid +"\n" +
                    "AND amount > 0" +
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
            sqlException(e);
        }
        return items;
    }

    /**
     * Funkcja zmniejszająca ilość przedmiotu z ekwipunku użytkownika w bazie danych
     * @param uid id użytkownika
     * @param iid id przedmiotu
     * @throws SQLException
     */

    public void deleteItemFromEquipment(int uid, int iid) throws SQLException {
        Statement statement = null;

        try {
            statement = connection.createStatement();

            statement.executeUpdate( "UPDATE \"VirtualMerchant\".equipments\n" +
                    "\tSET amount=amount-1\n" +
                    "\tWHERE uid = " + uid + " AND iid = " + iid + ";");
        }
        catch (SQLException e) {
            sqlException(e);
        }
    }

    /**
     * Funkcja zwiększająca ilość lub dodająca przedmiot w ekwipunku użytkownika w bazie danych.
     * Po kupieniu przedmiotu zmniejsza jego ilość w sklepie w bazie danych
     * @param sid id sklepu
     * @param uid id użytkownika
     * @param iid id przedmiotu
     * @param money pieniądze
     * @return pieniądze po odjęciu wartości przedmiotu
     * @throws SQLException
     */

    public float buyItemFromShop(int sid, int uid, int iid, float money) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
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
            sqlException(e);
        }
        return money;
    }

    /**
     * Funkcja zwracająca połączenie z bazą danych
     * @return
     */

    public Connection getConnection() {
        return connection;
    }

    /**
     * Funkcja nawiązująca połączenie z bazą danych
     * @throws SQLException
     */

    public void setConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, userDB, passwordDB);
    }

    /**
     * Funkcja wyświetlająca komunikat o błędzie SQL oraz nawiązująca połączenie
     * @param e błąd SQL
     * @throws SQLException
     */

    private void sqlException(SQLException e) throws SQLException {
        e.printStackTrace();
        showErrorMessage(e.getErrorCode()+": "+e.getMessage());
        this.connection = DriverManager.getConnection(url, userDB, passwordDB);
    }

    /**
     * Funkcja wyświetlająca komunikat
     * @param message treść komunikatu
     */

    private void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
