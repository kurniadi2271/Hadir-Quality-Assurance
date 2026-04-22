package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='title']")
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

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public void clickMenu() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            menuButton.click();
        } catch (Exception e) {
            // If menu already open, continue
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickResetAppState() {
        try {
            resetAppStateLink.click();
        } catch (Exception e) {
            // Handle if element not visible
        }
    }

    public void closeMenu() {
        try {
            Thread.sleep(300);
            menuButton.click();
            Thread.sleep(500);
        } catch (Exception e) {
            // Menu close attempt failed, continue
        }
    }

    public void clickLogout() {
        try {
            logoutLink.click();
        } catch (Exception e) {
            // Retry if element is intercepted
            try {
                Thread.sleep(1000);
                logoutLink.click();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
        addButton.click();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }

    public void clickCart() {
        cartLink.click();
    }

    public String getPageHeader() {
        return pageHeader.getText();
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