package com.anne.reports_tc01_tc04.hooks;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import com.juaracoding.kelompok1.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebHooks {

    @Before
    public void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
    }

    @After
    public void tearDown() {
        DriverSingleton.closeObjectInstance();
    }
}

