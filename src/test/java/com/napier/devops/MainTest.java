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


}
