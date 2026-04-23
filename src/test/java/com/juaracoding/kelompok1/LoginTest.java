package com.juaracoding.kelompok1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.DashboardPage;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.utils.Constants;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @After
    public void cleanUp(Scenario scenario) {
        System.out.println("\n=== Starting cleanUp for scenario: " + scenario.getName() + " ===");
        if (driver != null) {
            try {
                // Coba navigate ke login page instead of clicking logout
                // Ini lebih stabil daripada trying to click elements yang mungkin tidak ada
                driver.navigate().to(Constants.URL);
                Thread.sleep(1000);
                System.out.println("Successfully navigated to login page");
            } catch (Exception e) {
                System.out.println("Could not navigate to login page: " + e.getMessage());
            } finally {
                // Reset singleton instance untuk scenario berikutnya
                try {
                    DriverSingleton.closeObjectInstance();
                    System.out.println("Driver closed successfully");
                } catch (Exception e) {
                    System.out.println("Could not close driver properly: " + e.getMessage());
                }
            }
        }
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(Constants.URL);
    }


    @When("the user enters valid email and password")
    public void the_user_enters_valid_email_and_password() {
        loginPage.setCredentials("admin@hadir.com", "MagangSQA_JC@123");
        loginPage.waitForPageLoad();
    }


    @And("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLoginAndWait();
    }

    @Then("the user should be redirected to the dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        System.out.println("Expected: URL should contain 'dashboard'");
        System.out.println("Page title: " + driver.getTitle());
        Assert.assertTrue(currentUrl.contains("dashboard"), 
            "User should be logged in and on the dashboard page. Current URL: " + currentUrl);
    }

    @And("the page header should display Logo Hadir")
    public void the_page_header_should_display_Logo_Hadir() {
        Assert.assertEquals(dashboardPage.getPageHeader(), "Logo Hadir", "Page header should display Logo Hadir");
    }
    
    @And("the user clicks the logout button")
    public void the_user_clicks_the_logout_button() {
        dashboardPage.clickMenu();
        dashboardPage.clickLogout();
        System.out.println("User melakukan logout.");
    }

    @Then("the user should be redirected back to the login page")
    public void the_user_should_be_redirected_back_to_the_login_page() {
        // Verifikasi apakah tombol login muncul kembali atau URL kembali ke awal
        Assert.assertTrue(driver.getCurrentUrl().equals("https://magang.dikahadir.com/authentication/login"), "User gagal logout!");
    }

    @When("the user enters wrong email and password")
    public void the_user_enters_invalid_email_and_password() {
        // dashboardPage.clickMenu();
        // dashboardPage.clickLogout();
        loginPage.setCredentials("wrong_user@gmail.com", "wrong_password");
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        Assert.assertTrue(loginPage.getErrorText().contains("Username and password do not match"), "Error message should indicate invalid credentials");
    }
}
