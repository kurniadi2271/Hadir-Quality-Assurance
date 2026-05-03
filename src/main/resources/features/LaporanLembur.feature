@SIT @LaporanLembur
Feature: Laporan Lembur Functionality

    Background:
        Given User is on the login page
        When User enters valid email and password
        And The user clicks the login button
        Then The user is successfully redirected to the dashboard
        And the user navigates to the Laporan menu
        Then the user selects the Lembur submenu

    Scenario: Filter laporan lembur by date range successfully
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then all table data should be within the date range

    Scenario: Filter laporan lembur by name successfully
        When the user enters name "silva" in the search field
        And clicks the search button
        Then all table data should match the name

    Scenario: Filter laporan lembur by department using filter button
        When the user clicks the filter button
        And enters department "Recruitment" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Reset search filter
        When the user enters name "Testing Data" in the search field
        When the user clicks the reset button
        Then the search name field should be empty

    Scenario: Filter laporan lembur by wrong name and verify table is empty
        When the user enters name "Buffon" in the search field
        And clicks the search button
        Then the table should be empty

    Scenario: Export laporan lembur by date range, name, and filter department successfully
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        When the user enters name "!ikan toman" in the search field
        When the user clicks the filter button
        And enters department "Axiata Digital Labs" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports
        And user klik tombol export
        Then validasi file excel harus mengandung data spesifik "!IKAN TOMAN", "Axiata Digital Labs", dan "Apr"

    Scenario: Export laporan lembur tapi datanya memang kosong di database
        When the user selects date range from "1" "April" "2026" to "2" "April" "2026"
        When the user enters name "!ikan toman" in the search field
        When the user clicks the filter button
        And enters department "Recruitment" in the department search field
        And clicks the terapkan button
        And clicks the search button
        And user klik tombol export
        Then file excel tersebut harus kosong atau hanya header

    Scenario: Export laporan lembur tapi tanpa tanggal
        When the user enters name "silva" in the search field
        When the user clicks the filter button
        And enters department "Recruitment" in the department search field
        And clicks the terapkan button
        And clicks the search button
        And user klik tombol export
        Then the user should see an alert message "Harap isi tanggal terlebih dahulu"