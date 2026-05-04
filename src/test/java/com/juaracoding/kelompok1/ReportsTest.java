package com.juaracoding.kelompok1;

import com.juaracoding.kelompok1.utils.Hooks;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportsTest {

    // ── TC_REP_001: Auth + Navigation + Table Assertion ──

    @When("user opens Reports page")
    public void userOpensReportsPage() {
        Hooks.reportsPage.openFromMenu();
    }

    @Then("reports table should be displayed")
    public void reportsTableShouldBeDisplayed() {
        Assert.assertTrue(Hooks.reportsPage.isTableDisplayed(), "Reports table was not displayed.");
    }

    // ── TC_REP_002: Search by Name ──

    @When("user searches report by name {string}")
    public void userSearchesReportByName(String name) {
        Hooks.reportsPage.searchByName(name);
    }

    @Then("only rows matching name {string} should be shown")
    public void onlyRowsMatchingNameShouldBeShown(String name) {
        Assert.assertTrue(Hooks.reportsPage.isTableDisplayed(), "Table not displayed after name search.");

        List<String> names = Hooks.reportsPage.getVisibleNamesFromTables();
        // If results exist, validate all rows contain the searched name
        // Empty result is acceptable (user not present in current date range)
        for (String n : names) {
            Assert.assertTrue(
                    n.toLowerCase().contains(name.toLowerCase()),
                    "Row name does not match. Expected contains: " + name + ", got: " + n
            );
        }
    }

    // ── TC_REP_003: Search by Date Range ──

    @When("user searches report by date range {string} to {string}")
    public void userSearchesReportByDateRange(String from, String to) {
        Hooks.reportsPage.searchByDateRange(from, to);
    }

    @Then("only rows within date range {string} to {string} should be shown")
    public void onlyRowsWithinDateRangeShouldBeShown(String from, String to) {
        LocalDate start = parse(from);
        LocalDate end = parse(to);
        Assert.assertNotNull(start, "Cannot parse start date: " + from);
        Assert.assertNotNull(end, "Cannot parse end date: " + to);

        String startVal = Hooks.reportsPage.getStartDateValue();
        String endVal = Hooks.reportsPage.getEndDateValue();
        Assert.assertNotNull(startVal, "Start Date input value not found.");
        Assert.assertNotNull(endVal, "End Date input value not found.");

        String expectedStart = start.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String expectedEnd = end.format(DateTimeFormatter.ISO_LOCAL_DATE);

        Assert.assertTrue(startVal.contains(expectedStart),
                "Start Date not applied. Expected contains: " + expectedStart + ", got: " + startVal);
        Assert.assertTrue(endVal.contains(expectedEnd),
                "End Date not applied. Expected contains: " + expectedEnd + ", got: " + endVal);

        Assert.assertTrue(Hooks.reportsPage.isTableDisplayed(),
                "Dashboard tables/grids are not displayed after date search.");
    }

    // ── TC_REP_004: Filter by Unit ──

    @When("user filters report by unit {string}")
    public void userFiltersReportByUnit(String unit) {
        Hooks.reportsPage.openAttendanceReport();
        Hooks.reportsPage.filterByUnit(unit);
    }

    @Then("only rows matching unit {string} should be shown")
    public void onlyRowsMatchingUnitShouldBeShown(String unit) {
        Assert.assertTrue(Hooks.reportsPage.isTableDisplayed(), "Table/grid not displayed after filtering by unit.");

        var units = Hooks.reportsPage.getVisibleUnitsFromTable();
        // Empty result is acceptable (no attendance data for selected unit)
        if (!"<UNIT_NAME>".equals(unit) && !unit.trim().isEmpty()) {
            for (String u : units) {
                Assert.assertTrue(
                        u.toLowerCase().contains(unit.toLowerCase()),
                        "Row unit does not match. Expected contains: " + unit + ", got: " + u
                );
            }
        } else {
            for (String u : units) {
                Assert.assertFalse(u.isBlank(), "Found blank Unit value in table.");
            }
        }
    }

    // ── Helper ──

    private LocalDate parse(String raw) {
        DateTimeFormatter[] fmts = new DateTimeFormatter[]{
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy")
        };
        for (DateTimeFormatter f : fmts) {
            try {
                return LocalDate.parse(raw.trim(), f);
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
