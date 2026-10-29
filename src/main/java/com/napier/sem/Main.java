package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


/** To run the application, write these in the terminal:
docker compose up -d --build db (builds the database)
 docker compose run --build --rm app (Builds the application with the JAR and runs the java application interactively)
 */

public class Main
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args) {

        int n;

        if (args.length > 0) {
            // Use the number supplied when starting the application
            n = Integer.parseInt(args[0]);

            if (n <= 0) {
                throw new IllegalArgumentException("Number of countries must be greater than zero");
            }
        } else {
            // Ask the user for a number
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.print("How many countries do you want to display? ");

                if (!input.hasNextLine()) {
                    throw new IllegalStateException("No input is available. Run it interactively or supply N as an argument");
                }

                try {
                    n = Integer.parseInt(input.nextLine().trim());
                    if (n > 0) {
                        break;

                    }
                } catch (NumberFormatException e) {
                    // Display the message below and ask again
                }
                System.out.println("Please enter a whole number greater than zero.");
            }
        }
        // Create new Application
        Main a = new Main();
        try {
            // Connect to database
            a.connect();

            if (a.con == null) {
                throw new IllegalStateException("Database connection failed");
            }

            // Retrieve and display all countries by population
            ArrayList<CountryReport> countries =
                    CountryReport.getAllCountries(a.con);


            if (countries == null) {
                throw new IllegalStateException("Could not retrieve countries.");
            }

            CountryReport.displayCountries(countries, "All countries: Largest population to smallest");


            // Displays the number of countries requested by the user or startup argument.
            ArrayList<CountryReport> topCountries =
                    CountryReport.getTopCountries(a.con, n);

            if (topCountries == null) {
                throw new IllegalStateException("Could not retrieve top countries");
            }

            CountryReport.displayCountries(topCountries, "Top " + n + " countries worldwide");

            // Demonstration report of all capital cities - delete later
        /*
        ArrayList<CapitalCity> capitals = a.capitalsReport();
        CapitalCity.displayCapitals(capitals);
        */
        } finally {
            // Disconnect from database
            a.disconnect();
        }
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "coursework",
                        "coursework");

                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Returns ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReport()
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getFullResultSet(con);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }


}