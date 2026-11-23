package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Retrieves and displays total population reports.
 */
public class PopulationReport
{
    public String name;
    public long population;
    public double cityPop;
    public double nonCityPop;

    /**
     * returns a string representation of PopulationReport object
     * @return a string representation of PopulationReport object
     */
    public String ToString()
    {
        return ("Name: " + this.name + "  |  Population: " + Long.toString(this.population) + "  |  In cities: " + Double.toString(this.cityPop) + "%  |  Not in cities: " + Double.toString(this.nonCityPop) + "%");
    }

    /**
     * gets population data and groups it by continent
     * @param con a connection to the database
     * @return a ResultSet containing population data
     */
    public static ResultSet getPopResultSetContinent(Connection con)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT country.Continent AS popArea, SUM(country.Population) AS popTotal " +
                    "FROM country " +
                    "GROUP BY country.Continent";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * gets population data from all the cities in a given continent
     * @param con a connection to the database
     * @param continent name of the continent to get data from
     * @return total population of all cities in a given continent
     */
    public static double getPopResultSetContinentCity(Connection con, String continent)
    {
        try
        {
            double popCity = 0;
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(city.Population) AS popCity " +
                    "FROM city " +
                    "WHERE city.CountryCode IN " +
                        "(SELECT country.Code " +
                        "FROM country " +
                        "WHERE country.Continent = \'" + continent + "\')";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                popCity = rs.getDouble("popCity");
            }
            return popCity;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return 0;
        }
    }

    /**
     * gets population data and groups it by region
     * @param con a connection to the database
     * @return a ResultSet containing population data
     */
    public static ResultSet getPopResultSetRegion(Connection con)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT country.Region AS popArea, SUM(country.Population) AS popTotal " +
                    "FROM country " +
                    "GROUP BY country.Region";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * gets population data from all the cities in a given region
     * @param con a connection to the database
     * @param region name of the region to get data from
     * @return total population of all cities in a given region
     */
    public static double getPopResultSetRegionCity(Connection con, String region)
    {
        try
        {
            double popCity = 0;
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(city.Population) AS popCity " +
                    "FROM city " +
                    "WHERE city.CountryCode IN " +
                    "(SELECT country.Code " +
                    "FROM country " +
                    "WHERE country.Region = \'" + region + "\')";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                popCity = rs.getDouble("popCity");
            }
            return popCity;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return 0;
        }
    }

    /**
     * gets population data and groups it by country
     * @param con a connection to the database
     * @return a ResultSet containing population data
     */
    public static ResultSet getPopResultSetCountry(Connection con)
    {
        try
        {
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT country.Code, country.Name AS popArea, country.Population AS popTotal " +
                    "FROM country";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            return rs;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    /**
     * gets population data from all the cities in a given country
     * @param con a connection to the database
     * @param country name of the country to get data from
     * @return total population of all cities in a given country
     */
    public static double getPopResultSetCountryCity(Connection con, String country)
    {
        try
        {
            double popCity = 0;
            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(city.Population) AS popCity " +
                    "FROM city " +
                    "WHERE city.CountryCode = \'" + country + "\'";

            // Create and return ResultSet object
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                popCity = rs.getDouble("popCity");
            }
            return popCity;
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
            return 0;
        }
    }

    /**
     * creates an ArrayList of PopulationReport objects separated by continent
     * @param con a connection to the database
     * @param rs ResultSet containing population data
     * @return ArrayList of PopulationReport objects separated by continent
     */
    public static ArrayList<PopulationReport> rsToArrLstContinent(Connection con, ResultSet rs)
    {
        ArrayList<PopulationReport> ccArr = new ArrayList<PopulationReport>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new PopulationReport object, populates with data, and adds to ArrayList
                PopulationReport pr = new PopulationReport();
                String continentName = rs.getString("popArea");
                long totalPop = rs.getLong("popTotal");

                // Uses new SQL Query to get population of cities in given continent
                double cityPop = getPopResultSetContinentCity(con, continentName);
                double cityPopRatio = (cityPop / totalPop) * 100;
                double nonCityPopRatio = 100 - cityPopRatio;

                pr.name = continentName;
                pr.population = totalPop;
                pr.cityPop = (double) Math.round(cityPopRatio * 100) / 100;
                pr.nonCityPop = (double) Math.round(nonCityPopRatio * 100) / 100;

                ccArr.add(pr);
            }

        }
        catch (Exception e)
        {
            // Returns null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create Population report");
            return null;
        }

        return ccArr;
    }

    /**
     * creates an ArrayList of PopulationReport objects separated by region
     * @param con a connection to the database
     * @param rs ResultSet containing population data
     * @return ArrayList of PopulationReport objects separated by region
     */
    public static ArrayList<PopulationReport> rsToArrLstRegion(Connection con, ResultSet rs)
    {
        ArrayList<PopulationReport> ccArr = new ArrayList<PopulationReport>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new PopulationReport object, populates with data, and adds to ArrayList
                PopulationReport pr = new PopulationReport();
                String regionName = rs.getString("popArea");
                long totalPop = rs.getLong("popTotal");

                // Uses new SQL Query to get population of cities in given region
                double cityPop = getPopResultSetRegionCity(con, regionName);
                double cityPopRatio = (cityPop / totalPop) * 100;
                double nonCityPopRatio = 100 - cityPopRatio;

                pr.name = regionName;
                pr.population = totalPop;
                pr.cityPop = (double) Math.round(cityPopRatio * 100) / 100;
                pr.nonCityPop = (double) Math.round(nonCityPopRatio * 100) / 100;

                ccArr.add(pr);
            }

        }
        catch (Exception e)
        {
            // Returns null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create Population report");
            return null;
        }

        return ccArr;
    }

    /**
     * Creates an ArrayList of PopulationReport objects separated by country
     * @param con a connection to the database
     * @param rs ResultSet containing population data
     * @return ArrayList of PopulationReport objects separated by country
     */
    public static ArrayList<PopulationReport> rsToArrLstCountry(Connection con, ResultSet rs)
    {
        ArrayList<PopulationReport> ccArr = new ArrayList<PopulationReport>();
        try
        {
            // Increments for each row of data in ResultSet
            while (rs.next())
            {
                // Creates new PopulationReport object, populates with data, and adds to ArrayList
                PopulationReport pr = new PopulationReport();
                String CountryCode = rs.getString("country.Code");
                long totalPop = rs.getLong("popTotal");

                // Uses new SQL Query to get population of cities in given region
                double cityPop = getPopResultSetCountryCity(con, CountryCode);
                double cityPopRatio = (cityPop / totalPop) * 100;
                double nonCityPopRatio = 100 - cityPopRatio;

                pr.name = rs.getString("popArea");
                pr.population = totalPop;
                pr.cityPop = (double) Math.round(cityPopRatio * 100) / 100;
                pr.nonCityPop = (double) Math.round(nonCityPopRatio * 100) / 100;

                ccArr.add(pr);
            }

        }
        catch (Exception e)
        {
            // Returns null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to create Population report");
            return null;
        }

        return ccArr;
    }

    /**
     * Prints details of PopulationReports in an ArrayList
     * @param prArr ArrayList containing PopulationReport objects
     */
    public static void displayPopulationReport(ArrayList<PopulationReport> prArr)
    {
        if (prArr == null)
        {
            System.out.println("Report Empty");
            return;
        }
        System.out.println("Printing Population report:");
        // Increments for each object in ArrayList
        for (PopulationReport pr : prArr)
        {
            if (pr == null)
            {
                continue;
            }
            System.out.println(pr.ToString());
        }
    }


    /**
     * prints the population worldwide
     * @param con a conection to the database
     */
    public static void worldwidePopulation(Connection con)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(population) AS pop FROM country";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Worldwide population: " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }

    /**
     * prints the population of a given continent
     * @param con a connection to the database
     * @param continent name of the continent to get population of
     */
    public static void continentPopulation(Connection con, String continent)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(Population) AS pop FROM country " +
                    "WHERE Continent = \'" + continent + "\'";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Population of continent " + continent + ": " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }

    /**
     * prints the population of a given region
     * @param con a connection to the database
     * @param region name of the region to get population of
     */
    public static void regionPopulation(Connection con, String region)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(Population) AS pop FROM country " +
                    "WHERE Region = \'" + region + "\'";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Population of region " + region + ": " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }

    /**
     * prints the population of a given country
     * @param con a connection to the database
     * @param country name of the country to get population of
     */
    public static void countryPopulation(Connection con, String country)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT Population AS pop FROM country " +
                    "WHERE name = \'" + country + "\'";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Population of country " + country + ": " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }

    /**
     * prints the population of a given district
     * @param con a connection to the database
     * @param district name of the district to get population of
     */
    public static void districtPopulation(Connection con, String district)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT SUM(Population) AS pop FROM city " +
                    "WHERE District = \'" + district + "\'";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Population of district " + district + ": " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }

    /**
     * prints the population of a given city
     * @param con a connection to the database
     * @param city name of the city to get population of
     */
    public static void cityPopulation(Connection con, String city)
    {
        try
        {
            long population = 0;

            // Create and execute SQL statement
            Statement st = con.createStatement();
            String stInput = "SELECT Population AS pop FROM city " +
                    "WHERE Name = \'" + city + "\'";

            // Create ResultSet object and retrieve data
            ResultSet rs = st.executeQuery(stInput);
            while (rs.next())
            {
                population = rs.getLong("pop");
            }
            System.out.println("Population of city " + city + ": " + Long.toString(population));
        }
        catch (Exception e)
        {
            // Return null in case of error
            System.out.println(e.toString());
            System.out.println("Failed to get Population details");
        }
    }


}