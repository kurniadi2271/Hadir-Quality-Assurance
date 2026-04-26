package com.anne.reports_tc01_tc04;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features/reports_tc01_tc04",
        glue = "com.anne.reports_tc01_tc04",
        plugin = {"pretty", "html:target/cucumber-report-reports.html", "json:target/cucumber-reports.json"}
)
public class ReportsTestRunner extends AbstractTestNGCucumberTests {
}

