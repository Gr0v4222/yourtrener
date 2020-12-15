package com.projectKPO.yourtrener;


import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {


    @Test
    public void isCorrectToSave_NULL() {
        String name="";
        String age="";
        String height="";
        String weight="";
        Settings testsettings = new Settings();
        int a=testsettings.isCorrectToSave(name,age,height,weight);
        assertEquals(0, a );
    }
    @Test
    public void isCorrectToSave_OnlyName() {
        String name="Name";
        String age="";
        String height="";
        String weight="";
        Settings testsettings = new Settings();
        int a=testsettings.isCorrectToSave(name,age,height,weight);
        assertEquals(0, a );
    }
    @Test
    public void isCorrectToSave_FULL() {
        String name="Name";
        String age="17";
        String height="140";
        String weight="50";
        Settings testsettings = new Settings();
        int a=testsettings.isCorrectToSave(name,age,height,weight);
        assertEquals(0, a );
    }
    @Test
    public void isCorrectToSave_FULL_INCORRECT_AGE() {
        String name="Name";
        String age="-4";
        String height="150";
        String weight="45";
        Settings testsettings = new Settings();
        int a=testsettings.isCorrectToSave(name,age,height,weight);
        assertEquals(0, a );
    }
}