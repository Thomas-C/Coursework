package com.napier.devops;

import com.napier.sem.CapitalCity;
import com.napier.sem.CityReport;
import com.napier.sem.CountryReport;
import com.napier.sem.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        // China should be first country that displays in the database
        CountryReport country = countries.get(0);

        assertEquals("CHN", country.code);
        assertEquals("China", country.name);
        assertEquals("Asia", country.continent);
        assertEquals("Eastern Asia", country.region);
        assertEquals(1277558000L, country.population);
        assertEquals("Peking", country.capital);
    }

    /**
     * Tests country report for continent
     */
    @Test
    void testGetCountriesContinent()
    {
        ArrayList<CountryReport> countries = app.countriesReportContinent("Europe");

        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        // Russia should be first for Europe in the database
        CountryReport country = countries.get(0);

        assertEquals("RUS", country.code);
        assertEquals("Russian Federation", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("Eastern Europe", country.region);
        assertEquals(146934000L, country.population);
        assertEquals("Moscow", country.capital);
    }

    @Test
    void testGetCountriesRegion()
    {
        ArrayList<CountryReport> countries= app.countriesReportRegion("Western Europe");

        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        // Germany should be first in Western Europe in database
        CountryReport country = countries.get(0);

        assertEquals("DEU", country.code);
        assertEquals("Germany", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("Western Europe", country.region);
        assertEquals(82164700L, country.population);
        assertEquals("Berlin", country.capital);
    }

    /**
     * Tests top populated N countries by region
     */
    @Test
    void testTopCountriesByRegion()
    {
        ArrayList<CountryReport> countries = app.topCountriesReportRegion("Central Africa", 3);

        assertNotNull(countries);
        assertEquals(3, countries.size());

        assertEquals("COD", countries.get(0).code);
        assertEquals("CMR", countries.get(1).code);
        assertEquals("AGO", countries.get(2).code);

        for (CountryReport country : countries)
        {
            assertEquals("Central Africa", country.region);
        }
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

    @Test
    void testGetCitiesWorldwide()
    {
        // Get city data
        ArrayList<CityReport> cities = app.cityReports();

        // Check that the query returned data
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        // Check data is as expected
        CityReport city = cities.get(0);
        assertEquals(1024, city.id);
        assertEquals("Mumbai (Bombay)", city.name);
        assertEquals("India", city.country);
        assertEquals("Maharashtra", city.district);
        assertEquals(10500000, city.population);
    }

    @Test
    void testGetCitiesContinent()
    {
        // Get city data
        ArrayList<CityReport> cities = app.cityReportsContinent("Europe");

        // Check that the query returned data
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        // Check data is as expected
        CityReport city = cities.get(0);
        assertEquals(3580, city.id);
        assertEquals("Moscow", city.name);
        assertEquals("Russian Federation", city.country);
        assertEquals("Moscow (City)", city.district);
        assertEquals(8389200, city.population);
    }

    @Test
    void testGetCitiesRegion()
    {
        // Get city data
        ArrayList<CityReport> cities = app.cityReportsRegion("Caribbean");

        // Check that the query returned data
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        // Check data is as expected
        CityReport city = cities.get(0);
        assertEquals(2413, city.id);
        assertEquals("La Habana", city.name);
        assertEquals("Cuba", city.country);
        assertEquals("La Habana", city.district);
        assertEquals(2256000, city.population);
    }

    @Test
    void testGetCitiesCountry()
    {
        // Get city data
        ArrayList<CityReport> cities = app.cityReportsCountry("Malaysia");

        // Check that the query returned data
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        // Check data is as expected
        CityReport city = cities.get(0);
        assertEquals(2464, city.id);
        assertEquals("Kuala Lumpur", city.name);
        assertEquals("Malaysia", city.country);
        assertEquals("Wilayah Persekutuan", city.district);
        assertEquals(1297526, city.population);
    }

    @Test
    void testGetCitiesDistrict()
    {
        // Get city data
        ArrayList<CityReport> cities = app.cityReportsDistrict("Buenos Aires");

        // Check that the query returned data
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        // Check data is as expected
        CityReport city = cities.get(0);
        assertEquals(70, city.id);
        assertEquals("La Matanza", city.name);
        assertEquals("Argentina", city.country);
        assertEquals("Buenos Aires", city.district);
        assertEquals(1266461, city.population);
    }

    @AfterAll
    static void finish()
    {
        app.disconnect();
    }
}
