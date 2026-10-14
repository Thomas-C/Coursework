package com.napier.sem;

// A class which represents a capital city
public class CapitalCity
{

    public int id;
    public String name;
    public String country;
    public int population;

    public String ToString()
    {
        return ("Name: " + this.name + "  |  Country: " + this.country + "  |  Population: " + Integer.toString(this.population));
    }

}