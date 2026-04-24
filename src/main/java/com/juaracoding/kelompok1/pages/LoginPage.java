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

    @FindBy(xpath = "//p[text()='Akun tidak ditemukan']")
    private WebElement errorMessage;

    public void setCredentials(String email, String password) {
        click(emailField);
        sendKeys(emailField, email);
        click(passwordField);
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        // Tunggu tombol stabil, lalu klik. Titik.
        waitForElementVisible(loginButton);
        click(loginButton);
    }

    public String getErrorText() {
        // TUNGGU dulu sampai elemennya muncul sebelum ambil teks
        waitForElementVisible(errorMessage); 
        return errorMessage.getText();
    }

    public boolean isErrorVisible() {
        try {
            // Gunakan wait dengan waktu singkat untuk cek keberadaan error
            waitForElementVisible(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForPageLoad() {
        waitForElementVisible(emailField);
    }

    // Tambahkan method pembantu di BasePage atau LoginPage
    public boolean isDashboardPresent() {
        return waitForUrlContains("dashboard"); 
    }

    public void waitForDashboardRedirect() {
        waitForUrlContains("dashboard");
    }
}
