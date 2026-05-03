package com.juaracoding.kelompok1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources/features",
            glue = "com.juaracoding.kelompok1",
            plugin = {"pretty",
                    "json:target/cucumber.json",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends AbstractTestNGCucumberTests {
}