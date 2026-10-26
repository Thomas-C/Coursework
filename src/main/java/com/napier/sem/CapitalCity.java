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
public class CapitalCity
{

    public int id;
    public String name;
    public String country;
    public int population;

    /**
     * Returns String representation of CapitalCity object, not including ID
     * @return String representation of CapitalCity object, not including ID
     */
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
                    "WHERE city.ID IN (SELECT country.Capital FROM country)";

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
    public static ArrayList<CapitalCity> rsToArrLst(ResultSet rs)
    {
        ArrayList<CapitalCity> ccArr = new ArrayList<CapitalCity>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new CapitalCity object, populates with data, and adds to ArrayList
                CapitalCity cc = new CapitalCity();
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
    public static void displayCapitals(ArrayList<CapitalCity> ccArr)
    {
        System.out.println("Printing Capital City Report:");
        // Increments for each object in ArrayList
        for (CapitalCity cc : ccArr)
        {
            System.out.println(cc.ToString());
        }
    }

}