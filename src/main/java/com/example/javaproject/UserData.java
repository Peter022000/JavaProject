package com.example.javaproject;

/**
 * Funkcja przechowująca dane użytkownika
 */

public class UserData {
    private int uid;
    private float money;

    /**
     * Konstruktor tworzący obiekt zawierający informacje o użytkowniku
     * @param uid id użytkownika
     * @param money pieniądze użytkownika
     */

    public UserData(int uid, float money)
    {
        this.uid = uid;
        this.money = money;
    }

    /**
     * Funkcja zwraca id użytkownika
     * @return id użytkownika
     */

    public int getUid() {
        return uid;
    }

    /**
     * Funkcja zwracająca pieniądze użytkownika
     * @return pieniądze użytkownika
     */

    public float getMoney() {
        return money;
    }

    /**
     * Funkcja ustawiająca pieniądze użytkownika
     * @param money
     */

    public void setMoney(float money) {
        this.money = money;
    }
    
    @Override
    public String toString() {
        return "UserData{" +
                "uid=" + uid +
                ", money=" + money +
                '}';
    }
}
