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
     * Gets all countries worldwide
     */
    public static ArrayList<CountryReport> getAllCountries(Connection con)
    {
        return getCountries(con, "");
    }

    /**
     * Gets countries in one continent
     */

    public static ArrayList<CountryReport> getCountriesByContinent(Connection con, String continent)
    {
        return getCountries(con, "WHERE country.Continent = '" + continent + "'");
    }

    /**
     * Gets countries in one region
     */
    public static ArrayList<CountryReport> getCountriesByRegion(Connection con, String region)
    {
        return getCountries(con, "WHERE country.Region = '" + region + "' ");
    }

    public static ArrayList<CountryReport> getCountries(Connection con, String filter)
    {
        return getCountries(con, filter, 0);
    }
    /**
     *  Runs the query and reads countries, with the largest populations first
     */
    public static ArrayList<CountryReport> getCountries(Connection con, String filter, int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                            "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population," +
                            "city.Name AS CapitalName " +
                            "FROM country LEFT JOIN city ON country.Capital = city.ID " + filter +
                            " ORDER BY country.Population DESC, country.Name ASC";
            if (n > 0)
            {
                strSelect += " LIMIT " + n;
            }


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

    public static ArrayList<CountryReport> getTopCountries(Connection con, int n)
    {
        return getCountries(con, "", n);
    }

    public static ArrayList<CountryReport> getTopCountriesByContinent(Connection con, String continent, int n)
    {
        return getCountries(con, "WHERE country.Continent = '" + continent + "' ", n);
    }

    public static ArrayList<CountryReport> getTopCountriesByRegion(Connection con, String region, int n)
    {
        return getCountries(con, "WHERE country.Region = '" + region + "' ", n);
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
