package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A class which represents a city report
 *
 * Contains methods to get city report data from a database, create ArrayLists of
 * CapitalCity objects, and print contents of a CapitalCity ArrayList
 */
public class CityReport
{

    public int id;
    public String name;
    public String country;
    public String district;
    public int population;

    /**
     * Returns String representation of CapitalCity object, not including ID
     * @return String representation of CapitalCity object, not including ID
     */
    public String ToString()
    {
        return ("Name: " + this.name + "  |  Country: " + this.country + "  |  District: " + this.district + "  |  Population: " + Integer.toString(this.population));
    }

    /**
     * Gets data of all capital cities in the database using an SQL statement
     * @param con A connection to the database
     * @return A ResultSet containing capital city data
     */
    public static ResultSet getFullResultSet(Connection con)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT city.ID, city.Name, country.Name, city.district, city.Population " +
                    "FROM city JOIN country ON (city.CountryCode = country.Code) " +
                    "ORDER BY city.Population DESC, city.Name ASC";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets data of all capital cities from a given continent in the database using an SQL statement
     * @param con A connection to the database
     * @param continent The continent to get city data from
     * @return A ResultSet containing capital city data
     */
    public static ResultSet getContinentResultSet(Connection con, String continent)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT city.ID, city.Name, country.Name, city.Population " +
                    "FROM city JOIN country ON (city.CountryCode = country.Code) " +
                    "WHERE city.ID IN (SELECT country.Capital FROM country) " +
                    "AND country.Continent = \'" + continent + "\' " +
                    "ORDER BY city.Population DESC, city.Name ASC";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets data of all capital cities from a given region in the database using an SQL statement
     * @param con A connection to the database
     * @param region The region to get city data from
     * @return A ResultSet containing capital city data
     */
    public static ResultSet getRegionResultSet(Connection con, String region)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT city.ID, city.Name, country.Name, city.Population " +
                    "FROM city JOIN country ON (city.CountryCode = country.Code) " +
                    "WHERE city.ID IN (SELECT country.Capital FROM country) " +
                    "AND country.Region = \'" + region + "\' " +
                    "ORDER BY city.Population DESC, city.Name ASC";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get City Report details");
            return null;
        }
    }

    /**
     * Makes CityReport objects based on data contained within ResultSet object
     * @param rs ResultSet containing CityReport data
     * @return ArrayList containing CityReport objects
     */
    public static ArrayList<CityReport> rsToArrLst(ResultSet rs)
    {
        ArrayList<CityReport> ccArr = new ArrayList<CityReport>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new CityReport object, populates with data, and adds to ArrayList
                CityReport cr = new CityReport();
                cr.id = rs.getInt("city.ID");
                cr.name = rs.getString("city.Name");
                cr.country = rs.getString("country.name");
                cr.district = rs.getString("city.district");
                cr.population = rs.getInt("city.Population");
                ccArr.add(cr);
            }

        }
        catch (Exception e)
        {
            // Returns null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create City report");
            return null;
        }

        return ccArr;
    }

    /**
     * Prints details of CityReport objects
     * @param crArr ArrayList of CityReport objects to be printed
     */
    public static void displayCities(ArrayList<CityReport> crArr)
    {
        if (crArr == null)
        {
            System.out.println("No cities in report");
            return;
        }
        System.out.println("Printing city report:");
        // Increments for each object in ArrayList
        for (CityReport cr : crArr)
        {
            if (cr == null)
            {
                continue;
            }
            System.out.println(cr.ToString());
        }
    }

}