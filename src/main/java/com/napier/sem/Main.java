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

        if (args.length == 0)
        {
            // Connect to database
            a.connect("localhost:33060", 30000);
        }
        else if (args.length == 2)
        {
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        else
        {
            throw new IllegalArgumentException("Supply both the database location and connection delay.");
        }

        // Displays only the three most populated countries in Central Africa
        System.out.println("Top 3 countries in central Africa:");
        ArrayList<CountryReport> topCountries = a.topCountriesReportRegion("Central Africa", 3);
        CountryReport.displayCountries(topCountries);



        // Demonstration report of all capital cities - delete later
        ArrayList<CapitalCity> capitals = a.capitalsReportRegion("Central Africa");
        CapitalCity.displayCapitals(capitals);


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Returns all countries ordered by population
     */

    public ArrayList<CountryReport> countriesReport()
    {
        return CountryReport.getAllCountries(con);
    }

    public ArrayList<CountryReport> countriesReportRegion(String region)
    {
        return CountryReport.getCountriesByRegion(con, region);
    }

    public ArrayList<CountryReport> countriesReportContinent(String continent)
    {
        return CountryReport.getCountriesByContinent(con, continent);
    }

    public ArrayList<CountryReport> topCountriesReportRegion(String region, int n)
    {
        return CountryReport.getTopCountriesByRegion(con, region, n);
    }
    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay)
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
                Thread.sleep(delay);

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location +
                                "/world?allowPublicKeyRetrieval=true&useSSL=false",
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
     * Returns ArrayList of CapitalCity Objects from worldwide data
     * @return an ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReport()
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getFullResultSet(con);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }

    /**
     * Returns ArrayList of CapitalCity Objects from given continent
     * @param continent The continent to get data from
     * @return as ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReportContinent(String continent)
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getContinentResultSet(con,continent);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }

    /**
     * Returns ArrayList of CapitalCity Objects from given Region
     * @param Region The Region to get data from
     * @return as ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReportRegion(String Region)
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getRegionResultSet(con,Region);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }


}