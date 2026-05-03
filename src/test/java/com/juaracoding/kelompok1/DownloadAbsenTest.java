package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.DownloadAbsen;
import com.juaracoding.kelompok1.utils.Hooks;
import com.juaracoding.kelompok1.utils.ExcelReader; // Class helper yang kita buat tadi
import io.cucumber.java.en.*;
import org.testng.Assert;

public class DownloadAbsenTest {

    private DownloadAbsen downloadAbsen = Hooks.downloadAbsen;
    private String downloadFolder = System.getProperty("user.home") + "/Downloads";

    @Then("the user selects the Download Absen submenu")
    public void the_user_selects_the_download_absen_submenu() {
        downloadAbsen.clickDownloadAbsenSubMenu();
    }

    @When("user pilih tanggal dari {string} sampai {string}")
    public void user_pilih_tanggal(String start, String end) {
        downloadAbsen.selectDateRange(start, end);
    }

    @And("enters nik {string} in the nik input field")
    public void enters_nik_in_the_nik_input_field(String nik) {
        downloadAbsen.enterSearchNik(nik);
    }

    @And("enters name {string} in the name input field")
    public void enters_name_in_the_name_input_field(String name) {
        downloadAbsen.enterSearchName(name);
    }

    @And("enters upliner {string} in the upliner input field")
    public void enters_upliner_in_the_upliner_input_field(String upliner) {
        downloadAbsen.enterSearchUpliner(upliner);
    }

    @And("enters divisi {string} in the divisi input field")
    public void enters_divisi_in_the_divisi_input_field(String divisi) {
        downloadAbsen.enterSearchDivisi(divisi);
    }

    @And("enters unit {string} in the unit input field")
    public void enters_unit_in_the_unit_input_field(String unit) {
        downloadAbsen.enterSearchUnit(unit);
    }

    @And("user klik tombol download")
    public void user_klik_tombol_download() {
        downloadAbsen.clickDownloadButton();
        // Kasih jeda buat proses download file
    }

    @Then("validasi file excel harus mengandung data spesifik {string}, {string}, dan {string}")
    public void validasi_file_excel_spesifik(String NIK, String Nama, String Unit) {
        String latestFile = ExcelReader.getLatestDownloadFile(downloadFolder);
        Assert.assertNotNull(latestFile, "File gak ketemu di folder Download!");

        // Kita masukin semua kriteria ke dalam Array
        String[] criteria = {NIK, Nama, Unit};
        
        boolean isMatch = ExcelReader.verifyRowData(latestFile, criteria);
        
        Assert.assertTrue(isMatch, 
            String.format("Data Gagal! Baris dengan NIK: %s, Nama: %s, dan Unit: %s tidak ditemukan dalam satu baris.", 
            NIK, Nama, Unit));
        
        System.out.println("Validasi Sukses: Data ditemukan dalam satu baris yang sama.");
    }

    @And("file excel tersebut harus kosong atau hanya header")
    public void verify_empty_excel() {
        String latestFile = ExcelReader.getLatestDownloadFile(downloadFolder);
        int rows = ExcelReader.getRowCount(latestFile);
        
        // Biasanya row 1 itu header. Jadi kalau <= 1 berarti gak ada datanya.
        Assert.assertTrue(rows <= 2, "Excelnya ada isinya bruh, padahal harusnya kosong! Total baris: " + rows);
    }
}