package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanLembur;
import com.juaracoding.kelompok1.utils.ExcelReader;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LaporanLemburTest {

    private LaporanLembur laporanLembur = Hooks.laporanLembur;
    private String downloadFolder = System.getProperty("user.home") + "/Downloads";

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
        try {
            Thread.sleep(3000); // 3 detik, bisa disesuaikan
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the user should see an alert message {string}")
    public void the_user_should_see_an_alert_message(String expectedMessage) {
        // Kirim expectedMessage ke method agar dicari XPath-nya secara spesifik
        String actualMessage = laporanLembur.getRequiredErrorMessage(expectedMessage); 
        
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            String.format("Ekspektasi: '%s', tapi tidak ditemukan di halaman!", expectedMessage));
    }

    @Then("validasi file excel lembur harus mengandung data spesifik {string}, {string}, dan {string}")
    public void validasi_file_excel_lembur_spesifik(String Nama, String Unit, String Tanggal) {
        String latestFile = ExcelReader.getLatestDownloadFile(downloadFolder);
        Assert.assertNotNull(latestFile, "File gak ketemu di folder Download!");

        // Kita masukin semua kriteria ke dalam Array
        String[] criteria = {Nama, Unit, Tanggal};
        
        boolean isMatch = ExcelReader.verifyRowData(latestFile, criteria);
        
        Assert.assertTrue(isMatch, 
            String.format("Data Gagal! Baris dengan Nama Karyawan: %s, Unit: %s, dan Tanggal Pengajuan: %s tidak ditemukan dalam satu baris.", 
            Nama, Unit, Tanggal));
        
        System.out.println("Validasi Sukses: Data ditemukan dalam satu baris yang sama.");
    }
}