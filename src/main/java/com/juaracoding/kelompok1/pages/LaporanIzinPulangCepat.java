package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LaporanIzinPulangCepat extends BasePage {

    public LaporanIzinPulangCepat(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Izin Pulang Cepat']")
    private WebElement izinPulangCepatSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Izin Pulang Cepat']")
    private WebElement izinPulangCepatPageHeader;


    // --- SEGMENT 2: FILTER & CALENDAR ELEMENTS ---


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---
    @FindBy(xpath = "//button[@aria-label='Approval Koreksi']")
    private WebElement approvalButton;

    @FindBy(xpath = "//button[text()='Setujui']")
    private WebElement approvalConfirmButton;

    @FindBy(xpath = "//button[@aria-label='Reject Koreksi']")
    private WebElement rejectButton;
    
    @FindBy(xpath = "//input[@name=\"rejectReason\"]")
    private WebElement rejectReasonInput;
    
    @FindBy(xpath = "//button[text()='Tolak']")
    private WebElement rejectReasonConfirmButton;

    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickIzinPulangCepatSubMenu(){
        waitForElementVisible(izinPulangCepatSubMenu);
        izinPulangCepatSubMenu.click();
        waitForElementVisible(izinPulangCepatPageHeader);
        delay(2);
    }

    
    // --- METHODS: FILTER & SEARCH ---

    // --- METHODS: TABLE ACTIONS ---
    


    // --- METHODS: DATA VALIDATION ---
    private void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}