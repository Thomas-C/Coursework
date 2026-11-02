package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Retrieves and displays total population reports.
 */
public class PopulationReport
{
    private String name;
    private long population;

    public PopulationReport(String name, long population)
    {
        this.name = name;
        this.population = population;
    }

    /**
     * Gets the total population of the world.
     */
    public static PopulationReport getWorldPopulation(Connection con)
    {
        String sql =
                "SELECT SUM(Population) AS TotalPopulation FROM country";

        return getReport(con, sql, null, "World");
    }

    /**
     * Gets the total population of a continent.
     */
    public static PopulationReport getContinentPopulation(
            Connection con, String continent)
    {
        String sql =
                "SELECT SUM(Population) AS TotalPopulation " +
                        "FROM country WHERE Continent = ?";

        return getReport(con, sql, continent, continent);
    }

    /**
     * Gets the total population of a region.
     */
    public static PopulationReport getRegionPopulation(
            Connection con, String region)
    {
        String sql =
                "SELECT SUM(Population) AS TotalPopulation " +
                        "FROM country WHERE Region = ?";

        return getReport(con, sql, region, region);
    }

    /**
     * Gets the population of a country.
     */
    public static PopulationReport getCountryPopulation(
            Connection con, String country)
    {
        String sql =
                "SELECT SUM(Population) AS TotalPopulation " +
                        "FROM country WHERE Name = ?";

        return getReport(con, sql, country, country);
    }

    /**
     * Runs a population query and creates a report from the result.
     */
    private static PopulationReport getReport(
            Connection con, String sql, String filter, String name)
    {
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        // Close the statement automatically after the query finishes.
        try (PreparedStatement statement = con.prepareStatement(sql))
        {
            // Filtered queries have a placeholder for the user's input.
            if (filter != null)
            {
                statement.setString(1, filter);
            }

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long population = results.getLong("TotalPopulation");

                    // A null total means no matching population was found.
                    if (results.wasNull())
                    {
                        return null;
                    }

                    return new PopulationReport(name, population);
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Could not retrieve population report.");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Displays the report in the console.
     */
    public static void displayPopulation(PopulationReport report)
    {
        if (report == null)
        {
            System.out.println("No population report available.");
            return;
        }

        System.out.printf("%-30s %20s%n", "Area", "Population");
        System.out.printf("%-30s %,20d%n",
                report.name, report.population);
    }
}