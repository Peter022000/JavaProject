package com.example.javaproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShopController {
    @FXML
    private Label shopLabel;

    private String shop;

    public void initialize() throws IOException {
        readSelectedShop();
        shopLabel.setText(shop);
    }

    public static void saveSelectedShop(String shop) throws IOException {
        String fileName = "selectedShop.txt";
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(shop);
        myWriter.close();
    }

    private void readSelectedShop() throws IOException {
        String fileName = "selectedShop.txt";
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);

        this.shop = myReader.nextLine();

        myReader.close();
    }

}