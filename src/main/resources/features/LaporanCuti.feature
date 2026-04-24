Feature: Laporan Cuti Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Cuti submenu

    Scenario: Filter laporan cuti by date range successfully
        When the user selects date range from "1" to "22"
        And clicks the search button
        Then the user should see the list of cuti reports
        And the number of rows should be greater than 0
