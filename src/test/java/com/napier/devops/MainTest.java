package com.napier.devops;

import com.napier.sem.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest
{
    static Main a;

    @BeforeAll
    static void init()
    {
        a = new Main();
        Connection con = null;
    }


}
