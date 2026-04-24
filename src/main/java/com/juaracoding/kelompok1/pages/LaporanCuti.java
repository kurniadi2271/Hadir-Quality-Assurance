package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class LaporanCuti extends BasePage {
    
    public LaporanCuti(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement LaporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Cuti']")
    private WebElement CutiSubMenu;

    // Update your @FindBy in LaporanCuti.java
    @FindBy(xpath = "//input[@placeholder='Start Date']/following::button[1]")
    private WebElement DatePicker;

    // More flexible XPaths for the calendar days
    @FindBy(xpath = "//span[contains(@class,'rdrDayNumber')]//span[text()='1']")
    private WebElement StartDate;

    @FindBy(xpath = "//span[contains(@class,'rdrDayNumber')]//span[text()='22']")
    private WebElement EndDate;

    @FindBy(xpath = "//button[contains(text(), 'save')]")
    private WebElement SaveDate;

    @FindBy(xpath = "//button[contains(text(), 'Search')]")
    private WebElement SearchButton;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table/tbody/tr/td[contains(text(), 'No Data')]")
    private WebElement noDataMessage;

    public void clickLaporanMenu(){
        waitForElementVisible(LaporanMenu);
        click(LaporanMenu);
    }

    public void clickCutiSubMenu(){
        waitForElementVisible(CutiSubMenu);
        click(CutiSubMenu);
    }

    public int getTableRowCount() {
        return tableRows.size();
    }

    public boolean isTableEmpty() {
        // Cari elemen "No Data" atau cek size list
        return tableRows.isEmpty(); 
    }

    public void selectDateRange() {
        // 1. Wait for the page to be 'steady'
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        // 2. Re-verify the DatePicker is present and fresh
        waitForElementVisible(DatePicker);
        click(DatePicker);
        
        // 3. Wait specifically for the calendar overlay to appear
        waitForElementVisible(StartDate);
        click(StartDate);
        
        waitForElementVisible(EndDate);
        click(EndDate);
        
        waitForElementVisible(SaveDate);
        click(SaveDate);
    }

    public void clickSearchButton() {
        click(SearchButton);
    }

    public String getNoDataMessage() {
        return noDataMessage.getText();
    }
}
