package com.napier.devops;

import com.napier.sem.CountryReport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CountryReportTest
{
    /**
     * Check that a null list is handled without crashing the program
     */
    @Test
    void displayCountriesNullTest()
    {
        CountryReport.displayCountries(null);
    }

    /**
     * Check that an empty list is handled without crashing
     */
    @Test
    void displayCountriesEmptyTest()
    {
        ArrayList<CountryReport> countries = new ArrayList<>();

        CountryReport.displayCountries(countries);

    }
    /**
     * Tests that a null entry in the list is handled without crashing
     */
    @Test
    void displayCountriesNullEntryTest()
    {
        ArrayList<CountryReport> countries = new ArrayList<>();
        countries.add(null);

        CountryReport.displayCountries(countries);

    }

    /**
     * Tests that a null connection doesn't crash getAllCountries
     */
    @Test
    void getAllCountriesNullTest()
    {
        CountryReport.getAllCountries(null);
    }

    /**
     * Tests that a null connection doesn't crash getCountriesByContinent
     */
    @Test
    void getCountriesByContinentNullTest()
    {
        CountryReport.getCountriesByContinent(null, "test");
    }

    /**
     * Tests that a null connection and null string doesn't crash getCountriesByContinent
     */
    @Test
    void getCountriesByContinentNullStrTest()
    {
        CountryReport.getCountriesByContinent(null, null);
    }

    /**
     * Tests that a null connection doesn't crash getCountriesByRegion
     */
    @Test
    void getCountriesByRegionNullTest()
    {
        CountryReport.getCountriesByRegion(null, "test");
    }

    /**
     * Tests that a null connection and null string doesn't crash getCountriesByRegion
     */
    @Test
    void getCountriesByRegionNullStrTest()
    {
        CountryReport.getCountriesByRegion(null, null);
    }

    /**
     * Tests that a null connection doesn't crash getTopCountries
     */
    @Test
    void getTopCountriesNullTest()
    {
        CountryReport.getTopCountries(null, 3);
    }

    /**
     * Tests that a null connection doesn't crash getTopCountriesByContinent
     */
    @Test
    void getTopCountriesByContinentNullTest()
    {
        CountryReport.getTopCountriesByContinent(null, "test", 3);
    }

    /**
     * Tests that a null connection and null string doesn't crash getTopcountriesByContinent
     */
    @Test
    void getTopCountriesByContinentNullStrTest()
    {
        CountryReport.getTopCountriesByContinent(null, null, 3);
    }

    /**
     * Tests that a null connection doesn't crash getTopCountriesByRegion
     */
    @Test
    void getTopCountriesByRegionNullTest()
    {
        CountryReport.getTopCountriesByRegion(null, "test", 3);
    }

    /**
     * Tests that a null connection and null string doesn't crash getTopCountriesByRegion
     */
    @Test
    void getTopCountriesByRegionNullStrTest()
    {
        CountryReport.getTopCountriesByRegion(null, null, 3);
    }


    /**
     * Tests that a country with all its details can be displayed
     */
    @Test
    void displayCountriesTest()
    {
        ArrayList<CountryReport> countries = new ArrayList<>();

        CountryReport country = new CountryReport();
        country.code = "TST";
        country.name = "Test Country";
        country.continent = "Europe";
        country.region = "Test Region";
        country.population = 100000;
        country.capital = "Test Capital";

        countries.add(country);

        CountryReport.displayCountries(countries);
    }
}
