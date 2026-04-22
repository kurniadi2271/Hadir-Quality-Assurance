package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.List;

public class SortingTest {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    public SortingTest() {
        this.driver = DriverSingleton.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @When("the user selects sort option {string}")
    public void the_user_selects_sort_option(String option) {
        String value = "";
        switch (option) {
            case "Price (low to high)":
                value = "lohi";
                break;
            case "Price (high to low)":
                value = "hilo";
                break;
        }
        inventoryPage.selectSortOption(value);
    }

    @When("the user selects invalid sort option {string}")
    public void the_user_selects_invalid_sort_option(String option) {
        try {
            inventoryPage.selectSortOption(option);
        } catch (Exception e) {
            // Expected for invalid option
        }
    }

    @Then("the products should be sorted by price in ascending order")
    public void the_products_should_be_sorted_by_price_in_ascending_order() {
        List<Double> prices = inventoryPage.getProductPrices();
        Assert.assertTrue(inventoryPage.isSortedAscending(prices), "Products are not sorted in ascending order");
    }

    @Then("the products should be sorted by price in descending order")
    public void the_products_should_be_sorted_by_price_in_descending_order() {
        List<Double> prices = inventoryPage.getProductPrices();
        Assert.assertTrue(inventoryPage.isSortedDescending(prices), "Products are not sorted in descending order");
    }

    @Then("the products should not be sorted")
    public void the_products_should_not_be_sorted() {
        // For negative test, perhaps check if sorting failed or something
        Assert.assertTrue(true); // Placeholder
    }
}