Feature: Laporan Koreksi Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Koreksi submenu

    Scenario: Reject laporan koreksi with reason successfully
        When the user selects date range from "1" to "28"
        When the user enters name "silva" in the search field
        When the user clicks the filter button
        And enters department "Axiata Digital Labs" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports
        And the number of rows should be greater than 0
        And clicks the reject koreksi button with reason "Data tidak valid"
        Then the user should see an alert message "Berhasil menolak permintaan koreksi absen"

    Scenario: Approva laporan koreksi successfully
        When user pilih tanggal dari "1" sampai "28"
        And clicks the search button
        And clicks the approval koreksi button and confirms
        Then the user should see an alert message "Berhasil menyetujui koreksi absen"

