package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A class which represents a capital city
 *
 * Contains methods to get capital city data from a database, create ArrayLists of
 * CapitalCity objects, and print contents of a CapitalCity ArrayList
 */
public class PoppingCities
{

    public int id;
    public String name;
    public String country;
    public int population;

    public int _count;

    public String _continent, _region, _country, _district;

    public PoppingCities(String continent, String region, String country, String district, int count){
        _continent = continent;
        _region = region;
        _country = country;
        _district = district;
        _count = count;
    }

    public String ToString()
    {
        return ("Name: " + this.name + "  |  Country: " + this.country + "  |  Population: " + Integer.toString(this.population));
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
            String stInput = "SELECT city.ID, city.Name, country.Name, city.Population " +
                    "FROM city JOIN country ON (city.CountryCode = country.Code) " +
                    "WHERE city.ID IN (SELECT country.Capital FROM country) " +
                    "" +
                    "ORDER BY city.Population";
            /**
             * Change above to only show 'count' number of items.
             */

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
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Makes CapitalCity objects based on data contained within ResultSet object
     * @param rs ResultSet containing capital city data
     * @return ArrayList containing CapitalCity objects
     */
    public ArrayList<PoppingCities> rsToArrLst(ResultSet rs)
    {
        ArrayList<PoppingCities> ccArr = new ArrayList<PoppingCities>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new CapitalCity object, populates with data, and adds to ArrayList
                PoppingCities cc = new PoppingCities(_continent, _region, _country, _district);
                cc.id = rs.getInt("city.ID");
                cc.name = rs.getString("city.Name");
                cc.country = rs.getString("country.name");
                cc.population = rs.getInt("city.Population");
                ccArr.add(cc);
            }

        }
        catch (Exception e)
        {
            // Returns null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create Capital City report");
            return null;
        }

        return ccArr;
    }

    /**
     * Prints details of CapitalCity objects
     * @param ccArr ArrayList of CapitalCity objects to be printed
     */
    public static void displayCapitals(ArrayList<PoppingCities> ccArr)
    {
        if (ccArr == null)
        {
            System.out.println("No capital cities in report");
            return;
        }
        System.out.println("Printing capital city report:");
        // Increments for each object in ArrayList
        for (PoppingCities cc : ccArr)
        {
            if (cc == null)
            {
                continue;
            }
            System.out.println(cc.ToString());
        }
    }

}