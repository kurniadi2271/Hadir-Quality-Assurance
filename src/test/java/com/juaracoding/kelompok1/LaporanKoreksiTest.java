package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanKoreksi;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.*;

public class LaporanKoreksiTest {

    private LaporanKoreksi laporanKoreksi = Hooks.laporanKoreksi;

    // --- Navigation ---

    @And("the user selects the Koreksi submenu")
    public void the_user_selects_the_koreksi_submenu() {
        laporanKoreksi.clickKoreksiSubMenu();
    }

    // --- Search & Date Range ---

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action---
    @And("clicks the approval koreksi button and confirms")
    public void clicks_the_approval_koreksi_button_and_confirms() {
        laporanKoreksi.clickApprovalButton();
        laporanKoreksi.clickApprovalConfirmButton();
    }

    @And("clicks the reject koreksi button with reason {string}")
    public void clicks_the_reject_koreksi_button_with_reason(String reason) {
        laporanKoreksi.clickRejectButton();
        laporanKoreksi.enterRejectReason(reason);
        laporanKoreksi.clickRejectReasonConfirmButton();
    }

}