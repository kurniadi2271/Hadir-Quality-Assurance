package com.juaracoding.kelompok1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.InventoryPage;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.utils.Constants;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static InventoryPage inventoryPage;

    @BeforeAll
    public static void setUp() {
        DriverSingleton.getInstance(Constants.FIREFOX);
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    /* @After
    public void cleanUp(Scenario scenario) {
        // Hanya logout jika kita berada di halaman inventory (sudah login)
        if (driver.getCurrentUrl().contains("inventory")) {
            inventoryPage.clickLogout();
        }
        
    } */

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(Constants.URL);
    }

    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        loginPage.setCredentials("standard_user", "secret_sauce");
    }


    @And("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "User should be logged in and on the inventory page");
    }

    @And("the page header should display Products")
    public void the_page_header_should_display_Products() {
        Assert.assertEquals(inventoryPage.getPageHeader(), "Products", "Page header should display Products");
    }
    
    @And("the user clicks the reset app state button")
    public void the_user_clicks_the_reset_app_state_button() {
        inventoryPage.clickMenu();
        inventoryPage.clickResetAppState();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("User melakukan reset app state.");
        inventoryPage.closeMenu();
    }

    @And("the user clicks the logout button")
    public void the_user_clicks_the_logout_button() {
        inventoryPage.clickMenu();
        inventoryPage.clickLogout();
        System.out.println("User melakukan logout.");
    }

    @Then("the user should be redirected back to the login page")
    public void the_user_should_be_redirected_back_to_the_login_page() {
        // Verifikasi apakah tombol login muncul kembali atau URL kembali ke awal
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/"), "User gagal logout!");
    }

    @When("the user enters wrong username and password")
    public void the_user_enters_invalid_username_and_password() {
        // inventoryPage.clickMenu();
        // inventoryPage.clickLogout();
        loginPage.setCredentials("wrong_user", "wrong_password");
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        Assert.assertTrue(loginPage.getErrorText().contains("Username and password do not match"), "Error message should indicate invalid credentials");
    }
}
