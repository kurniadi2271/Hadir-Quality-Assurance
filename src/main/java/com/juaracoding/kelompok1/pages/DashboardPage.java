package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================
    @FindBy(xpath = "//img[@alt='Logo Hadir']")
    private WebElement pageHeader;

    @FindBy(xpath = "//button[@aria-controls='profile-menu']")
    private WebElement profileButton;

    @FindBy(xpath = "//button[normalize-space()='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@placeholder='Start Date']")
    private WebElement startDate;

    @FindBy(xpath = "//input[@placeholder='End Date']")
    private WebElement endDate;

    @FindBy(xpath = "//button[contains(text(),'Search') or contains(text(),'Cari')]")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private WebElement btnReset;

    @FindBy(id = "job_departement")
    private WebElement unitSelect;

    @FindBy(xpath = "//div[contains(translate(.,'LEMBUR','lembur'),'lembur')]")
    private WebElement widgetLembur;

    @FindBy(xpath = "//div[contains(translate(.,'CUTI','cuti'),'cuti')]")
    private WebElement widgetCuti;

    @FindBy(xpath = "//div[contains(translate(.,'KOREKSI','koreksi'),'koreksi')]")
    private WebElement widgetKoreksi;

    @FindBy(xpath = "//table//tbody//tr")
    private java.util.List<WebElement> tableRows;

    // ================= ACTIONS =================
    public String getPageHeader() {
        waitForElementVisible(pageHeader);
        return pageHeader.getAttribute("alt");
    }

    public void inputStartDate(String date) {
        sendKeys(startDate, date); // Menggunakan method dari BasePage
    }

    public void inputEndDate(String date) {
        sendKeys(endDate, date);
    }

    public void clickSearch() {
        click(btnSearch);
    }

    public void clickReset() {
        click(btnReset);
    }

    public void clickProfileButton() {
        click(profileButton);
    }

    public void clickLogoutButton() {
        click(logoutButton);
    }

    public int getEmployeeRowCount() {
        return tableRows.size();
    }

    public void clickMenu(String menu) {
        String xpath = String.format("//*[normalize-space(text())='%s']", menu);
        driver.findElement(By.xpath(xpath)).click();
    }

    public boolean areWidgetsDisplayed() {
        return isElementPresent(widgetLembur) && 
               isElementPresent(widgetCuti) && 
               isElementPresent(widgetKoreksi);
    }
}