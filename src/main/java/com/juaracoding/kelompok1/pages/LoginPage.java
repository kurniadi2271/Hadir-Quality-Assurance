package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "[role='alert']")
    private WebElement errorMessage;

    public void setCredentials(String email, String password) {
        click(emailField);
        sendKeys(emailField, email);
        click(passwordField);
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void clickLoginAndWait() {
        click(loginButton);
        // Tunggu hingga salah satu: URL berubah ke dashboard ATAU error message muncul
        try {
            waitForDashboardRedirect();
        } catch (Exception e) {
            // Mungkin login gagal, cek apakah ada error message
            System.out.println("Dashboard redirect timeout, checking for error message...");
            try {
                Thread.sleep(1000);
                if (isErrorVisible()) {
                    System.out.println("Error message found: " + getErrorText());
                }
            } catch (Exception ex) {
                System.out.println("Could not check for error message: " + ex.getMessage());
            }
            throw e; // Re-throw original exception
        }
    }

    public boolean isErrorVisible() {
        return isElementPresent(errorMessage);
    }

    public String getErrorText() {
        return getText(errorMessage);
    }

    public void waitForPageLoad() {
        waitForElementVisible(emailField);
    }

    public void waitForDashboardRedirect() {
        waitForUrlContains("dashboard");
    }
}
