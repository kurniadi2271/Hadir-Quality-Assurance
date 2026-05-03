package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import java.util.List;

public class LaporanLembur extends BasePage {

    public LaporanLembur(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Lembur']")
    private WebElement lemburSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Lembur']")
    private WebElement lemburPageHeader;

    // --- SEGMENT 2: FILTER & CALENDAR ELEMENTS ---


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---
    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table/tbody/tr/td[contains(text(), 'No Data')]")
    private WebElement noDataMessage;

    @FindBy(xpath = "//button[text()='Export']")
    private WebElement exportButton;


    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickLemburSubMenu(){
        waitForElementVisible(lemburSubMenu);
        lemburSubMenu.click();
        waitForElementVisible(lemburPageHeader);
        delay(2);
    }


    // --- METHODS: FILTER & SEARCH ---


    // --- METHODS: TABLE ACTIONS ---
    public void clickExportButton() {
        waitForElementVisible(exportButton);
        exportButton.click();
    }

    // --- METHODS: DATA VALIDATION ---
    public String getRequiredErrorMessage(String text) {
        try {
            // Kita masukkan string parameter ke dalam format XPath
            String dynamicXpath = String.format("//div[contains(text(), '%s')]", text);
            
            // Cari elemen langsung menggunakan xpath dinamis tersebut
            return driver.findElement(By.xpath(dynamicXpath)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Helper untuk menggantikan Thread.sleep agar lebih rapi
    private void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}