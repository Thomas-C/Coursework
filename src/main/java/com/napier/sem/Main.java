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

        try
        {
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
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }


        // Displays only the three most populated countries in a given Region
        //ArrayList<CountryReport> topCountries = a.topCountriesReportRegion("Central Africa", 3);
        //CountryReport.displayCountries(topCountries);

        // Demonstration report of all capital cities in a given Continent
        //ArrayList<CapitalCity> capitals = a.capitalsReportContinent("Europe");
        //CapitalCity.displayCapitals(capitals);

        // Displays only the top three most populated capital cities in a given Region
        //ArrayList<CapitalCity> topCapitals = a.topCapitalsReportRegion("Central Africa", 3);
        //CapitalCity.displayCapitals(topCapitals);

        // Demonstration report of all cities in a given Country
        //ArrayList<CityReport> cities = a.cityReportsCountry("Malaysia");
        //CityReport.displayCities(cities);

        // Displays a Report on the populations of countries and the percentage that live in cities
        //ArrayList<PopulationReport> popReport = a.populationReportCountry();
        //PopulationReport.displayPopulationReport(popReport);

        // Displays the population of a given district
        //a.districtPop("Nagano");

        // Displays Report on common languages
        //ArrayList<LanguagesReport> languages = a.languagesReport();
        //LanguagesReport.DisplayLanguages(languages);

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

    public ArrayList<CountryReport> countriesReportRegion(String region)
    {
        return CountryReport.getCountriesByRegion(con, region);
    }

    public ArrayList<CountryReport> countriesReportContinent(String continent)
    {
        return CountryReport.getCountriesByContinent(con, continent);
    }

    public ArrayList<CountryReport> topCountriesReportRegion(String region, int n)
    {
        return CountryReport.getTopCountriesByRegion(con, region, n);
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
     * Returns the top n most populated capital cities worldwide
     */
    public ArrayList<CapitalCity> topCapitalsReport(int n)
    {
        return CapitalCity.getTopN(capitalsReport(), n);
    }

    /**
     * Returns the top n most populated capital cities in a continent
     */
    public ArrayList<CapitalCity> topCapitalsReportContinent(String continent, int n)
    {
        return CapitalCity.getTopN(capitalsReportContinent(continent), n);
    }

    /**
     * Returns the top n most populated capital cities in a region
     */
    public ArrayList<CapitalCity> topCapitalsReportRegion(String region, int n)
    {
        return CapitalCity.getTopN(capitalsReportRegion(region), n);
    }
     /** Returns ArrayList of CityReport objects using worldwide data
     * @return ArrayList of CityReport objects
     */
    public ArrayList<CityReport> cityReports()
    {
        // Create and execute SQL statement
        ResultSet rs = CityReport.getFullResultSet(con);

        // Populate ArrayList with results of SQL statement
        ArrayList<CityReport> crArr = CityReport.rsToArrLst(rs);
        return crArr;
    }

    /**
     * Returns ArrayList of CityReport objects using data from one continent
     * @param continent Name of continent to get data from
     * @return ArrayList of CityReport objects
     */
    public ArrayList<CityReport> cityReportsContinent(String continent)
    {
        // Create and execute SQL statement
        ResultSet rs = CityReport.getContinentResultSet(con, continent);

        // Populate ArrayList with results of SQL statement
        ArrayList<CityReport> crArr = CityReport.rsToArrLst(rs);
        return crArr;
    }

    /**
     * Returns ArrayList of CityReport objects using data from one region
     * @param region Name of region to get data from
     * @return ArrayList of CityReport objects
     */
    public ArrayList<CityReport> cityReportsRegion(String region)
    {
        // Create and execute SQL statement
        ResultSet rs = CityReport.getRegionResultSet(con, region);

        // Populate ArrayList with results of SQL statement
        ArrayList<CityReport> crArr = CityReport.rsToArrLst(rs);
        return crArr;
    }

    /**
     * Returns ArrayList of CityReport objects using data from one country
     * @param country Name of country to get data from
     * @return ArrayList of CityReport objects
     */
    public ArrayList<CityReport> cityReportsCountry(String country)
    {
        // Create and execute SQL statement
        ResultSet rs = CityReport.getCountryResultSet(con, country);

        // Populate ArrayList with results of SQL statement
        ArrayList<CityReport> crArr = CityReport.rsToArrLst(rs);
        return crArr;
    }

    /**
     * Returns ArrayList of CityReport objects using data from one district
     * @param district Name of district to get data from
     * @return ArrayList of CityReport objects
     */
    public ArrayList<CityReport> cityReportsDistrict(String district)
    {
        // Create and execute SQL statement
        ResultSet rs = CityReport.getDistrictResultSet(con, district);

        // Populate ArrayList with results of SQL statement
        ArrayList<CityReport> crArr = CityReport.rsToArrLst(rs);
        return crArr;
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

    /**
     * Returns ArrayList of language data
     * @return ArrayList of language data
     */
    public ArrayList<LanguagesReport> languagesReport()
    {
        return LanguagesReport.getLanguagesBySpeaker(con);
    }

}