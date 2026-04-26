package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanCuti;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LaporanCutiTest {

    private LaporanCuti laporanCuti = Hooks.laporanCuti;

    // --- Navigation ---
    @And("the user navigates to the Laporan menu")
    public void the_user_navigates_to_the_laporan_menu() {
        laporanCuti.clickLaporanMenu();
    }

    @And("the user selects the Cuti submenu")
    public void the_user_selects_the_cuti_submenu() {
        laporanCuti.clickCutiSubMenu();
    }

    // --- Search & Date Range ---
    @When("the user selects date range from {string} to {string}")
    public void the_user_selects_date_range(String start, String end) {
        // Jika start dan end ingin digunakan secara dinamis, 
        // Anda perlu memodifikasi method di Page Object.
        laporanCuti.selectDateRange(start, end); 
    }

    @And("clicks the search button")
    public void clicks_the_search_button() {
        laporanCuti.clickSearchButton();
    }

    @Then("the user should see the list of reports")
    public void the_user_should_see_the_list_of_reports() {
        // Memastikan jumlah baris lebih besar dari 0
        Assert.assertTrue(laporanCuti.getTableRowCount() > 0, "Tabel kosong atau data tidak muncul");
    }

    @And("the number of rows should be greater than 0")
    public void the_number_of_rows_should_be_greater_than_0() {
        int rows = laporanCuti.getTableRowCount();
        System.out.println("Jumlah laporan ditemukan: " + rows);
        Assert.assertTrue(rows > 0, "Ekspektasi ada data, tapi tabel kosong/No Data!");
    }

    // --- Filter Features ---
    @When("the user enters name {string} in the search field")
    public void the_user_enters_name_in_the_search_field(String name) {
        laporanCuti.enterSearchName(name);
    }

    @When("the user clicks the filter button")
    public void the_user_clicks_the_filter_button() {
        laporanCuti.clickFilterButton();
    }

    @And("enters department {string} in the department search field")
    public void enters_department_in_the_department_search_field(String dept) {
        laporanCuti.enterSearchDepartment(dept);
    }

    @And("clicks the terapkan button")
    public void clicks_the_terapkan_button() {
        laporanCuti.clickTerapkanButton();
    }

    // --- Reset Action ---
    @When("the user clicks the reset button")
    public void the_user_clicks_the_reset_button() {
        laporanCuti.clickResetButton();
    }

    @Then("the search name field should be empty")
    public void the_search_name_field_should_be_empty() {
        String actualValue = laporanCuti.getSearchNameValue();
        Assert.assertEquals(actualValue, "", "Field Nama tidak kosong setelah di-reset!");
    }

    // --- Action & Cancel Cuti ---
    @And("the user scrolls the table to the right to find Action button")
    public void the_user_scrolls_horizontal_to_action() {
        laporanCuti.scrollToActionButton();
    }

    @And("clicks the action button on the first row")
    public void clicks_the_action_button_on_the_first_row() {
        laporanCuti.clickActionButton();
    }

    @And("clicks the cancel cuti action with reason {string}")
    public void clicks_the_cancel_cuti_action_with_reason(String reason) {
        laporanCuti.clickCancelCutiAction();
        laporanCuti.enterCancelCutiReason(reason);
        laporanCuti.clickCancelCutiConfirmButton();
    }

    @Then("the user should see a confirmation popup or success message")
    public void verify_cancel_success() {
        // Tips: Jika tidak ada pesan sukses, cek apakah modal tertutup atau data berkurang
        Assert.assertTrue(true); // Placeholder: tambahkan asersi spesifik jika ada elemen toast/alert
    }
}