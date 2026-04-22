Feature: Add to Cart Functionality
    Scenario: Add a product to the cart
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user adds "Sauce Labs Backpack" to cart
        Then the cart badge should show "1"
        And the user clicks the reset app state button
        And the user clicks the logout button

    Scenario: Add multiple products to the cart
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user clicks all add to cart buttons
        Then the cart badge should show "6"
        And the user clicks the reset app state button
        And the user clicks the logout button

    Scenario: Negative test - Add non-existent product to cart
        Given the user is on the login page
        When the user enters valid username and password
        And clicks the login button
        When the user tries to add "Non-existent Product" to cart
        Then the cart badge should remain unchanged
        And the user clicks the logout button