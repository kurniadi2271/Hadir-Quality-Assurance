package com.anne.reports_tc01_tc04.steps;

import com.anne.reports_tc01_tc04.pages.LoginPage;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public class AuthSteps {

    @Given("user is logged in as admin")
    public void userIsLoggedInAsAdmin() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed or still on login page.");
    }
}

