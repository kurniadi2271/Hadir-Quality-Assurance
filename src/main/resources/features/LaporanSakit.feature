Feature: Laporan Cuti Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Sakit submenu

    Scenario: Filter laporan sakit by date range successfully
        When the user selects date range from "1" to "28"
        And clicks the search button
        Then the user should see the list of reports
        And the number of rows should be greater than 0

    Scenario: Filter laporan sakit by name successfully
        When the user enters name "silva" in the search field
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Filter laporan sakit by department using filter button
        When the user clicks the filter button
        And enters department "Unit 123" in the department search field
        And clicks the terapkan button

    Scenario: Reset search filter
        When the user enters name "Testing Data" in the search field
        When the user clicks the reset button
        Then the search name field should be empty

    Scenario: Validate sick leave attachment image
        When the user clicks the View button on the first row
        Then a modal with the attachment image should appear
        And the image source should not be empty

