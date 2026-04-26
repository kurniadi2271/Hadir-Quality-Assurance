package com.anne.reports_tc01_tc04.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchByNameSteps {

    @When("user searches report by name {string}")
    public void userSearchesReportByName(String name) {
        // TODO: fill name input then click Cari
    }

    @Then("only rows matching name {string} should be shown")
    public void onlyRowsMatchingNameShouldBeShown(String name) {
        // TODO: assert each row contains the expected name
    }
}

