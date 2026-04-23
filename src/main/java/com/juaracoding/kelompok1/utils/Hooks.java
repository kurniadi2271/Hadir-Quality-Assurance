package com.juaracoding.kelompok1.utils;

import org.openqa.selenium.WebDriver;
import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.pages.DashboardPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;

    @Before(order = 1)
    public void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    
    @After
    public void tearDown() {
        DriverSingleton.closeObjectInstance();
    }
}