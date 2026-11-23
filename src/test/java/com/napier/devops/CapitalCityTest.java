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
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
    }

    /**
     * test that string is properly returned by ToString method
     */
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

    /**
     * test that ToString doesn't crash on null object
     */
    @Test
    void toStringBlankTest()
    {
        CapitalCity cc = new CapitalCity();
        assertNotNull(cc.ToString());
    }


    /**
     * test that null connection doesn't crash getFullResultSet method
     */
    @Test
    void fullResultSetNullTest()
    {
        CapitalCity.getFullResultSet(null);
    }

    /**
     * test that null connection doesn't crash getContinentResultSet method
     */
    @Test
    void continentResultSetNullTest()
    {
        CapitalCity.getContinentResultSet(null, "test");
    }

    /**
     * test that null connection and null String doesn't crash getContinentResultSet method
     */
    @Test
    void continentResultSetNullStrTest()
    {
        CapitalCity.getContinentResultSet(null, null);
    }

    /**
     * test that null connection doesn't crash getRegionResultSet method
     */
    @Test
    void regionResultSetNullTest()
    {
        CapitalCity.getRegionResultSet(null, "test");
    }

    /**
     * test that null connection and null String doesn't crash getRegionResultSet method
     */
    @Test
    void regionResultSetNullStrTest()
    {
        CapitalCity.getRegionResultSet(null, null);
    }

    /**
     * test that null ArrayList doesn't crash displayCapitals method
     */
    @Test
    void displayCapitalsNullTest()
    {
        CapitalCity.displayCapitals(null);
    }

    /**
     * test that empty ArrayList doesn't crash displayCapitals method
     */
    @Test
    void displayCapitalsEmptyTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        CapitalCity.displayCapitals(testArr);
    }

    /**
     * test that null entry in ArrayList doesn't crash displayCapitals method
     */
    @Test
    void displayCapitalsNullEntryTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        testArr.add(null);
        CapitalCity.displayCapitals(testArr);
    }

    /**
     * test that displayCapitals method works under normal circumstances
     */
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

    @Test
    void rsToArrLstNullTest()
    {
        CapitalCity.rsToArrLst(null);
    }

    @Test
    void getTopNTestNull()
    {
        ArrayList<CapitalCity> testArr = CapitalCity.getTopN(null, null);
    }

    @Test
    void getTopNTestNullArrayList()
    {
        ArrayList<CapitalCity> testArr = CapitalCity.getTopN(null, 1);
    }

    @Test
    void getTopNTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        CapitalCity cc1 = new CapitalCity();
        cc1.id = 123;
        cc1.name = "test1";
        cc1.country = "test2";
        cc1.population = 100;
        CapitalCity cc2 = new CapitalCity();
        cc2.id = 123;
        cc2.name = "test1";
        cc2.country = "test2";
        cc2.population = 100;
        testArr.add(cc1);
        testArr.add(cc2);
        ArrayList<CapitalCity> shortArr = CapitalCity.getTopN(testArr, 1);
    }

    @Test
    void getTopNTestSmallArrayListTest()
    {
        ArrayList<CapitalCity> testArr = new ArrayList<CapitalCity>();
        CapitalCity cc1 = new CapitalCity();
        cc1.id = 123;
        cc1.name = "test1";
        cc1.country = "test2";
        cc1.population = 100;
        testArr.add(cc1);
        ArrayList<CapitalCity> shortArr = CapitalCity.getTopN(testArr, 0);
    }

}