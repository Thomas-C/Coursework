package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Stores population data and retrieves population totals from the database.
public class PopulationReport
{
    private String name;
    private long population;

    // Creates a report with an area name and its population.
    public PopulationReport(String name, long population)
    {
        this.name = name;
        this.population = population;
    }

    // Gets the total population of all countries.
    public static PopulationReport getWorldPopulation(Connection con)
    {
        // Stop if no database connection has been provided.
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country";

        // Automatically close the statement after use.
        try (PreparedStatement statement = con.prepareStatement(sql))
        {
            // Run the query and automatically close its results after use.
            try (ResultSet results = statement.executeQuery())
            {
                if (results.next() && !results.wasNull())
                {
                    long total = results.getLong("TotalPopulation");

                    // Only create a report if the total is not SQL NULL.
                    if (!results.wasNull())
                    {
                        return new PopulationReport("World", total);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            // Print any database error.
            System.out.println(e.getMessage());
        }

        // No report could be created.
        return null;
    }

    // Gets the total population of countries in the given continent.
    public static PopulationReport getContinentPopulation(
            Connection con, String continent)
    {
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT SUM(Population) AS TotalPopulation "
                + "FROM country WHERE Continent = ?";

        try (PreparedStatement statement = con.prepareStatement(sql))
        {
            // Replace the query's placeholder with the continent name.
            statement.setString(1, continent);

            try (ResultSet results = statement.executeQuery())
            {
                // Move to the row containing the population total.
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

                    // Return a report only if the query produced a non-null total.
                    if (!results.wasNull())
                    {
                        return new PopulationReport(continent, total);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Gets the total population of countries in the given region.
    public static PopulationReport getRegionPopulation(
            Connection con, String region)
    {
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT SUM(Population) AS TotalPopulation "
                + "FROM country WHERE Region = ?";

        try (PreparedStatement statement = con.prepareStatement(sql))
        {
            // Replace the query's placeholder with the region name.
            statement.setString(1, region);

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

                    // Check that the total is not SQL NULL before using it.
                    if (!results.wasNull())
                    {
                        return new PopulationReport(region, total);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Gets the population for the given country name.
    public static PopulationReport getCountryPopulation(
            Connection con, String country)
    {
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT SUM(Population) AS TotalPopulation "
                + "FROM country WHERE Name = ?";

        try (PreparedStatement statement = con.prepareStatement(sql))
        {
            // Replace the query's placeholder with the country name.
            statement.setString(1, country);

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

                    // Create a report if a non-null population was returned.
                    if (!results.wasNull())
                    {
                        return new PopulationReport(country, total);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Prints the report's area name and population.
    public static void displayPopulation(PopulationReport report)
    {
        // Avoid accessing a report that does not exist.
        if (report == null)
        {
            System.out.println("No population report available.");
            return;
        }

        System.out.println("Area: " + report.name);
        System.out.println("Population: " + report.population);
    }
}
