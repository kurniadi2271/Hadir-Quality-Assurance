package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanLembur;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LaporanLemburTest {

    private LaporanLembur laporanLembur = Hooks.laporanLembur;

    // --- Navigation ---

    @And("the user selects the Lembur submenu")
    public void the_user_selects_the_lembur_submenu() {
        laporanLembur.clickLemburSubMenu();
    }

    // --- Search & Date Range ---

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action---
    @And("user klik tombol export")
    public void user_klik_tombol_export() {
        laporanLembur.clickExportButton();
        // Kasih jeda buat proses download file
    }

    @Then("the user should see an alert message {string}")
    public void the_user_should_see_an_alert_message(String expectedMessage) {
        // Kirim expectedMessage ke method agar dicari XPath-nya secara spesifik
        String actualMessage = laporanLembur.getRequiredErrorMessage(expectedMessage); 
        
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            String.format("Ekspektasi: '%s', tapi tidak ditemukan di halaman!", expectedMessage));
    }
}