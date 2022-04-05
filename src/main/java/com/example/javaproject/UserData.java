package com.example.javaproject;

public class UserData {
    private int uid;
    private float money;

    public UserData(int uid, float money)
    {
        this.uid = uid;
        this.money = money;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public float getMoney() {
        return money;
    }

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
