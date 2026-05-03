package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.HttpURLConnection;
import java.net.URL;


public class LaporanKoreksi extends BasePage {

    public LaporanKoreksi(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Koreksi']")
    private WebElement koreksiSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Koreksi']")
    private WebElement koreksiPageHeader;


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

    public void clickKoreksiSubMenu(){
        waitForElementVisible(koreksiSubMenu);
        koreksiSubMenu.click();
        waitForElementVisible(koreksiPageHeader);
    }

    
    // --- METHODS: FILTER & SEARCH ---

    // --- METHODS: TABLE ACTIONS ---
    public void clickApprovalButton() {
        waitForElementVisible(approvalButton);
        approvalButton.click();
    }

    public void clickApprovalConfirmButton() {
        waitForElementVisible(approvalConfirmButton);
        approvalConfirmButton.click();
    }

    public void clickRejectButton() {
        waitForElementVisible(rejectButton);
        rejectButton.click();
    }

    public void enterRejectReason(String reason) {
        waitForElementVisible(rejectReasonInput);
        rejectReasonInput.clear();
        rejectReasonInput.sendKeys(reason);
    }

    public void clickRejectReasonConfirmButton() {
        waitForElementVisible(rejectReasonConfirmButton);
        rejectReasonConfirmButton.click();
    }


    // --- METHODS: DATA VALIDATION ---
}