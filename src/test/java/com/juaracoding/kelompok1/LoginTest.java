package com.juaracoding.kelompok1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.kelompok1.pages.DashboardPage;
import com.juaracoding.kelompok1.pages.LoginPage;
import com.juaracoding.kelompok1.utils.Constants;
import com.juaracoding.kelompok1.utils.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {

    private WebDriver driver = Hooks.driver;
    private LoginPage loginPage = Hooks.loginPage;
    private DashboardPage dashboardPage = Hooks.dashboardPage;

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
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        // Kita kasih waktu 10-15 detik untuk pindah halaman
        boolean success = loginPage.isDashboardPresent(); 
        Assert.assertTrue(success, "Gagal login! URL tidak berpindah ke dashboard.");
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
        loginPage.setCredentials("wrong_user@gmail.com", "wrong_password");
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        // Jangan cek URL! Langsung cek teks errornya.
        String actualError = loginPage.getErrorText(); 
        Assert.assertTrue(actualError.contains("Akun tidak ditemukan") || actualError.contains("match"), 
            "Pesan error tidak muncul atau salah teks!");
    }
}
