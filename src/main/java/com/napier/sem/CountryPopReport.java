package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *  Stores countries details and provides the country report methods
 */

public class CountryPopReport
{
    /**
     * SQL Variables
     */
    public String code;
    public String name;
    public String continent;
    public String region;
    public long population;
    public String capital;

    /**
     * Not SQL Variables 😊
     */

    public String _userContinent;
    public String _userRegion;

    public CountryPopReport(String userContinent, String userRegion){
        _userContinent = userContinent;
        _userRegion = userRegion;
    }


    /**
     *  Gets all the countries ordered by population, largest first
     */
    public ArrayList<CountryPopReport> getAllCountries(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                            "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population," +
                            "city.Name AS CapitalName " +
                            "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                                    "where country.Region=" + _userRegion + " where country.Continent=" + _userContinent +
                            "ORDER BY country.Population";


            // Close the statement when this block finishes
            try (stmt)
            {
                // Execute SQL statement
                try (ResultSet rset = stmt.executeQuery(strSelect))
                {
                    // Create a list to store the countries
                    ArrayList<CountryPopReport> countries = new ArrayList<>();

                    // Read each country returned by the query
                    while (rset.next())
                    {
                        CountryPopReport country = new CountryPopReport(_userContinent, _userRegion);

                        country.code = rset.getString("Code");
                        country.name = rset.getString("Name");
                        country.continent = rset.getString("Continent");
                        country.region = rset.getString("Region");
                        country.population = rset.getLong("Population");
                        country.capital = rset.getString("CapitalName");

                        countries.add(country);
                    }

                    return countries;
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
    /**
     *  Prints the country report
     */
    public static void displayCountries(ArrayList<CountryPopReport> countries)
    {
        if (countries == null)
        {
            System.out.println("Cannot display report as the country data was not retrieved");
            return;

        }
        System.out.println("Country Report: Largest population to smallest");

        for (CountryPopReport country : countries)
        {
            // Skip missing entries in the list
            if (country == null)
            {
                continue;
            }

            System.out.println(
                    "Code: " + country.code
                    + " | Name: " + country.name
                    + " | Continent: " + country.continent
                    + " | Region: " + country.region
                    + " | Population: " + country.population
                    + " | Capital: " + country.capital);

        }
    }
}
