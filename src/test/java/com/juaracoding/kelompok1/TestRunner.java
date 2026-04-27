package com.juaracoding.kelompok1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources/features/LaporanKoreksi.feature",
            glue = "com.juaracoding.kelompok1",
            plugin = {"pretty","html:target/cucumber-report.html","json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
