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
     * Checks that a null entry in the list is handled without crashing
     */
    @Test
    void displayCountriesNullEntryTest()
    {
        ArrayList<CountryReport> countries = new ArrayList<>();
        countries.add(null);

        CountryReport.displayCountries(countries);

    }

    /**
     * Checks that a country with all its details can be displayed
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
