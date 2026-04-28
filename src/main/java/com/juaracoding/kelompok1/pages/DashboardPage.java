package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================

    By inputEmail = By.name("email");
    By inputPassword = By.id("password");
    By btnLogin = By.xpath("//button[@type='submit']");

    By startDate = By.xpath("//input[@placeholder='Start Date']");
    By endDate = By.xpath("//input[@placeholder='End Date']");

    By btnSearch = By.xpath("//button[contains(text(),'Search') or contains(text(),'Cari')]");
    By btnFilter = By.xpath("//button[contains(text(),'Filter') or contains(text(),'filter')]");
    By btnApply = By.xpath("//button[contains(text(),'Terapkan') or contains(text(),'Apply')]");
    By btnReset = By.xpath("//button[contains(text(),'Reset')]");

    By unitSelect = By.id("job_departement");

    By widgetLembur = By.xpath("//div[contains(translate(.,'LEMBUR','lembur'),'lembur')]");
    By widgetCuti = By.xpath("//div[contains(translate(.,'CUTI','cuti'),'cuti')]");
    By widgetKoreksi = By.xpath("//div[contains(translate(.,'KOREKSI','koreksi'),'koreksi')]");

    By tableRows = By.xpath("//table//tbody//tr");

    // ================= ACTIONS =================

    public void login(String email, String password) {

    driver.get("https://magang.dikahadir.com/authentication/login");

    WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
    emailField.clear();
    emailField.sendKeys(email);

    WebElement pwdField = wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
    pwdField.clear();
    pwdField.sendKeys(password);

    wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();

    // WAIT SAMPAI PINDAH KE DASHBOARD
    wait.until(ExpectedConditions.urlContains("dashboard"));

    // WAIT SAMPAI ELEMENT DASHBOARD READY
    wait.until(ExpectedConditions.visibilityOfElementLocated(startDate));
    }

    public void inputStartDate(String date) {
        WebElement element = waitVisible(startDate);
        element.clear();
        element.sendKeys(date);
    }

    public void inputEndDate(String date) {
        WebElement element = waitVisible(endDate);
        element.clear();
        element.sendKeys(date);
    }

    public void clickSearch() {
        waitVisible(btnSearch).click();
    }

    public int getEmployeeRowCount() {
        return driver.findElements(tableRows).size();
    }

    public void clickFilter() {
        waitVisible(btnFilter).click();
    }

    public void selectUnit(String unit) {
        Select select = new Select(waitVisible(unitSelect));
        select.selectByVisibleText(unit);
    }

    public void clickApply() {
        waitVisible(btnApply).click();
    }

    public void clickReset() {
        waitVisible(btnReset).click();
    }

    public void clickMenu(String menu) {
        String xpath = String.format("//*[normalize-space(text())='%s']", menu);
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        menuItem.click();
    }

    public boolean isWidgetLemburDisplayed() {
        return waitVisible(widgetLembur).isDisplayed();
    }

    public boolean isWidgetCutiDisplayed() {
        return waitVisible(widgetCuti).isDisplayed();
    }

    public boolean isWidgetKoreksiDisplayed() {
        return waitVisible(widgetKoreksi).isDisplayed();
    }

    public boolean isUnitDataDisplayed(String unit) {
        return driver.getPageSource().contains(unit);
    }

    public boolean isStartDateEmpty() {
        return driver.findElement(startDate).getAttribute("value").isEmpty();
    }

    public boolean isEndDateEmpty() {
        return driver.findElement(endDate).getAttribute("value").isEmpty();
    }
}
