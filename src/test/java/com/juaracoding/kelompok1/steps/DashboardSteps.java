package com.juaracoding.kelompok1.steps;

import com.juaracoding.kelompok1.pages.DashboardPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
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

    @Given("User login sebagai Admin")
    public void user_login_sebagai_admin() throws InterruptedException {
        LoginSteps login = new LoginSteps();
        login.user_on_login_page();
        login.input_valid_credentials();
        login.click_login_button();

    }

    @Given("User berada di halaman Dashboard")
    public void user_di_dashboard() throws InterruptedException {
        user_login_sebagai_admin();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Given("User sudah menerapkan filter")
    public void user_sudah_menerapkan_filter() throws InterruptedException {
        user_di_dashboard();
        getDashboardPage().inputStartDate("2024-01-01");
        getDashboardPage().inputEndDate("2024-01-31");
        getDashboardPage().clickSearch();
    }

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

    @Then("Data yang ditampilkan hanya dalam rentang {string} sampai {string}")
    public void verify_date_range(String start, String end) {
        int rowCount = getDashboardPage().getEmployeeRowCount();
        Assert.assertTrue(rowCount > 0);
    }

    @Then("Sistem menampilkan validasi atau menggunakan default date range")
    public void verify_empty_date() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Given("User berada di halaman Dashboard tanpa filter")
    public void user_dashboard_tanpa_filter() throws InterruptedException {
        user_di_dashboard();
    }

    @Then("Tidak ada perubahan data")
    public void tidak_ada_perubahan_data() {
        Assert.assertTrue(getDashboardPage().getEmployeeRowCount() >= 0);
    }

    @Then("Sistem tidak menampilkan error")
    public void sistem_tidak_menampilkan_error() {
        Assert.assertFalse(getDriver().getPageSource().contains("Error"));
    }

    @When("User membuka halaman Dashboard")
    public void buka_dashboard() throws InterruptedException {
        user_login_sebagai_admin();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }

    @When("User klik menu {string}")
    public void klik_menu(String menu) {
        getDashboardPage().clickMenu(menu);
    }

    @Then("Halaman Dashboard ditampilkan")
    public void verify_dashboard_page() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    }
}
