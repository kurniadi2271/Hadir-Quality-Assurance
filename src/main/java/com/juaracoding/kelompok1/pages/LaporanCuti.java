package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.Keys;

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

    @FindBy(xpath = "//button[contains(text(), 'Terapkan')]")
    private WebElement terapkanButton;

    @FindBy(xpath = "//button[contains(text(), 'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//button[contains(text(), 'Search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Start Date']/following::button[1]")
    private WebElement datePicker;

    @FindBy(xpath = "//span[@class='rdrMonthPicker']/select")
    private WebElement monthDropdown;

    @FindBy(xpath = "//span[@class='rdrYearPicker']/select")
    private WebElement yearDropdown;

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

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    private List<WebElement> dateCells;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    private List<WebElement> nameCells;


    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickCutiSubMenu(){
        waitForElementVisible(cutiSubMenu);
        cutiSubMenu.click();
        waitForElementVisible(cutiPageHeader);
        delay(2);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 1. Pastikan input field siap
        wait.until(ExpectedConditions.elementToBeClickable(searchDepartmentInput));
        searchDepartmentInput.click(); // Klik dulu supaya fokus
        searchDepartmentInput.clear();
        
        // 2. Kirim teks satu per satu (opsional tapi lebih stabil untuk dropdown filter)
        for (char c : department.toCharArray()) {
            searchDepartmentInput.sendKeys(String.valueOf(c));
        }

        // 3. Tambahkan sedikit jeda agar filter selesai loading
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        String xpathOpsi = String.format("//li[@role='option' and contains(., '%s')]", department);
        
        // 4. Coba klik dengan mekanisme yang lebih kuat
        try {
            WebElement opsiTepat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOpsi)));
            
            // Klik pakai JavaScript biar gak rewel soal koordinat/stale
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", opsiTepat);
            
        } catch (Exception e) {
            // Jika gagal (misal ketutup), coba kirim tombol ENTER sebagai backup
            System.out.println("Gagal klik opsi, mencoba tekan ENTER sebagai alternatif");
            searchDepartmentInput.sendKeys(Keys.ENTER);
        }
    }

    public void clickTerapkanButton() {
        try {
            WebElement element = driver.findElement(By.xpath("//button[contains(., 'Terapkan')]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("JS Click failed: " + e.getMessage());
        }
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
        delay(2);
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

    // Method untuk mengambil semua teks tanggal dari kolom tertentu (misal kolom ke-4)
    public List<String> getAllDatesFromTable() {
        List<WebElement> dateElements = dateCells; 
        List<String> dates = new ArrayList<>();
        for (WebElement el : dateElements) {
            dates.add(el.getText());
        }
        return dates;
    }

    public List<String> getAllNamesFromTable() {
        List<WebElement> nameElements = nameCells; 
        List<String> names = new ArrayList<>();
        for (WebElement el : nameElements) {
            names.add(el.getText());
        }
        return names;
    }


    // --- METHODS: DATA VALIDATION ---
    public int getTableRowCount() {
        return tableRows.size();
    }

    public boolean tableEmpty() {
        return tableRows.isEmpty();
    }

    public String getSearchNameValue() {
        waitForElementVisible(searchNameInput);
        return searchNameInput.getAttribute("value");
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