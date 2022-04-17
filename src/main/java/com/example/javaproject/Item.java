package com.example.javaproject;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class Item {
    private int iid;
    private String name;
    private String description;
    private float weight;
    private float value;

    private int amount;

    public Item(int iid, String name, String description, float weight, float value, int amount) {
        this.iid = iid;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.amount = amount;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                ", amount=" + amount +
                "}\n";
    }
}
