package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A class which represents a population report
 *
 * Contains methods to get population data from a database, create ArrayLists of
 * PopulationReport objects, and print contents of a PopulationReport ArrayList
 */
public class PopulationReport
{

    public String name;
    public long population;

    /**
     * Returns String representation of PopulationReport object
     * @return String representation of PopulationReport object
     */
    public String ToString()
    {
        return ("Area: " + this.name +  "  |  Population: " + Long.toString(this.population));
    }

    /**
     * Gets the total population of the world
     * @param con A connection to the database
     * @return A ResultSet containing world population data
     */
    public static ResultSet getFullResultSet(Connection con)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT 'World' AS Name, " +
                    "SUM(Population) AS Population " +
                    "FROM country";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get world population");
            return null;
        }
    }

    /**
     * Gets the total population of a given continent
     * @param con A connection to the database
     * @param continent The continent to get population data from
     * @return A ResultSet containing continent population data
     */
    public static ResultSet getContinentResultSet(Connection con, String continent)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT Continent AS Name, " +
                    "SUM(Population) AS Population " +
                    "FROM country " +
                    "WHERE Continent = '" + continent + "' " +
                    "GROUP BY Continent";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get continent population");
            return null;
        }
    }

    /**
     * Gets the total population of a given region
     * @param con A connection to the database
     * @param region The region to get population data from
     * @return A ResultSet containing region population data
     */
    public static ResultSet getRegionResultSet(Connection con, String region)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT Region AS Name, " +
                    "SUM(Population) AS Population " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "GROUP BY Region";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get region population");
            return null;
        }
    }

    /**
     * Gets the population of a given country
     * @param con A connection to the database
     * @param country The country to get population data from
     * @return A ResultSet containing country population data
     */
    public static ResultSet getCountryResultSet(Connection con, String country)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT Name, Population " +
                    "FROM country " +
                    "WHERE Name = '" + country + "'";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get country population");
            return null;
        }
    }

    /**
     * Makes PopulationReport objects from data contained in a ResultSet
     * @param rs ResultSet containing population data
     * @return ArrayList containing PopulationReport objects
     */
    public static ArrayList<PopulationReport> rsToArrLst(ResultSet rs)
    {
        ArrayList<PopulationReport> prArr = new ArrayList<PopulationReport>();

        try
        {
            // Read each row of data in ResultSet
            while (rs.next())
            {
                // Create a report, populate it, and add it to the ArrayList
                PopulationReport pr = new PopulationReport();
                pr.name = rs.getString("Name");
                pr.population = rs.getLong("Population");
                prArr.add(pr);
            }
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create population report");
            return null;
        }

        return prArr;
    }

    /**
     * Prints details of PopulationReport objects
     * @param prArr ArrayList of PopulationReport objects to be printed
     */
    public static void displayPopulation(ArrayList<PopulationReport> prArr)
    {
        if (prArr == null)
        {
            System.out.println("No population data in report");
            return;
        }

        System.out.println("Printing population report:");

        // Print each object in ArrayList
        for (PopulationReport pr : prArr)
        {
            if (pr == null)
            {
                continue;
            }

            System.out.println(pr.ToString());
        }
    }

}
