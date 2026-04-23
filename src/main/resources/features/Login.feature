Feature: Hadir Login Functionality
    Scenario: Successful login with valid credentials
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user clicks the logout button
        Then the user should be redirected back to the login page
    
    Scenario: Login failure with incorrect credentials
        Given the user is on the login page
        When the user enters wrong email and password
        And clicks the login button
        Then the user should see an error message

