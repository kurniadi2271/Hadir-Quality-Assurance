package com.juaracoding.kelompok1.pages;

import com.juaracoding.kelompok1.utils.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReportsLoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public ReportsLoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(Constants.URL);
        waitForElementVisible(emailInput);
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(submitButton);
    }

    public boolean isLoggedIn() {
        try {
            wait.until(d -> d.getCurrentUrl() != null && !d.getCurrentUrl().contains("/authentication/login"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
