Feature: Hadir Login Functionality

    Background:
        Given the user is on the login page

    Scenario: Successful login with valid credentials
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir

    Scenario: Login failure with incorrect credentials
        When the user enters wrong email and password
        And clicks the login button
        Then the user should see an error message 

