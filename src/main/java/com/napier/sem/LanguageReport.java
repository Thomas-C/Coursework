package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *  Stores countries details and provides the country report methods
 */

public class LanguageReport
{
    public String code;
    public String name;
    public String continent;
    public String region;
    public long population;
    public String capital;

    public String _language;

    public LanguageReport(String language){
        _language = language;
    }

    /**
     *  Gets all the countries ordered by population, largest first
     */
    public ArrayList<LanguageReport> getAllCountries(Connection con)
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
                            "ORDER BY country.Population DESC, country.Name ASC";

            /**
             *              Change THIS ^^^^^ honestly I'm not good enought at SQL to explain what you do here. you got this!
             */


            // Close the statement when this block finishes
            try (stmt)
            {
                // Execute SQL statement
                try (ResultSet rset = stmt.executeQuery(strSelect))
                {
                    // Create a list to store the countries
                    ArrayList<LanguageReport> countries = new ArrayList<>();

                    // Read each country returned by the query
                    while (rset.next())
                    {
                        LanguageReport country = new LanguageReport(_language);

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
    public static void displayCountries(ArrayList<LanguageReport> countries)
    {
        if (countries == null)
        {
            System.out.println("Cannot display report as the country data was not retrieved");
            return;

        }
        System.out.println("Country Report: Largest population to smallest");

        for (LanguageReport country : countries)
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
