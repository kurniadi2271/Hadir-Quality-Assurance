package com.juaracoding.kelompok1.utils;

import org.openqa.selenium.WebDriver;
import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.pages.DashboardPage;
import com.juaracoding.kelompok1.pages.LaporanCuti;
import com.juaracoding.kelompok1.pages.LaporanSakit;
import com.juaracoding.kelompok1.pages.DownloadAbsen;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static LaporanCuti laporanCuti;
    public static LaporanSakit laporanSakit;
    public static DownloadAbsen downloadAbsen;

    @Before(order = 1)
    public void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        laporanCuti = new LaporanCuti(driver);
        laporanSakit = new LaporanSakit(driver);
        downloadAbsen = new DownloadAbsen(driver);
    }


    @After
    public void tearDown() {
        DriverSingleton.closeObjectInstance();
    }
}