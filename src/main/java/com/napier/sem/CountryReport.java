package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *  Stores countries details and provides the country report methods
 */

public class CountryReport
{
    public String code;
    public String name;
    public String continent;
    public String region;
    public long population;
    public String capital;

    /**
     *  Gets all the countries ordered by population, largest first
     */
    public static ArrayList<CountryReport> getAllCountries(Connection con)
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


            // Close the statement when this block finishes
            try (stmt)
            {
                // Execute SQL statement
                try (ResultSet rset = stmt.executeQuery(strSelect))
                {
                    // Create a list to store the countries
                    ArrayList<CountryReport> countries = new ArrayList<>();

                    // Read each country returned by the query
                    while (rset.next())
                    {
                        CountryReport country = new CountryReport();

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
    public static void displayCountries(ArrayList<CountryReport> countries)
    {
        if (countries == null)
        {
            System.out.println("Cannot display report as the country data was not retrieved");
            return;

        }
        System.out.println("Country Report: Largest population to smallest");

        for (CountryReport country : countries)
        {
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
