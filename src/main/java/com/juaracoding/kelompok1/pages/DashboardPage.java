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
    /* @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> listAddCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

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
        click(menuButton);
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
            click(logoutLink);
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
    } */

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
        // Para sa image elements, kunin ang alt attribute instead ng text
        waitForElementVisible(pageHeader);
        String altText = pageHeader.getAttribute("alt");
        if (altText != null && !altText.isEmpty()) {
            return altText;
        }
        return getText(pageHeader);
    }

    public void selectSortOption(String option) {
        Select select = new Select(sortDropdown);
        select.selectByValue(option);
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public boolean isSortedAscending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedDescending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
