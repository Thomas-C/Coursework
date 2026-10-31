package com.napier.devops;

import com.napier.sem.CapitalCity;
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
        assertEquals(1891, capital.id);
        assertEquals("Peking", capital.name);
        assertEquals("China", capital.country);
        assertEquals(7472000, capital.population);
    }

    @AfterAll
    static void finish()
    {
        app.disconnect();
    }
}
