package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@alt='Logo Hadir']")
    private WebElement pageHeader;

    // COLLECTION: Menangkap semua tombol add to cart
    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> listAddCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @FindBy(xpath = "//button[@aria-label='menu'][contains(., 'Admin')]")
    private WebElement profileMenu;

    @FindBy(xpath = "//button[contains(., 'Logout')]")
    private WebElement logoutButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public void clickMenu() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        click(profileMenu);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickResetAppState() {
        click(resetAppStateLink);
    }

    public void closeMenu() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        click(menuButton);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickLogout() {
        try {
            click(logoutButton);
            // Tunggu halaman berubah ke login page
            try {
                Thread.sleep(2000);
                wait.until(ExpectedConditions.urlContains("login"));
            } catch (Exception e) {
                System.out.println("Waiting for login page URL: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
            throw e;
        }
    }

    public void addAllProductsToCart() {
        for (WebElement btn : listAddCartButtons) {
            btn.click();
        }
    }

    public void addProductToCart(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        WebElement addButton = driver.findElement(By.id(id));
        click(addButton);
    }

    public String getCartCount() {
        return getText(cartBadge);
    }

    public void clickCart() {
        click(cartLink);
    }

    public String getPageHeader() {
        // Untuk elemen gambar, gunakan atribut alt sebagai pengganti teks.
        waitForElementVisible(pageHeader);
        String altText = pageHeader.getAttribute("alt");
        if (altText != null && !altText.isEmpty()) {
            return altText;
        }
        return getText(pageHeader);
    }
    
}
