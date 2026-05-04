package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanKehadiran;
import com.juaracoding.kelompok1.utils.ExcelReader;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import java.util.List;

public class LaporanKehadiranTest {

    private LaporanKehadiran laporanKehadiran = Hooks.laporanKehadiran;
    private String downloadFolder = System.getProperty("user.home") + "/Downloads";

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

    @Then("validasi file excel kehadiran harus mengandung data spesifik {string}, {string}, dan {string}")
    public void validasi_file_excel_kehadiran_spesifik(String Nama, String Unit, String Jabatan) {
        String latestFile = ExcelReader.getLatestDownloadFile(downloadFolder);
        Assert.assertNotNull(latestFile, "File gak ketemu di folder Download!");

        // Kita masukin semua kriteria ke dalam Array
        String[] criteria = {Nama, Unit, Jabatan};
        
        boolean isMatch = ExcelReader.verifyRowData(latestFile, criteria);
        
        Assert.assertTrue(isMatch, 
            String.format("Data Gagal! Baris dengan Nama Karyawan: %s, Unit: %s, dan Jabatan: %s tidak ditemukan dalam satu baris.", 
            Nama, Unit, Jabatan));
        
        System.out.println("Validasi Sukses: Data ditemukan dalam satu baris yang sama.");
    }
}