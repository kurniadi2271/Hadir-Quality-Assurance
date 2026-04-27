package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import java.util.List;

public class LaporanCuti extends BasePage {

    public LaporanCuti(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Cuti']")
    private WebElement cutiSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Cuti']")
    private WebElement cutiPageHeader;

    // --- SEGMENT 2: FILTER & CALENDAR ELEMENTS ---
    @FindBy(xpath = "//button[contains(@class, 'MuiButton-root') and .//*[contains(@class, 'feather-filter')]]")
    private WebElement filterButton;

    @FindBy(id = "search")
    private WebElement searchNameInput;

    @FindBy(xpath = "//input[@id='job_departement']")
    private WebElement searchDepartmentInput;

    @FindBy(xpath ="(//li[@role='option'])[1]")
    private WebElement firstDepartmentOption;

    @FindBy(xpath = "//button[text()='Terapkan']")
    private WebElement terapkanButton;

    @FindBy(xpath = "//button[contains(text(), 'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//button[contains(text(), 'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Start Date']/following::button[1]")
    private WebElement datePicker;

    @FindBy(xpath = "//button[contains(text(), 'save')]")
    private WebElement saveDate;


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---
    @FindBy(xpath = "//*[contains(@aria-label, 'action')][1]")
    private WebElement actionButton;

    @FindBy(xpath = "//li[@role='menuitem' and contains(., 'Cancel Cuti')][1]")
    private WebElement cancelCutiAction;

    @FindBy(id = "rejectReason")
    private WebElement cancelCutiReasonInput;

    @FindBy(xpath = "//button[contains(text(), 'Iya')]")
    private WebElement cancelCutiConfirmButton;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table/tbody/tr/td[contains(text(), 'No Data')]")
    private WebElement noDataMessage;


    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickCutiSubMenu(){
        waitForElementVisible(cutiSubMenu);
        cutiSubMenu.click();
        waitForElementVisible(cutiPageHeader);
    }


    // --- METHODS: FILTER & SEARCH ---
    public void enterSearchName(String name) {
        waitForElementVisible(searchNameInput);
        searchNameInput.sendKeys(name);
    }

    public void clickFilterButton() {
        delay(1);
        int attempts = 0;
        while(attempts < 3) {
            try {
                waitForElementVisible(filterButton);
                filterButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    public void enterSearchDepartment(String department) {
        waitForElementVisible(searchDepartmentInput);
        searchDepartmentInput.clear();
        searchDepartmentInput.sendKeys(department);
        waitForElementVisible(firstDepartmentOption);
        delay(1);
        firstDepartmentOption.click();
    }

    public void clickTerapkanButton() {
        waitForElementVisible(terapkanButton);
        terapkanButton.click();
        delay(1);
    }

    public void clickSearchButton() {
        waitForElementVisible(searchButton);
        searchButton.click();
        delay(1);
    }

    public void clickResetButton() {
        waitForElementVisible(resetButton);
        resetButton.click();
    }

    private void clickDynamicDate(String date) {
        String xpath = String.format("//span[contains(@class,'rdrDayNumber')]//span[text()='%s']", date);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectDateRange(String start, String end) {
        // Menggunakan delay kecil untuk stabilitas UI jika diperlukan
        delay(1); 
        waitForElementVisible(datePicker);
        datePicker.click();
        
        clickDynamicDate(start);
        
        clickDynamicDate(end);
        
        waitForElementVisible(saveDate);
        saveDate.click();
    }


    // --- METHODS: TABLE ACTIONS ---
    public void scrollToActionButton() {
        waitForElementVisible(actionButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", actionButton);
        delay(1);
    }

    public void clickActionButton() {
        waitForElementVisible(actionButton);
        actionButton.click();
    }

    public void clickCancelCutiAction() {
        waitForElementVisible(cancelCutiAction);
        cancelCutiAction.click();
    }

    public void enterCancelCutiReason(String reason) {
        waitForElementVisible(cancelCutiReasonInput);
        cancelCutiReasonInput.sendKeys(reason);
    }

    public void clickCancelCutiConfirmButton() {
        waitForElementVisible(cancelCutiConfirmButton);
        cancelCutiConfirmButton.click();
    }


    // --- METHODS: DATA VALIDATION ---
    public int getTableRowCount() {
        return tableRows.size();
    }

    public boolean isTableEmpty() {
        return tableRows.isEmpty() || getNoDataMessage().contains("No Data");
    }

    public String getSearchNameValue() {
        waitForElementVisible(searchNameInput);
        return searchNameInput.getAttribute("value");
    }

    public String getNoDataMessage() {
        try {
            return noDataMessage.getText();
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