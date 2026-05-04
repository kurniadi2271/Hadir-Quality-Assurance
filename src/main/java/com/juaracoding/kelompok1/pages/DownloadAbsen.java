package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

public class DownloadAbsen extends BasePage {

    public DownloadAbsen(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Download Absen']")
    private WebElement downloadAbsenSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Download Absen']")
    private WebElement downloadAbsenPageHeader;

    // --- SEGMENT 2: SEARCH & CALENDAR ELEMENTS ---
    @FindBy(xpath = "//input[@placeholder='Cari berdasarkan NIK']")
    private WebElement searchNikInput;

    @FindBy(xpath = "//input[@placeholder='Cari berdasarkan nama']")
    private WebElement searchNameInput;

    @FindBy(xpath = "//input[@placeholder='Cari berdasarkan upliner']")
    private WebElement searchUplinerInput;

    @FindBy(xpath = "//input[@placeholder='Pilih Divisi']")
    private WebElement searchDivisiInput;

    @FindBy(xpath = "//input[@placeholder='Pilih Unit']")
    private WebElement searchUnitInput;

    @FindBy(xpath = "//button[contains(text(), 'Download')]")
    private WebElement downloadButton;

    @FindBy(xpath = "//button[contains(text(), 'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//input[@placeholder='Start Date']/following::button[1]")
    private WebElement datePicker;

    @FindBy(xpath = "//span[@class='rdrMonthPicker']/select")
    private WebElement monthDropdown;

    @FindBy(xpath = "//span[@class='rdrYearPicker']/select")
    private WebElement yearDropdown;

    @FindBy(xpath = "//input[@placeholder='Start Date']/following::button[2]")
    private WebElement saveDate;


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---


    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickDownloadAbsenSubMenu(){
        waitForElementVisible(downloadAbsenSubMenu);
        downloadAbsenSubMenu.click();
        waitForElementVisible(downloadAbsenPageHeader);
        delay(2);
    }


    // --- METHODS: FILTER & SEARCH ---
    public void enterSearchNik(String nik) {
        waitForElementVisible(searchNikInput);
        searchNikInput.clear();
        searchNikInput.sendKeys(nik);
        delay(1);
        // Membuat dynamic xpath yang mencari element dengan teks yang pas
        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", nik);
        
        WebElement opsiTepat = driver.findElement(By.xpath(xpathOpsi));
        waitForElementVisible(opsiTepat);
        opsiTepat.click();
    }

    public void enterSearchName(String name) {
        waitForElementVisible(searchNameInput);
        searchNameInput.clear();
        searchNameInput.sendKeys(name);
        delay(1);
        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", name);
        
        WebElement opsiTepat = driver.findElement(By.xpath(xpathOpsi));
        waitForElementVisible(opsiTepat);
        opsiTepat.click();
    }

    public void enterSearchUpliner(String upliner) {
        waitForElementVisible(searchUplinerInput);
        searchUplinerInput.clear();
        searchUplinerInput.sendKeys(upliner);
        delay(1);
        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", upliner);
        
        WebElement opsiTepat = driver.findElement(By.xpath(xpathOpsi));
        waitForElementVisible(opsiTepat);
        opsiTepat.click();
    }

    public void enterSearchDivisi(String divisi) {
        waitForElementVisible(searchDivisiInput);
        searchDivisiInput.clear();
        searchDivisiInput.sendKeys(divisi);
        delay(1);
        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", divisi);
        
        WebElement opsiTepat = driver.findElement(By.xpath(xpathOpsi));
        waitForElementVisible(opsiTepat);
        opsiTepat.click();
    }

    public void enterSearchUnit(String unit) {
        waitForElementVisible(searchUnitInput);
        searchUnitInput.clear();
        searchUnitInput.sendKeys(unit);
        delay(1);
        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", unit);
        
        WebElement opsiTepat = driver.findElement(By.xpath(xpathOpsi));
        waitForElementVisible(opsiTepat);
        opsiTepat.click();
    }

    public void clickDownloadButton() {
        waitForElementVisible(downloadButton);
        downloadButton.click();
        delay(5);
    }

    private void selectMonth(String month) {
        Select select = new Select(monthDropdown);
        select.selectByVisibleText(month);
    }

    private void selectYear(String year) {
        Select select = new Select(yearDropdown);
        select.selectByVisibleText(year);
    }

    private void clickDynamicDate(String date) {
        String xpath = String.format("//span[contains(@class,'rdrDayNumber')]//span[text()='%s']", date);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectFullDateRange(String startDay, String startMonth, String startYear, 
                                String endDay, String endMonth, String endYear) {
        waitForElementVisible(datePicker);
        datePicker.click();
        delay(1);

        // Pilih Bulan & Tahun Awal (Opsional: jika picker hanya satu panel, biasanya set start dulu)
        selectMonth(startMonth);
        selectYear(startYear);
        clickDynamicDate(startDay);

        // Pilih Bulan & Tahun Akhir
        selectMonth(endMonth);
        selectYear(endYear);
        clickDynamicDate(endDay);

        waitForElementVisible(saveDate);
        saveDate.click();
    }

    public void clickResetButton() {
        waitForElementVisible(resetButton);
        resetButton.click();
    }

    // --- METHODS: TABLE ACTIONS ---


    // --- METHODS: DATA VALIDATION ---

    // Helper untuk menggantikan Thread.sleep agar lebih rapi
    private void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}