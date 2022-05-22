package com.example.javaproject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class InternetChecker {
    private static boolean isNetAvailable() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Connected");
            return true;

        } catch (Exception e) {
            System.out.println("Not connected");
            return false;
        }
    }
}


