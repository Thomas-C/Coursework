package com.napier.devops;

import com.napier.sem.CapitalCity;
import com.napier.sem.CountryReport;
import com.napier.sem.Main;
import com.napier.sem.PopulationReport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReportIntegrationTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
        app.connect("localhost:33060", 30000);
    }

    /**
     * tests full country report
     */
    @Test
    void testGetCountries()
    {
        ArrayList<CountryReport> countries = app.countriesReport();

        // Check that the query returned data
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        // China should be first country that displays in the supplied database
        CountryReport country = countries.get(0);

        assertEquals("CHN", country.code);
        assertEquals("China", country.name);
        assertEquals("Asia", country.continent);
        assertEquals("Eastern Asia", country.region);
        assertEquals(1277558000L, country.population);
        assertEquals("Peking", country.capital);
    }

    /**
     * tests full capital city report
     */
    @Test
    void testGetCapitalCities()
    {
        // Get CapitalCity data
        ArrayList<CapitalCity> capitals = app.capitalsReport();

        // Check that the query returned data
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());

        // Peking, China should be the first capital city in data
        CapitalCity capital = capitals.get(0);
        assertEquals(2331, capital.id);
        assertEquals("Seoul", capital.name);
        assertEquals("South Korea", capital.country);
        assertEquals(9981619, capital.population);
    }

    /**
     * tests capital city by continent report
     */
    @Test
    void testGetCapitalCitiesContinent()
    {
        // Get CapitalCity data
        ArrayList<CapitalCity> capitals = app.capitalsReportContinent("Europe");

        // Check that the query returned data
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());

        // Peking, China should be the first capital city in data
        CapitalCity capital = capitals.get(0);
        assertEquals(3580, capital.id);
        assertEquals("Moscow", capital.name);
        assertEquals("Russian Federation", capital.country);
        assertEquals(8389200, capital.population);
    }

    /**
     * tests capital city by region report
     */
    @Test
    void testGetCapitalCitiesRegion()
    {
        // Get CapitalCity data
        ArrayList<CapitalCity> capitals = app.capitalsReportRegion("Central Africa");

        // Check that the query returned data
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());

        // Kinshasa, DRC should be the first capital city in data
        CapitalCity capital = capitals.get(0);
        assertEquals(2298, capital.id);
        assertEquals("Kinshasa", capital.name);
        assertEquals("Congo, The Democratic Republic of the", capital.country);
        assertEquals(5064000, capital.population);
    }

    /**
     * tests population by continent report
     */
    @Test
    void populationReportContinentTest()
    {
        ArrayList<PopulationReport> pop = app.populationReportContinent();
        assertNotNull(pop);
        assertFalse(pop.isEmpty());
    }

    /**
     * tests population by region report
     */
    @Test
    void populationReportRegionTest()
    {
        ArrayList<PopulationReport> pop = app.populationReportRegion();
        assertNotNull(pop);
        assertFalse(pop.isEmpty());
    }

    /**
     * tests population by country report
     */
    @Test
    void populationReportCountryTest()
    {
        ArrayList<PopulationReport> pop = app.populationReportCountry();
        assertNotNull(pop);
        assertFalse(pop.isEmpty());
    }

    /**
     * tests world population report
     */
    @Test
    void worldPopTest()
    {
        app.worldPop();
    }

    /**
     * tests continent population report
     */
    @Test
    void continentPopTest()
    {
        app.continentPop("Europe");
    }

    /**
     * tests continent population report with null given
     */
    @Test
    void continentPopNullTest()
    {
        app.continentPop(null);
    }

    /**
     * tests region population report
     */
    @Test
    void regionPopTest()
    {
        app.regionPop("Southern Europe");
    }

    /**
     * tests region population report with null given
     */
    @Test
    void regionPopNullTest()
    {
        app.regionPop(null);
    }

    /**
     * tests country population report
     */
    @Test
    void countryPopTest()
    {
        app.countryPop("Italy");
    }

    /**
     * tests country population report with null given
     */
    @Test
    void countryPopNullTest()
    {
        app.countryPop(null);
    }

    /**
     * tests district population report
     */
    @Test
    void districtPopTest()
    {
        app.districtPop("Nagano");
    }

    /**
     * tests district population report with null given
     */
    @Test
    void districtPopNullTest()
    {
        app.districtPop(null);
    }

    /**
     * tests city population report
     */
    @Test
    void cityPopTest()
    {
        app.cityPop("Edinburgh");
    }

    /**
     * tests city population report with null given
     */
    @Test
    void cityPopNullTest()
    {
        app.cityPop(null);
    }

    @AfterAll
    static void finish()
    {
        app.disconnect();
    }
}
