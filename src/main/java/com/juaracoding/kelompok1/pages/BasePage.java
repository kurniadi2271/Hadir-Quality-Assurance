package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long TIMEOUT_SECONDS = 30; // Increased timeout

    public BasePage(WebDriver driver) {
        this.driver = Objects.requireNonNull(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    // Metode klik yang menunggu elemen clickable
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Metode kirim teks yang menunggu elemen visible
    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Metode untuk menunggu elemen visible
    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Metode untuk menunggu elemen presence
    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Metode untuk menunggu URL mengandung teks tertentu
    public boolean waitForUrlContains(String partUrl) {
        return wait.until(ExpectedConditions.urlContains(partUrl));
    }

    // Metode untuk mendapatkan teks elemen
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // Metode untuk menunggu elemen tidak visible
    protected boolean waitForElementInvisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            return false;
        }
    }
}