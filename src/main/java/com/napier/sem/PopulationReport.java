package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationReport
{
    private String name;
    private long population;

    public PopulationReport(String name, long population)
    {
        this.name = name;
        this.population = population;
    }

    // Total population across all countries.
    public static PopulationReport getWorldPopulation(Connection con)
    {
        if (con == null)
        {
            System.out.println("No database connection.");
            return null;
        }

        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country";

        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet results = statement.executeQuery())
        {
            if (results.next())
            {
                long total = results.getLong("TotalPopulation");

                if (!results.wasNull())
                {
                    return new PopulationReport("World", total);
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

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
            statement.setString(1, continent);

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

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
            statement.setString(1, region);

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

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
            statement.setString(1, country);

            try (ResultSet results = statement.executeQuery())
            {
                if (results.next())
                {
                    long total = results.getLong("TotalPopulation");

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

    public static void displayPopulation(PopulationReport report)
    {
        if (report == null)
        {
            System.out.println("No population report available.");
            return;
        }

        System.out.println("Area: " + report.name);
        System.out.println("Population: " + report.population);
    }
}
