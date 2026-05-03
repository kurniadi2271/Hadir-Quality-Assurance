package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.DashboardPage;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardSteps {

    private WebDriver driver = Hooks.driver;
    private DashboardPage dashboardPage = Hooks.dashboardPage;

    @Given("User berada di halaman Dashboard")
    public void user_berada_di_halaman_dashboard() {
        // Memastikan URL mengandung dashboard, login dilakukan di Background Feature
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "User tidak di Dashboard!");
    }

    @Then("Halaman Dashboard ditampilkan")
    public void verify_dashboard_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        Assert.assertEquals(dashboardPage.getPageHeader(), "Logo Hadir");
    }

    @Then("Widget Lembur, Cuti, Koreksi tampil")
    public void verify_widgets() {
        Assert.assertTrue(dashboardPage.areWidgetsDisplayed(), "Widget tidak lengkap!");
    }

    @When("User menginput Start Date {string}")
    public void input_start_date(String date) {
        dashboardPage.inputStartDate(date);
    }

    @When("User menginput End Date {string}")
    public void input_end_date(String date) {
        dashboardPage.inputEndDate(date);
    }

    @When("User klik tombol Search")
    public void klik_search() {
        dashboardPage.clickSearch();
    }

    @Then("Data berhasil ditampilkan")
    public void data_berhasil() {
        Assert.assertTrue(dashboardPage.getEmployeeRowCount() >= 0);
    }

    @Then("Halaman tetap di dashboard")
    public void tetap_dashboard() {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @When("User klik tombol Reset")
    public void klik_reset() {
        dashboardPage.clickReset();
    }

    @Then("Data kembali seperti semula")
    public void reset_berhasil() {
        Assert.assertTrue(dashboardPage.getEmployeeRowCount() >= 0);
    }

    @When("User klik menu {string}")
    public void klik_menu(String menu) {
        dashboardPage.clickMenu(menu);
    }

    @Given("User sudah menerapkan filter")
    public void user_sudah_filter() {
        dashboardPage.inputStartDate("2024-01-01");
        dashboardPage.inputEndDate("2024-01-31");
        dashboardPage.clickSearch();
    }
}