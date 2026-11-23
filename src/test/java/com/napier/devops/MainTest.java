package com.napier.devops;

import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
        Connection con = null;
    }

    @Test
    void mainTestArgs()
    {
        String[] args = {"localhost:33060", "30000"};
        Main.main(args);
    }

    @Test
    void mainTestNoArgs()
    {
        String[] args = {};
        Main.main(args);
    }

    @Test
    void mainTestNullArgs()
    {
        String[] args = {null, null};
        Main.main(args);
    }

}
