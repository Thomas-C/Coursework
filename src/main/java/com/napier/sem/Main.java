package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String url = "jdbc:mysql://db:3306/world"
                + "?allowPublicKeyRetrieval=true&useSSL=false";

        // MySQL may still be starting when the application starts.
        for (int attempt = 1; attempt <= 30; attempt++) {
            System.out.println("Connecting to database: attempt " + attempt);

            try (Connection connection =
                         DriverManager.getConnection(
                                 url, "coursework", "coursework")) {

                System.out.println("Successfully connected to world database!");
                return;

            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }

            Thread.sleep(5000);
        }

        throw new IllegalStateException("Could not connect to the world database.");
    }
}