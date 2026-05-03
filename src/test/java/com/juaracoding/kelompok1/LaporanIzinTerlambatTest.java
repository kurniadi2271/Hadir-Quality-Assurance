package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanIzinTerlambat;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.And;

public class LaporanIzinTerlambatTest {

    private LaporanIzinTerlambat laporanIzinTerlambat = Hooks.laporanIzinTerlambat;

    // --- Navigation ---

    @And("the user selects the Izin Terlambat submenu")
    public void the_user_selects_the_izin_terlambat_submenu() {
        laporanIzinTerlambat.clickIzinTerlambatSubMenu();
    }

    // --- Search & Date Range ---

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action---

}