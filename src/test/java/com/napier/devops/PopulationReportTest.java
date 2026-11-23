package com.napier.devops;

import com.napier.sem.CapitalCity;
import com.napier.sem.PopulationReport;
import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationReportTest
{

    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
    }

    /**
     * tests ToString method returns string
     */
    @Test
    void toStringTest()
    {
        PopulationReport pr = new PopulationReport();
        pr.name = "test";
        pr.population = 100;
        pr.cityPop = 1.1;
        pr.nonCityPop = 2.2;
        assertNotNull(pr.ToString());
    }

    /**
     * test ToString method with null object
     */
    @Test
    void toStringBlankTest()
    {
        PopulationReport pr = new PopulationReport();
        assertNotNull(pr.ToString());
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetContinentNullTest()
    {
        PopulationReport.getPopResultSetContinent(null);
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetContinentCityNullTest()
    {
        PopulationReport.getPopResultSetContinentCity(null, "test");
    }

    /**
     * tests method with null Connection and String parameter
     */
    @Test
    void getPopResultSetContinentCityNullStringTest()
    {
        PopulationReport.getPopResultSetContinentCity(null, null);
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetRegionNullTest()
    {
        PopulationReport.getPopResultSetRegion(null);
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetRegionCityNullTest()
    {
        PopulationReport.getPopResultSetRegionCity(null, "test");
    }

    /**
     * tests method with null Connection and String parameter
     */
    @Test
    void getPopResultSetRegionCityNullStringTest()
    {
        PopulationReport.getPopResultSetRegionCity(null, null);
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetCountryNullTest()
    {
        PopulationReport.getPopResultSetCountry(null);
    }

    /**
     * tests method with null Connection parameter
     */
    @Test
    void getPopResultSetCountryCityNullTest()
    {
        PopulationReport.getPopResultSetCountryCity(null, "test");
    }

    /**
     * tests method with null Connection and String parameter
     */
    @Test
    void getPopResultSetCountryCityNullStringTest()
    {
        PopulationReport.getPopResultSetCountryCity(null, null);
    }

    /**
     * tests display method works with null parameter
     */
    @Test
    void displayPopulationReportNullTest()
    {
        PopulationReport.displayPopulationReport(null);
    }

    /**
     * tests display method with ArrayList with null entry
     */
    @Test
    void displayPopulationReportNullEntryTest()
    {
        ArrayList<PopulationReport> testArr = new ArrayList<PopulationReport>();
        testArr.add(null);
        PopulationReport.displayPopulationReport(testArr);
    }

    /**
     * tests display method with empty ArrayList
     */
    @Test
    void displayPopulationReportEmptyTest()
    {
        ArrayList<PopulationReport> testArr = new ArrayList<PopulationReport>();
        PopulationReport.displayPopulationReport(testArr);
    }

    /**
     * tests display method in normal circumstances
     */
    @Test
    void displayPopulationReportTest()
    {
        ArrayList<PopulationReport> testArr = new ArrayList<PopulationReport>();
        PopulationReport pr = new PopulationReport();
        pr.name = "test";
        pr.population = 100;
        pr.cityPop = 1.1;
        pr.nonCityPop = 2.2;
        testArr.add(pr);
        PopulationReport.displayPopulationReport(testArr);
    }

    @Test
    void worldwidePopulationNullTest()
    {
        PopulationReport.worldwidePopulation(null);
    }

    @Test
    void continentPopulationNullTest()
    {
        PopulationReport.continentPopulation(null, "test");
    }

    @Test
    void continentPopulationNullStringTest()
    {
        PopulationReport.continentPopulation(null, null);
    }

    @Test
    void regionPopulationNullTest()
    {
        PopulationReport.regionPopulation(null, "test");
    }

    @Test
    void regionPopulationNullStringTest()
    {
        PopulationReport.continentPopulation(null, null);
    }

    @Test
    void countryPopulationNullTest()
    {
        PopulationReport.countryPopulation(null, "test");
    }

    @Test
    void countryPopulationNullStringTest()
    {
        PopulationReport.countryPopulation(null, null);
    }

    @Test
    void districtPopulationNullTest()
    {
        PopulationReport.districtPopulation(null, "test");
    }

    @Test
    void districtPopulationNullStringTest()
    {
        PopulationReport.districtPopulation(null, null);
    }

    @Test
    void cityPopulationNullTest()
    {
        PopulationReport.cityPopulation(null, "test");
    }

    @Test
    void cityPopulationNullStringTest()
    {
        PopulationReport.cityPopulation(null, null);
    }

    @Test
    void rsToArrLstContinentNullTest()
    {
        PopulationReport.rsToArrLstContinent(null, null);
    }

    @Test
    void rsToArrLstRegionNullTest()
    {
        PopulationReport.rsToArrLstRegion(null, null);
    }

    @Test
    void rsToArrLstCountryNullTest()
    {
        PopulationReport.rsToArrLstCountry(null, null);
    }

}
