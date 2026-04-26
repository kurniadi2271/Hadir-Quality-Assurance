package com.anne.reports_tc01_tc04.steps;

import com.anne.reports_tc01_tc04.pages.ReportsPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ReportsAssertionsSteps {

    @Then("reports table should be displayed")
    public void reportsTableShouldBeDisplayed() {
        Assert.assertTrue(new ReportsPage().isTableDisplayed(), "Reports table was not displayed.");
    }
}

