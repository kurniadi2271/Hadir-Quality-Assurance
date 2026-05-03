package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.pages.LaporanIzinPulangCepat;
import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.And;

public class LaporanIzinPulangCepatTest {

    private LaporanIzinPulangCepat laporanIzinPulangCepat = Hooks.laporanIzinPulangCepat;

    // --- Navigation ---

    @And("the user selects the Izin Pulang Cepat submenu")
    public void the_user_selects_the_izin_pulang_cepat_submenu() {
        laporanIzinPulangCepat.clickIzinPulangCepatSubMenu();
    }

    // --- Search & Date Range ---

    // --- Filter Features ---

    // --- Reset Action ---

    // --- Action---

}