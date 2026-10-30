package com.napier.devops;

import com.napier.sem.CountryReport;
import com.napier.sem.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountryReportIntegrationTest
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

    @AfterAll
    static void finish()
    {
        app.disconnect();
    }
}
