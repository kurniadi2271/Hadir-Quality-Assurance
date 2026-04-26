package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.HttpURLConnection;
import java.net.URL;


public class LaporanSakit extends BasePage {

    public LaporanSakit(WebDriver driver) {
        super(driver);
    }

    // --- SEGMENT 1: NAVIGATION ELEMENTS ---
    @FindBy(xpath = "//*[contains(text(), 'Laporan')]")
    private WebElement laporanMenu;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//p[text()='Sakit']")
    private WebElement sakitSubMenu;

    @FindBy(xpath = "//div[contains(@class, 'MuiToolbar-root')]//p[text()='Sakit']")
    private WebElement sakitPageHeader;

    @FindBy(xpath = "//img[@alt='photo sakit'][1]") // Sesuaikan jika gambar ada di dalam modal
    private WebElement imagePreview;


    // --- SEGMENT 2: FILTER & CALENDAR ELEMENTS ---


    // --- SEGMENT 3: TABLE & ACTION ELEMENTS ---
    @FindBy(xpath = "//a[contains(text(), 'View')]")
    private WebElement viewButton;


    // --- METHODS: NAVIGATION ---
    public void clickLaporanMenu(){
        waitForElementVisible(laporanMenu);
        laporanMenu.click();
    }

    public void clickSakitSubMenu(){
        waitForElementVisible(sakitSubMenu);
        sakitSubMenu.click();
        waitForElementVisible(sakitPageHeader);
    }

    
    // --- METHODS: FILTER & SEARCH ---

    // --- METHODS: TABLE ACTIONS ---
    public void clickViewButton() {
        waitForElementVisible(viewButton);
        viewButton.click();
    }


    // --- METHODS: DATA VALIDATION ---

    // Helper untuk menggantikan Thread.sleep agar lebih rapi
    public boolean isImageDisplayed() {
        waitForElementVisible(imagePreview);
        return imagePreview.isDisplayed();
    }

    public String getImageSource() {
        return imagePreview.getAttribute("src");
    }

    public int getBrokenImageResponseCode() {
        String src = imagePreview.getAttribute("src");
        int responseCode = 0;
        try {
            URL url = new URL(src);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            responseCode = http.getResponseCode();
        } catch (Exception e) {
            System.out.println("Error checking image URL: " + e.getMessage());
        }
        return responseCode;
    }
}