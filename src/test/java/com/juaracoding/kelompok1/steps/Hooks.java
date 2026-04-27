package com.juaracoding.kelompok1.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.juaracoding.kelompok1.drivers.DriverSingleton;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        DriverSingleton.quitDriver();
        driver = null;
    }
}
