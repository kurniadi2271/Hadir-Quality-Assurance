package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    } 

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    
    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1qamc72']")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@id='password']/parent::div//button")
    private WebElement passwordToggle;

    @FindBy(xpath = "//button[contains(@class,'MuiIconButton')]")
    private WebElement passwordToggleButton;


    public String getPasswordFieldType() {
        return passwordField.getAttribute("type");
    }


    public void setCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

  

    public void clickTogglePassword() {
            waitForElementVisible(passwordToggle);
            passwordToggle.click();
        }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
    }

    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))).isDisplayed();
    }

    public String getCurrentUrl() {
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("saucedemo.com"),
            ExpectedConditions.visibilityOf(loginButton)
        ));
        return driver.getCurrentUrl();
    }

    public String getEmailValidationMessage(WebDriver driver) {
    return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
        .executeScript("return document.getElementById('email').validationMessage;");
}

    

}
