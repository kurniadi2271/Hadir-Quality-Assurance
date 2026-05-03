package com.juaracoding.kelompok1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;


import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.utils.Constants;
import com.juaracoding.kelompok1.pages.DashboardPage;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginTest {
    Logger logger = org.slf4j.LoggerFactory.getLogger(LoginTest.class);

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private Dotenv dotenv;

    @Before
    public void setUp() {
        dotenv = Dotenv.load();
        logger.info("Setting up WebDriver and initializing page objects");
        logger.debug("Initializing WebDriver for Firefox");
        DriverSingleton.getInstance(Constants.FIREFOX);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        logger.info("setUp completed successfully");
    }

    @After
    public void tearDown(){
        logger.info("Tearing down WebDriver instance");
        DriverSingleton.closeObjectInstance();
        logger.info("tearDown completed successfully");
    }

    //-----TC_LOG_001 - Login Valid (Positive)-----
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
    logger.info("Navigating to login page: " + Constants.URL);
    driver.get(Constants.URL);
    logger.info("Current URL: " + Constants.URL);}

    @When("User enters valid email and password")
    public void the_user_enters_valid_email_and_password() {
        loginPage.setCredentials(dotenv.get("HADIR_EMAIL"), dotenv.get("HADIR_PASSWORD"));
    }

    @And("The user clicks the login button")
    public void clicks_the_login_button() throws InterruptedException {
        logger.info("Clicking the login button");
        loginPage.clickLogin();
        Thread.sleep(5000); 
    }

    @Then("The user is successfully redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard_page() throws InterruptedException {
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL after login: " + currentUrl);
        if (!currentUrl.contains("dashboard")) {
            logger.error("Login failed - user is not on dashboard. Current URL: " + currentUrl);
        }
        Assert.assertTrue(currentUrl.contains("dashboard"),
            "User should be on dashboard. Current URL: " + currentUrl);
        Assert.assertEquals(dashboardPage.getPageHeader(),
            "Logo Hadir", "Page header should display Logo Hadir");
        logger.info("Login successful - user is on dashboard with correct header");
    }

    //-----TC_LOG_002 - Logout Function-----
    @Given("User is already logged in to the Dashboard")
    public void user_is_already_logged_in_to_the_dashboard() throws InterruptedException {
        driver.get(Constants.URL);
        loginPage.setCredentials(dotenv.get("HADIR_EMAIL"), dotenv.get("HADIR_PASSWORD"));
        loginPage.clickLogin();
        Thread.sleep(3000); 
        logger.info("User logged in successfully for logout test");
    }

    @When("User clicks the profile name in the top right corner")
    public void user_clicks_the_profile_name_in_the_top_right_corner() {
        dashboardPage.clickProfileButton();
    }

    @And("User clicks the Logout button")
    public void user_clicks_the_logout_button() throws InterruptedException{
        logger.info("Clicking the Logout button");
        dashboardPage.clickLogoutButton();
        Thread.sleep(3000); }

    @And("User is redirected back to the login page")
    public void user_is_redirected_back_to_the_login_page() {
        String currentUrl = loginPage.getCurrentUrl();
        if (!currentUrl.contains("authentication/login")) {
            logger.warn("warning - User may not be redirected to login page. Current URL: " + currentUrl);
            logger.error("Logout may have failed - user is not on login page. Current URL: " + currentUrl);
        }
 
        Assert.assertTrue(currentUrl.contains("authentication/login"),
            "User should be redirected to login page after logout. Current URL: " + currentUrl);
        logger.info("Logout successful - user is redirected to login page");
        } 
        
    //-----TC_LOG_003 - Login Invalid Password-----
    @When("User enters valid email and invalid password")
    public void user_enters_valid_email_and_invalid_password() {
        loginPage.setCredentials(dotenv.get("HADIR_EMAIL"), "wrongPassword123");
    }

    @Then("An error message is displayed")
    public void an_error_message_is_displayed() {
        logger.info("Checking for error message visibility and text");
        Assert.assertTrue(loginPage.isErrorVisible(), "Expected error message not found");
        Assert.assertEquals(loginPage.getErrorText(), "Email atau password salah",
            "Expected error message text does not match");
        logger.info("Error message displayed correctly: " + loginPage.getErrorText());
    }

    //-----TC_LOG_004 - Login Invalid Email-----
    @When("User enters invalid email and valid password")
    public void user_enters_invalid_email_and_valid_password() {
        loginPage.setCredentials("wrongemail@hadir.com", dotenv.get("HADIR_PASSWORD"));
    }
    
    @Then("An error message 'Akun tidak ditemukan' is displayed")
    public void a_validation_message_akun_tidak_ditemukan_is_displayed() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("Checking for error message visibility and text for invalid email");
        Assert.assertTrue(loginPage.isErrorVisible(), "Expected error message not found");
        Assert.assertEquals(loginPage.getErrorText(), "Akun tidak ditemukan",
            "Expected error message text does not match");
        logger.info("Error message displayed correctly for invalid email: " + loginPage.getErrorText());
    }

    //-----TC_LOG_005 - Empty Field Validation-----
    @When("User leaves email and password fields empty")
    public void user_leaves_email_and_password_fields_empty() {
        loginPage.setCredentials("", "");
    }

    //-----TC_LOG_006 - Email Format Check-----
    @When("User enters invalid format email without '@' character")
    public void user_enters_invalid_format_email() {
        loginPage.setCredentials("adminhadir.com", dotenv.get("HADIR_PASSWORD"));
    }

    @Then("An invalid email format validation message is displayed")
    public void a_browser_validation_message_is_displayed() {
        logger.info("Checking for browser validation message for invalid email format");
        String validationMsg = loginPage.getEmailValidationMessage(driver);
        logger.info("Browser validation message: " + validationMsg);
        Assert.assertFalse(validationMsg.isEmpty(),
            "Browser validation message should appear. Actual: " + validationMsg);
    }

    //-----TC_LOG_007 - Password Visible Toggle Check-----
    @When("User enters a password in the password field")
    public void user_enters_a_password_in_the_password_field() {
        loginPage.setCredentials(dotenv.get("HADIR_EMAIL"), dotenv.get("HADIR_PASSWORD"));
    }

    @And ("User clicks the password visibility toggle icon")
    public void user_clicks_the_password_visibility_toggle_icon() throws InterruptedException {
        loginPage.clickTogglePassword();
        Thread.sleep(3000);
    }

    @Then("The password should become visible")
    public void the_password_should_become_visible() {
        String inputType = loginPage.getPasswordFieldType();
        logger.info("Password field type after toggle: " + inputType);
        Assert.assertEquals(inputType, "text",
            "Password should be visible. Field type: " + inputType);
    }

    

}