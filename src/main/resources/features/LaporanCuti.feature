@SIT @LaporanCuti
Feature: Laporan Cuti Functionality

    Background:
        Given User is on the login page
        When User enters valid email and password
        And The user clicks the login button
        Then The user is successfully redirected to the dashboard
        And the user navigates to the Laporan menu
        Then the user selects the Cuti submenu

    Scenario: Filter laporan cuti by date range successfully
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then all table data should be within the date range

    Scenario: Filter laporan cuti by name successfully
        When the user enters name "silva" in the search field
        And clicks the search button
        Then all table data should match the name

    Scenario: Filter laporan cuti by department using filter button
        When the user clicks the filter button
        And enters department "Recruitment" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Reset search filter
        When the user enters name "Testing Data" in the search field
        When the user clicks the reset button
        Then the search name field should be empty

    Scenario: Filter laporan cuti by wrong name and verify table is empty
        When the user enters name "Buffon" in the search field
        And clicks the search button
        Then the table should be empty

    Scenario: Cancel a cuti request successfully
        When the user enters name "Aditya" in the search field
        And clicks the search button
        And the user scrolls the table to the right to find Action button
        And clicks the action button on the first row
        And clicks the cancel cuti action with reason "Tidak jadi cuti"
