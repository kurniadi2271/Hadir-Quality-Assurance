Feature: Product Sorting Functionality
    Scenario: Sort products by price low to high
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user selects sort option "Price (low to high)"
        Then the products should be sorted by price in ascending order
        And the user clicks the reset app state button
        And the user clicks the logout button

    Scenario: Sort products by price high to low
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user selects sort option "Price (high to low)"
        Then the products should be sorted by price in descending order
        And the user clicks the reset app state button
        And the user clicks the logout button

    Scenario: Negative test - Invalid sort option
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user selects invalid sort option "invalid"
        Then the products should not be sorted
        And the user clicks the reset app state button
        And the user clicks the logout button