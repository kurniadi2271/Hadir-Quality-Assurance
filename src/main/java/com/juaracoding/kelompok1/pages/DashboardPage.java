package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    
    @FindBy(xpath = "//img[@alt='Logo Hadir']")
    private WebElement pageHeader;

    @FindBy(xpath = "//button[@aria-controls='profile-menu']")
    private WebElement profileButton;

    @FindBy(xpath = "//button[normalize-space()='Logout']")
    private WebElement logoutButton;

    public String getPageHeader() {
        // Para sa image elements, kunin ang alt attribute instead ng text
        waitForElementVisible(pageHeader);
        String altText = pageHeader.getAttribute("alt");
        if (altText != null && !altText.isEmpty()) {
            return altText;
        }
        return getText(pageHeader);
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
