package com.juaracoding.kelompok1.drivers;

import org.openqa.selenium.WebDriver;
import com.juaracoding.kelompok1.drivers.strategies.DriverStrategy;
import com.juaracoding.kelompok1.drivers.strategies.DriverStrategyImplementer;

public class DriverSingleton {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void getInstance(String browser) {
        if (driver == null) {
            DriverStrategy strategy = DriverStrategyImplementer.chooseStrategy(browser);
            if (strategy != null) {
                driver = strategy.setStrategy(browser);
                driver.manage().window().maximize();
            }
        }
    }

    public static void closeObjectInstance() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void quitDriver() {
        closeObjectInstance();
    }
}