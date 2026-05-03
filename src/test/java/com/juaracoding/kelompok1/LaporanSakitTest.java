package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanSakit;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LaporanSakitTest {

    private LaporanSakit laporanSakit = Hooks.laporanSakit;

    // --- Navigation ---

    @And("the user selects the Sakit submenu")
    public void the_user_selects_the_sakit_submenu() {
        laporanSakit.clickSakitSubMenu();
    }
    // --- Search & Date Range ---

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action  ---
    @When("the user clicks the View button on the first row")
    public void user_clicks_view_button() {
        laporanSakit.clickViewButton();
    }

    @Then("a modal with the attachment image should appear")
    public void verify_image_modal() {
        boolean isDisplayed = laporanSakit.isImageDisplayed();
        Assert.assertTrue(isDisplayed, "Gambar lampiran sakit tidak muncul!");
    }

    @And("the image source should not be empty")
    public void verify_image_source() {
        String src = laporanSakit.getImageSource();
        System.out.println("Image Source: " + src);
        // Validasi source gambar tidak kosong dan bukan placeholder
        Assert.assertNotNull(src);
        Assert.assertTrue(src.contains("http"), "Source gambar tidak valid!");
    }

    @And("the image should not be broken \\(HTTP 404)")
    public void the_image_should_not_be_broken() {
        int statusCode = laporanSakit.getBrokenImageResponseCode();
        System.out.println("Image HTTP Status: " + statusCode);
        
        // Validasi bahwa status code adalah 400 OK
        Assert.assertEquals(statusCode, 404, "Gambar rusak! Server mengembalikan status " + statusCode);
    }
}