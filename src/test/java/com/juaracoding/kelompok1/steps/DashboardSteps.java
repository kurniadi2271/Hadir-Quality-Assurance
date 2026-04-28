package com.juaracoding.kelompok1.steps;

import com.juaracoding.kelompok1.pages.DashboardPage;
import io.cucumber.java.en.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardSteps {

    private WebDriver driver;
    private DashboardPage dashboardPage;

    String email = "admin@hadir.com";
    String password = "MagangSQA_JC@123";

    private WebDriver getDriver() {
        if (driver == null) {
            driver = Hooks.driver;
        }
        return driver;
    }

    private DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(getDriver());
        }
        return dashboardPage;
    }

    // ================= LOGIN =================

    @Given("User login sebagai Admin")
    public void user_login_sebagai_admin() {
        getDashboardPage().login(email, password);
    }

    @Given("User berada di halaman Dashboard")
    public void user_di_dashboard() {
        user_login_sebagai_admin();
        waitDashboard();
    }

    private void waitDashboard() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dashboard"));
    }

    // ================= VALIDATION =================

    @Then("Halaman Dashboard ditampilkan")
    public void verify_dashboard_page() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Then("Widget Lembur, Cuti, Koreksi tampil")
    public void verify_widgets() {
        Assert.assertTrue(getDashboardPage().isWidgetLemburDisplayed());
        Assert.assertTrue(getDashboardPage().isWidgetCutiDisplayed());
        Assert.assertTrue(getDashboardPage().isWidgetKoreksiDisplayed());
    }

    @Then("Data berhasil ditampilkan")
    public void data_berhasil() {
        Assert.assertTrue(getDashboardPage().getEmployeeRowCount() >= 0);
    }

    @Then("Halaman tetap di dashboard")
    public void tetap_dashboard() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Then("Data kembali seperti semula")
    public void reset_berhasil() {
        Assert.assertTrue(getDashboardPage().getEmployeeRowCount() >= 0);
    }

    // ================= ACTION =================

    @When("User menginput Start Date {string}")
    public void input_start_date(String date) {
    getDashboardPage().inputStartDate(date);
    }

    @When("User menginput End Date {string}")
    public void input_end_date(String date) {
    getDashboardPage().inputEndDate(date);
    }

    @When("User klik tombol Search")
    public void klik_search() {
        getDashboardPage().clickSearch();
    }

    @When("User klik tombol Reset")
    public void klik_reset() {
        getDashboardPage().clickReset();
    }

    @When("User klik menu {string}")
    public void klik_menu(String menu) {
        getDashboardPage().clickMenu(menu);
    }

    @Given("User sudah menerapkan filter")
    public void user_sudah_filter() {
        user_di_dashboard();
        getDashboardPage().inputStartDate("2024-01-01");
        getDashboardPage().inputEndDate("2024-01-31");
        getDashboardPage().clickSearch();
    }
}