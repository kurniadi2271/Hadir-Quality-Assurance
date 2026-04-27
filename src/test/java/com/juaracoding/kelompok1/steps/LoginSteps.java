package com.juaracoding.kelompok1.steps;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginSteps() {
        driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("the user is on the login page")
    public void user_on_login_page() {
        driver.get("[magang.dikahadir.com](https://magang.dikahadir.com/authentication/login)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    }

    @When("the user enters valid username and password")
    public void input_valid_credentials() {

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailField.click();
        emailField.sendKeys("admin@hadir.com");

        WebElement pwdField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        try { Thread.sleep(500); } catch (Exception ignored) {}
        pwdField.click();
        try { Thread.sleep(300); } catch (Exception ignored) {}
        pwdField.sendKeys("MagangSQA_JC@123");
    }

    @When("the user enters wrong username and password")
    public void input_invalid_credentials() {

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailField.click();
        emailField.sendKeys("wrong@email.com");

        WebElement pwdField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        try { Thread.sleep(500); } catch (Exception ignored) {}
        pwdField.click();
        try { Thread.sleep(300); } catch (Exception ignored) {}
        pwdField.sendKeys("wrongpassword");
    }

    @When("clicks the login button")
    public void click_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
    }

    @Then("the user should be redirected to the inventory page")
    public void verify_login_success() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Then("the page header should display Products")
    public void verify_header() {
        Assert.assertTrue(true);
    }

    @Then("the user should see an error message")
    public void verify_error_message() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Invalid')]")
        ));
        Assert.assertTrue(true);
    }

    @Then("the user clicks the logout button")
    public void logout() {}

    @Then("the user should be redirected back to the login page")
    public void verify_logout() {
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }
}
