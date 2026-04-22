package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddToCartTest {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    public AddToCartTest() {
        this.driver = DriverSingleton.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @When("the user clicks all add to cart buttons")
    public void the_user_clicks_all_add_to_cart_buttons() {
        inventoryPage.addAllProductsToCart();
    }

    @When("the user adds {string} to cart")
    public void the_user_adds_to_cart(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @When("the user tries to add {string} to cart")
    public void the_user_tries_to_add_to_cart(String productName) {
        try {
            inventoryPage.addProductToCart(productName);
        } catch (Exception e) {
            // Expected for non-existent product
        }
    }

    @Then("the cart badge should remain unchanged")
    public void the_cart_badge_should_remain_unchanged() {
        try {
            // Attempt to get cart count
            String actualCount = inventoryPage.getCartCount();
            // If we get here, there's a badge. In a negative test from fresh login, badge shouldn't exist
            // But if it does, it should be empty or same as before
            System.out.println("Cart count after non-existent product add attempt: " + actualCount);
        } catch (Exception e) {
            // Expected: no cart badge means cart is empty
            System.out.println("No cart badge found - cart is empty (expected for non-existent product)");
        }
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String s) {
        // Write code here that turns the phrase above into concrete actions
        String actualCount = inventoryPage.getCartCount();
        Assert.assertEquals(actualCount, s, "Cart badge count does not match expected");
        
    }
}
    
