package com.anne.reports_tc01_tc04.pages;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath(
            "//input[" +
                    "@type='email' or @name='email' or @id='email' or " +
                    "contains(translate(@placeholder,'EMAIL','email'),'email')" +
                    "]"
    );
    private final By passwordInput = By.xpath(
            "//input[" +
                    "@type='password' or @name='password' or @id='password' or " +
                    "contains(translate(@placeholder,'PASSWORD','password'),'password')" +
                    "]"
    );
    private final By submitButton = By.xpath(
            "//button[" +
                    "@type='submit' or " +
                    "contains(translate(normalize-space(.),'MASUKLOGIN','masuklogin'),'masuk') or " +
                    "contains(translate(normalize-space(.),'MASUKLOGIN','masuklogin'),'login')" +
                    "]"
    );

    public LoginPage() {
        this.driver = DriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get(Constants.URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    }

    public void login(String email, String password) {
        WebElement emailEl = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement passEl = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passEl.clear();
        passEl.sendKeys(password);

        WebElement btn = firstVisibleEnabled(submitButton);
        btn.click();
    }

    private WebElement firstVisibleEnabled(By locator) {
        FluentWait<WebDriver> w = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return w.until(d -> {
            List<WebElement> els = d.findElements(locator);
            for (WebElement el : els) {
                if (el.isDisplayed() && el.isEnabled()) return el;
            }
            return null;
        });
    }

    public boolean isLoggedIn() {
        // Heuristic: after login, URL should change away from /authentication/login
        try {
            wait.until(d -> d.getCurrentUrl() != null && !d.getCurrentUrl().contains("/authentication/login"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

