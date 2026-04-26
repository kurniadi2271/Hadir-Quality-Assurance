package com.anne.reports_tc01_tc04.steps;

import com.anne.reports_tc01_tc04.pages.ReportsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchByDateRangeSteps {

    @When("user searches report by date range {string} to {string}")
    public void userSearchesReportByDateRange(String from, String to) {
        new ReportsPage().searchByDateRange(from, to);
    }

    @Then("only rows within date range {string} to {string} should be shown")
    public void onlyRowsWithinDateRangeShouldBeShown(String from, String to) {
        LocalDate start = parse(from);
        LocalDate end = parse(to);
        Assert.assertNotNull(start, "Cannot parse start date: " + from);
        Assert.assertNotNull(end, "Cannot parse end date: " + to);

        ReportsPage page = new ReportsPage();

        // On this dashboard page, tables show aggregates (Nama/Total) and the chosen range is in the date inputs.
        String startVal = page.getStartDateValue();
        String endVal = page.getEndDateValue();
        Assert.assertNotNull(startVal, "Start Date input value not found.");
        Assert.assertNotNull(endVal, "End Date input value not found.");

        String expectedStart = start.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String expectedEnd = end.format(DateTimeFormatter.ISO_LOCAL_DATE);

        Assert.assertTrue(startVal.contains(expectedStart), "Start Date not applied. Expected contains: " + expectedStart + ", got: " + startVal);
        Assert.assertTrue(endVal.contains(expectedEnd), "End Date not applied. Expected contains: " + expectedEnd + ", got: " + endVal);

        Assert.assertTrue(page.isTableDisplayed(), "Dashboard tables/grids are not displayed after date search.");
    }

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

