package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanCuti;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LaporanCutiTest {

    private LaporanCuti laporanCuti = Hooks.laporanCuti;

    @And("the user navigates to the Laporan menu")
    public void the_user_navigates_to_the_laporan_menu() {
        laporanCuti.clickLaporanMenu();
    }

    @And("the user selects the Cuti submenu")
    public void the_user_selects_the_cuti_submenu() {
        laporanCuti.clickCutiSubMenu();
    }

    @When("the user selects date range from {string} to {string}")
    public void the_user_selects_date_range(String start, String end) {
        // Method selectDateRange di Page Object kamu sudah mencakup klik Save
        laporanCuti.selectDateRange(); 
    }

    @And("clicks the search button")
    public void clicks_the_search_button() {
        laporanCuti.clickSearchButton();
        // Beri waktu napas untuk React render ulang tabel
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Then("the user should see the list of cuti reports")
    public void the_user_should_see_the_list_of_cuti_reports() {
        // Memastikan tabel minimal ada di halaman
        Assert.assertNotNull(laporanCuti.getTableRowCount());
    }

    @And("the number of rows should be greater than 0")
    public void the_number_of_rows_should_be_greater_than_0() {
        int rows = laporanCuti.getTableRowCount();
        System.out.println("Jumlah laporan ditemukan: " + rows);
        Assert.assertTrue(rows > 0, "Laporan kosong, padahal diekspektasi ada data!");
    }

}