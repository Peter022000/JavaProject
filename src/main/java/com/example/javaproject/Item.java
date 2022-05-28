package com.example.javaproject;

/**
 * Klasa odpowiedzialna za przechowywanie przedmiotu
 */

public class Item {
    private int iid;
    private String name;
    private String description;
    private float weight;
    private float value;

    private int amount;

    /**
     * Konstruktor tworzący obiekt zawierający dane dotyczące przedmiotu
     * @param iid id przedmiotu
     * @param name nazwa przedmiotu
     * @param description opis przedmiotu
     * @param weight waga przedmiotu
     * @param value wartość przedmiotu
     * @param amount ilość przedmiotu
     */

    public Item(int iid, String name, String description, float weight, float value, int amount) {
        this.iid = iid;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.amount = amount;
    }

    /**
     * Funkcja zwracająca id przedmiotu
     * @return id przemiotu
     */

    public int getIid() {
        return iid;
    }

    /**
     * Funkcja zwracająca id przedmiotu
     * @return id przemiotu
     */

    public String getName() {
        return name;
    }

    /**
     * Funkcja zwracająca nazwę przedmiotu
     * @return nazwa przedmiotu
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Funkcja zwracająca opis przedmiotu
     * @return opis przedmiotu
     */

    public String getDescription() {
        return description;
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
