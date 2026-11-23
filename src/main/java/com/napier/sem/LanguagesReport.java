package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguagesReport
{
    public String language;
    public long speakers;
    public double worldPercentage;

    public static ArrayList<LanguagesReport> getLanguagesBySpeaker(Connection con)
    {
       try
       {
           /* SQL Statement */
           Statement stmt = con.createStatement();

           String strSelect =
                   "SELECT countrylanguage.Language, " +
                   "ROUND(SUM(country.Population * countrylanguage.Percentage / 100)) AS Speakers, " +
                   "ROUND(SUM(country.Population * countrylanguage.Percentage / 100) / " +
                   "(SELECT SUM(Population) FROM country) * 100, 2) AS WorldPercentage " +
                   "FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code " +
                   "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                   "GROUP BY countrylanguage.Language " +
                   "ORDER BY Speakers DESC";

           try (stmt)
           {
               /* Run Statement*/
               try (ResultSet rset = stmt.executeQuery(strSelect))
               {
                   ArrayList<LanguagesReport> languages = new ArrayList<>();

                   while (rset.next())
                   {
                       LanguagesReport lang = new LanguagesReport();

                       lang.language = rset.getString("Language");
                       lang.speakers = rset.getLong("Speakers");
                       lang.worldPercentage = rset.getDouble("WorldPercentage");

                       languages.add(lang);
                   }

                   return languages;
               }
           }
       }
       /* Catches Error */
       catch (Exception e)
       {
           System.out.println(e.getMessage());
           System.out.println("Failed to get language details");
           return null;
       }
    }

    /* Display */
    public static void DisplayLanguages(ArrayList<LanguagesReport>languages)
    {
        if (languages == null)
        {
            System.out.println("Cannot display report as the language data was not retrieved");
            return;
        }
        System.out.println("Languages Report: Most Speakers to Fewest");

        for (LanguagesReport lang : languages)
        {
            if (lang == null)
            {
                continue;
            }

            System.out.println(
                    "Language: " + lang.language +
                    " | Speakers: " + lang.speakers +
                    " | World Population: " + String.format("%.2f", lang.worldPercentage) + "%");
        }
    }
}
