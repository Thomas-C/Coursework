package com.napier.devops;

import com.napier.sem.CityReport;
import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CityReportTest {
    static Main app;

    @BeforeAll
    static void init() {
        app = new Main();
    }

    /**
     * test that string is properly returned by ToString method
     */
    @Test
    void toStringTest()
    {
        CityReport cr = new CityReport();
        cr.id = 123;
        cr.name = "test1";
        cr.country = "test2";
        cr.district = "test3";
        cr.population = 100000;
        assertNotNull(cr.ToString());
    }

    /**
     * test that ToString doesn't crash on null object
     */
    @Test
    void toStringBlankTest()
    {
        CityReport cr = new CityReport();
        assertNotNull(cr.ToString());
    }

    /**
     * test that null connection doesn't crash getFullResultSet method
     */
    @Test
    void fullResultSetNullTest()
    {
        CityReport.getFullResultSet(null);
    }

    /**
     * test that null connection doesn't crash getContinentResultSet method
     */
    @Test
    void getContinentResultSetTest()
    {
        CityReport.getContinentResultSet(null, "test");
    }

    /**
     * test that null connection and string doesn't crash getContinentResultSet method
     */
    @Test
    void getContinentResultSetNullTest()
    {
        CityReport.getContinentResultSet(null, null);
    }

    /**
     * test that null connection doesn't crash getRegionResultSet method
     */
    @Test
    void getRegionResultSetTest()
    {
        CityReport.getRegionResultSet(null, "test");
    }

    /**
     * test that null connection and string doesn't crash getRegionResultSet method
     */
    @Test
    void getRegionResultSetNullTest()
    {
        CityReport.getRegionResultSet(null, null);
    }

    /**
     * test that null connection doesn't crash getCountryResultSet method
     */
    @Test
    void getCountryResultSetTest()
    {
        CityReport.getCountryResultSet(null, "test");
    }

    /**
     * test that null connection and string doesn't crash getCountryResultSet method
     */
    @Test
    void getCountryResultSetNullTest()
    {
        CityReport.getCountryResultSet(null, null);
    }

    /**
     * test that null connection doesn't crash getDistrictResultSet method
     */
    @Test
    void getDistrictResultSetTest()
    {
        CityReport.getDistrictResultSet(null, "test");
    }

    /**
     * test that null connection and string doesn't crash getDistrictResultSet method
     */
    @Test
    void getDistrictResultSetNullTest()
    {
        CityReport.getDistrictResultSet(null, null);
    }


    // -----------------------------------

    /**
     * test that null ArrayList doesn't crash displayCities method
     */
    @Test
    void displayCitiesNullTest()
    {
        CityReport.displayCities(null);
    }

    /**
     * test that empty ArrayList doesn't crash displayCities method
     */
    @Test
    void displayCitiesEmptyTest()
    {
        ArrayList<CityReport> testArr = new ArrayList<CityReport>();
        CityReport.displayCities(testArr);
    }

    /**
     * test that null entry in ArrayList doesn't crash displayCities method
     */
    @Test
    void displayCitiesNullEntryTest()
    {
        ArrayList<CityReport> testArr = new ArrayList<CityReport>();
        testArr.add(null);
        CityReport.displayCities(testArr);
    }

    /**
     * test that displayCities method works under normal circumstances
     */
    @Test
    void displayCitiesTest()
    {
        ArrayList<CityReport> testArr = new ArrayList<CityReport>();
        CityReport cr = new CityReport();
        cr.id = 123;
        cr.name = "test1";
        cr.country = "test2";
        cr.district = "test3";
        cr.population = 100;
        assertNotNull(cr.ToString());
        testArr.add(cr);
        CityReport.displayCities(testArr);
    }

    @Test
    void displayTopCitiesNullCount()
    {
        ArrayList<CityReport> testArr = new ArrayList<CityReport>();
        CityReport cr = new CityReport();
        cr.id = 123;
        cr.name = "test1";
        cr.country = "test2";
        cr.district = "test3";
        cr.population = 100;
        assertNotNull(cr.ToString());
        testArr.add(cr);
        CityReport.displayTopCities(testArr, null);
    }

    @Test
    void displayTopCitiesNullArr()
    {
        CityReport.displayTopCities(null, 5);
    }

    @Test
    void displayTopCities()
    {
        ArrayList<CityReport> testArr = new ArrayList<CityReport>();
        CityReport cr = new CityReport();
        cr.id = 123;
        cr.name = "test1";
        cr.country = "test2";
        cr.district = "test3";
        cr.population = 100;
        assertNotNull(cr.ToString());
        testArr.add(cr);
        CityReport.displayTopCities(testArr, 5);
    }

}