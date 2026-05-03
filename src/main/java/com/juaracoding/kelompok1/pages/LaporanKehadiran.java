package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class LaporanKehadiran extends BasePage {

    public LaporanKehadiran(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Kehadiran']")
    private WebElement kehadiranSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Kehadiran']")
    private WebElement kehadiranPageHeader;


    // --- SEGMENT 2: FILTER & CALENDAR ELEMENTS ---


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---
    @FindBy(xpath = "//button[@type='submit' and text()='Export']")
    private WebElement exportButtonConfirmation;

    @FindBy(xpath = "//table/tbody/tr/td[4]")
    private List<WebElement> dateCells;

    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickKehadiranSubMenu(){
        waitForElementVisible(kehadiranSubMenu);
        kehadiranSubMenu.click();
        waitForElementVisible(kehadiranPageHeader);
        delay(2);
    }

    
    // --- METHODS: FILTER & SEARCH ---

    // --- METHODS: TABLE ACTIONS ---
    public void clickExportButtonConfirmation() {
        waitForElementVisible(exportButtonConfirmation);
        exportButtonConfirmation.click();
    }

    public List<String> getAllDatesFromTable() {
        List<WebElement> dateElements = dateCells; 
        List<String> dates = new ArrayList<>();
        for (WebElement el : dateElements) {
            dates.add(el.getText());
        }
        return dates;
    }


    // --- METHODS: DATA VALIDATION ---
    private void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}