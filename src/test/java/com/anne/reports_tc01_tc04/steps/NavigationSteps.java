package com.anne.reports_tc01_tc04.steps;

import com.anne.reports_tc01_tc04.pages.ReportsPage;
import io.cucumber.java.en.When;

public class NavigationSteps {

    @When("user opens Reports page")
    public void userOpensReportsPage() {
        new ReportsPage().openFromMenu();
    }
}

