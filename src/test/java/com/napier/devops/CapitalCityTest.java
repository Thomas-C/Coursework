package com.napier.devops;

import com.napier.sem.CapitalCity;
import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
    void ToStringTest()
    {
        CapitalCity cc = new CapitalCity();
        cc.name = "test1";
        cc.country = "test2";
        cc.population = 100;
        assertNotNull(cc.ToString());
    }
}