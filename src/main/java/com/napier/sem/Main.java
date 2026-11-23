package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class Main
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args)
    {
        // Create new Application
        Main a = new Main();

        if (args.length == 0)
        {
            // Connect to database
            a.connect("localhost:33060", 30000);
        }
        else if (args.length == 2)
        {
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        else
        {
            throw new IllegalArgumentException("Supply both the database location and connection delay.");
        }
        // Retrieve and display all countries by population
        /*
        ArrayList<CountryReport> countries = a.countriesReport();
        CountryReport.displayCountries(countries);
        */


        // Demonstration report of all capital cities - delete later
        //ArrayList<CapitalCity> capitals = a.capitalsReportRegion("Central Africa");
        //CapitalCity.displayCapitals(capitals);

        //ArrayList<PopulationReport> capPop = a.populationReportCountry();
        //PopulationReport.displayPopulationReport(capPop);

        //a.worldPop();
        //a.continentPop("Europe");
        //a.regionPop("Southern Europe");
        //a.countryPop("Italy");
        //a.districtPop("Nagano");
        //a.cityPop("Edinburgh");

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Returns all countries ordered by population
     */
    public ArrayList<CountryReport> countriesReport()
    {
        return CountryReport.getAllCountries(con);
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(delay);

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location +
                                "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "coursework",
                        "coursework");

                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }




    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Returns ArrayList of CapitalCity Objects from worldwide data
     * @return an ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReport()
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getFullResultSet(con);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }

    /**
     * Returns ArrayList of CapitalCity Objects from given continent
     * @param continent The continent to get data from
     * @return as ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReportContinent(String continent)
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getContinentResultSet(con,continent);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }

    /**
     * Returns ArrayList of CapitalCity Objects from given Region
     * @param Region The Region to get data from
     * @return as ArrayList of CapitalCity Objects
     */
    public ArrayList<CapitalCity> capitalsReportRegion(String Region)
    {
        // Create and execute SQL statement
        ResultSet rs = CapitalCity.getRegionResultSet(con,Region);

        // populate Arraylist with results of SQL statement
        ArrayList<CapitalCity> ccArr = CapitalCity.rsToArrLst(rs);
        return ccArr;
    }


    /**
     * returns ArrayList containing PopulationReport objects by continent
     * @return ArrayList containing PopulationReport objects by continent
     */
    public ArrayList<PopulationReport> populationReportContinent()
    {
        ResultSet rs = PopulationReport.getPopResultSetContinent(con);
        ArrayList<PopulationReport> prArr = PopulationReport.rsToArrLstContinent(con, rs);
        return prArr;
    }

    /**
     * returns ArrayList containing PopulationReport objects by region
     * @return ArrayList containing PopulationReport objects by region
     */
    public ArrayList<PopulationReport> populationReportRegion()
    {
        ResultSet rs = PopulationReport.getPopResultSetRegion(con);
        ArrayList<PopulationReport> prArr = PopulationReport.rsToArrLstRegion(con, rs);
        return prArr;
    }

    /**
     * returns ArrayList containing PopulationReport objects by region
     * @return ArrayList containing PopulationReport objects by region
     */
    public ArrayList<PopulationReport> populationReportCountry()
    {
        ResultSet rs = PopulationReport.getPopResultSetCountry(con);
        ArrayList<PopulationReport> prArr = PopulationReport.rsToArrLstCountry(con, rs);
        return prArr;
    }

    /**
     * prints worldwide population
     */
    public void worldPop()
    {
        PopulationReport.worldwidePopulation(con);
    }

    /**
     * Prints population of given continent
     * @param continent name of continent
     */
    public void continentPop(String continent)
    {
        PopulationReport.continentPopulation(con, continent);
    }

    /**
     * Prints population of given region
     * @param region name of region
     */
    public void regionPop(String region)
    {
        PopulationReport.regionPopulation(con, region);
    }

    /**
     * Prints population of given country
     * @param country name of country
     */
    public void countryPop(String country)
    {
        PopulationReport.countryPopulation(con, country);
    }

    /**
     * Prints population of given district
     * @param district name of district
     */
    public void districtPop(String district)
    {
        PopulationReport.districtPopulation(con, district);
    }

    /**
     * Prints population of given city
     * @param city name of city
     */
    public void cityPop(String city)
    {
        PopulationReport.cityPopulation(con, city);
    }

}