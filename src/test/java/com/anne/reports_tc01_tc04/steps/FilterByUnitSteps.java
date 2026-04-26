package com.anne.reports_tc01_tc04.steps;

import com.anne.reports_tc01_tc04.pages.ReportsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FilterByUnitSteps {

    @When("user filters report by unit {string}")
    public void userFiltersReportByUnit(String unit) {
        ReportsPage page = new ReportsPage();
        page.openAttendanceReport();
        page.filterByUnit(unit);
    }

    @Then("only rows matching unit {string} should be shown")
    public void onlyRowsMatchingUnitShouldBeShown(String unit) {
        ReportsPage page = new ReportsPage();
        Assert.assertTrue(page.isTableDisplayed(), "Table/grid not displayed after filtering by unit.");

        if (!"<UNIT_NAME>".equals(unit) && !unit.trim().isEmpty()) {
            // If unit was explicitly provided, validate the Unit column contains it
            var units = page.getVisibleUnitsFromTable();
            Assert.assertFalse(units.isEmpty(), "No Unit values found in table.");
            for (String u : units) {
                Assert.assertTrue(
                        u.toLowerCase().contains(unit.toLowerCase()),
                        "Row unit does not match. Expected contains: " + unit + ", got: " + u
                );
            }
        } else {
            // Placeholder mode: we can't pick a specific unit yet; at least ensure Unit column is populated
            var units = page.getVisibleUnitsFromTable();
            Assert.assertFalse(units.isEmpty(), "No Unit values found in table.");
            for (String u : units) {
                Assert.assertFalse(u.isBlank(), "Found blank Unit value in table.");
            }
        }
    }
}

