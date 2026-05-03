package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanKehadiran;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import java.util.List;

public class LaporanKehadiranTest {

    private LaporanKehadiran laporanKehadiran = Hooks.laporanKehadiran;

    // --- Navigation ---

    @And("the user selects the Kehadiran submenu")
    public void the_user_selects_the_kehadiran_submenu() {
        laporanKehadiran.clickKehadiranSubMenu();
    }

    // --- Search & Date Range ---
    @Then("validasi data tanggal sesuai pencarian pada laporan kehadiran")
        public void validasi_data_tanggal_sesuai_pencarian_pada_laporan_kehadiran() {
        List<String> actualDates = laporanKehadiran.getAllDatesFromTable();
        for (String date : actualDates) {
            Assert.assertTrue(date.contains("2026"), "Data tanggal tidak sesuai pencarian: " + date);
        }
    }

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action---
    @And("user klik export lagi untuk konfirmasi")
    public void user_klik_export_lagi_untuk_confirmation() {
        laporanKehadiran.clickExportButtonConfirmation();
    }
}