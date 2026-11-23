package com.napier.devops;

import com.napier.sem.LanguagesReport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LanguagesReportTest
{

    /* Null Check (checking if program can handle nulls)*/
    @Test
    void getLanguagesBySpeakersNullTest()
    {
        assertNull(LanguagesReport.getLanguagesBySpeaker(null));
    }

    /* Null Check */
    @Test
    void displayLanguagesNullTest()
    {
        LanguagesReport.DisplayLanguages(null);
    }

    /* Empty Check  (Checks to see if program can handle empty array)*/
    @Test
    void displayLanguagesEmptyTest()
    {
        ArrayList<LanguagesReport> languages = new ArrayList<>();

        LanguagesReport.DisplayLanguages(languages);
    }

    /* Null check */
    @Test
    void displayLanguagesNullEntryTest()
    {
        ArrayList<LanguagesReport> languages = new ArrayList<>();
        languages.add(null);

        LanguagesReport.DisplayLanguages(languages);
    }

    /* Test Display*/
    @Test
    void displayLanguagesTest()
    {
        ArrayList<LanguagesReport> languages = new ArrayList<>();

        LanguagesReport language = new LanguagesReport();
        language.language = "Test Language";
        language.speakers = 100000;
        language.worldPercentage = 1.5;

        languages.add(language);

        LanguagesReport.DisplayLanguages(languages);
    }
}
