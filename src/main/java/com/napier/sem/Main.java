package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class Main
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args)
    {
        // Create new Application
        Main a = new Main();

        // Connect to database
        a.connect();

        // Demonstration report of all capital cities - delete later
        /*
        ArrayList<CapitalCity> capitals = a.capitalsReport();
        a.displayCapitals(capitals);
        */

        // Disconnect from database
        a.disconnect();
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
        ArrayList<CapitalCity> ccArr = new ArrayList<CapitalCity>();

        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT city.ID, city.Name, country.Name, city.Population " +
                                "FROM city JOIN country ON (city.CountryCode = country.Code) " +
                                "WHERE city.ID IN (SELECT country.Capital FROM country)";
            ResultSet rs = st.executeQuery(stInput);

            // populate Arraylist with results of SQL statement
            while (rs.next())
            {
                CapitalCity cc = new CapitalCity();
                cc.id = rs.getInt("city.ID");
                cc.name = rs.getString("city.Name");
                cc.country = rs.getString("country.name");
                cc.population = rs.getInt("city.Population");
                ccArr.add(cc);
            }

            return ccArr;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Prints contents of Capital City ArrayList
     */
    public void displayCapitals(ArrayList<CapitalCity> ccArr)
    {
        System.out.println("Printing Capital City Report:");
        for (CapitalCity capitalCity : ccArr)
        {
            System.out.println(capitalCity.ToString());
        }
    }
}