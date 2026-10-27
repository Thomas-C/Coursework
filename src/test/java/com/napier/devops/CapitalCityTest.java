package com.napier.devops;

import com.napier.sem.CapitalCity;
import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityTest
{
    static Main a;

    @BeforeAll
    static void init()
    {
        a = new Main();
    }

    @Test
    void toStringTest()
    {
        CapitalCity cc = new CapitalCity();
        cc.id = 123;
        cc.name = "test1";
        cc.country = "test2";
        cc.population = 100000;
        assertNotNull(cc.ToString());
    }

    @Test
    void toStringBlankTest()
    {
        CapitalCity cc = new CapitalCity();
        assertNotNull(cc.ToString());
    }



    // needs more testing of connection stuff
    @Test
    void fullResultSetNullTest()
    {
        CapitalCity.getFullResultSet(null);
    }

    @Test
    void displayCapitalsNullTest()
    {
        CapitalCity.displayCapitals(null);
    }

    @Test
    void displayCapitalsEmptyTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        CapitalCity.displayCapitals(testArr);
    }

    @Test
    void displayCapitalsNullEntryTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        testArr.add(null);
        CapitalCity.displayCapitals(testArr);
    }

    @Test
    void displayCapitalsTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        CapitalCity cc = new CapitalCity();
        cc.id = 123;
        cc.name = "test1";
        cc.country = "test2";
        cc.population = 100;
        assertNotNull(cc.ToString());
        testArr.add(cc);
        CapitalCity.displayCapitals(testArr);
    }
}