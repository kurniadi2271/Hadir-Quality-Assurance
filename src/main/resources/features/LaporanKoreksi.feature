@SIT @LaporanKoreksi
Feature: Laporan Koreksi Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Koreksi submenu

    Scenario: Filter laporan koreksi by date range successfully
        When the user selects date range from "1" to "28"
        And clicks the search button
        Then all table data should be within the date range

    Scenario: Filter laporan koreksi by name successfully
        When the user enters name "silva" in the search field
        And clicks the search button
        Then all table data should match the name

    Scenario: Filter laporan koreksi by department using filter button
        When the user clicks the filter button
        And enters department "Recruitment" in the department search field
        And clicks the terapkan button
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

    Scenario: Reject laporan koreksi with reason successfully
        When the user selects date range from "1" to "28"
        When the user enters name "silva" in the search field
        When the user clicks the filter button
        And enters department "Axiata Digital Labs" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports
        And clicks the reject koreksi button with reason "Data tidak valid"
        Then the user should see an alert message "Berhasil menolak permintaan koreksi absen"

    Scenario: Approva laporan koreksi successfully
        When user pilih tanggal dari "1" sampai "28"
        And clicks the search button
        And clicks the approval koreksi button and confirms
        Then the user should see an alert message "Berhasil menyetujui koreksi absen"

