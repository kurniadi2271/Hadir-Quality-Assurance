package com.juaracoding.kelompok1.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.pages.DashboardPage;
import com.juaracoding.kelompok1.pages.LaporanCuti;
import com.juaracoding.kelompok1.pages.LaporanSakit;
import com.juaracoding.kelompok1.pages.LaporanLembur;
import com.juaracoding.kelompok1.pages.LaporanKoreksi;
import com.juaracoding.kelompok1.pages.DownloadAbsen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static LaporanCuti laporanCuti;
    public static LaporanSakit laporanSakit;
    public static LaporanLembur laporanLembur;
    public static LaporanKoreksi laporanKoreksi;
    public static DownloadAbsen downloadAbsen;

    @Before(order = 1)
    public void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        laporanCuti = new LaporanCuti(driver);
        laporanSakit = new LaporanSakit(driver);
        laporanLembur = new LaporanLembur(driver);
        laporanKoreksi = new LaporanKoreksi(driver);
        downloadAbsen = new DownloadAbsen(driver);
    }


    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverSingleton.getDriver();
        
        // Ambil screenshot untuk SEMUA kondisi (Lulus maupun Gagal)
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        
        // Berikan nama dinamis berdasarkan status skenario
        String status = scenario.isFailed() ? "Gagal" : "Berhasil";
        scenario.attach(screenshot, "image/png", "Evidence_" + status); 

        // Tutup browser
        DriverSingleton.closeObjectInstance();
    }
}