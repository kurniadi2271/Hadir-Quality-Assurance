@SIT @LaporanIzinPulangCepat
Feature: Laporan Izin Pulang Cepat Functionality

    Background:
        Given User is on the login page
        When User enters valid email and password
        And The user clicks the login button
        Then The user is successfully redirected to the dashboard
        And the user navigates to the Laporan menu
        Then the user selects the Izin Pulang Cepat submenu

    Scenario: Filter laporan koreksi by department using filter button
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        When the user clicks the filter button
        And enters department "Sysmex" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Filter laporan koreksi by date range successfully
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Filter laporan koreksi by name successfully
        When the user enters name "mafira" in the search field
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Reset search filter
        When the user enters name "Testing Data" in the search field
        When the user clicks the reset button
        Then the search name field should be empty

    Scenario: Filter laporan koreksi by wrong name and verify table is empty
        When the user enters name "Buffon" in the search field
        And clicks the search button
        Then the table should be empty

    