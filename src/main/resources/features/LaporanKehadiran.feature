@SIT @LaporanKehadiran
Feature: Laporan Kehadiran Functionality

    Background:
        Given User is on the login page
        When User enters valid email and password
        And The user clicks the login button
        Then The user is successfully redirected to the dashboard
        And the user navigates to the Laporan menu
        Then the user selects the Kehadiran submenu

    Scenario: Filter laporan koreksi by department using filter button
        When the user clicks the filter button
        And enters department "Axiata Digital Labs" in the department search field
        And clicks the terapkan button
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then the user should see the list of reports

    Scenario: Filter laporan koreksi by date range successfully
        When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
        And clicks the search button
        Then validasi data tanggal sesuai pencarian pada laporan kehadiran

    Scenario: Filter laporan koreksi by name successfully
        When the user enters name "silva" in the search field
        And clicks the search button
        Then all table data should match the name

    Scenario: Reset search filter
        When the user enters name "Testing Data" in the search field
        When the user clicks the reset button
        Then the search name field should be empty

    Scenario: Filter laporan koreksi by wrong name and verify table is empty
        When the user enters name "Buffon" in the search field
        And clicks the search button
        Then the table should be empty

    # Scenario: Export laporan kehadiran by date range, name, and filter department successfully
    #     When the user selects date range from "1" "April" "2026" to "28" "April" "2026"
    #     When the user enters name "!ikan toman" in the search field
    #     When the user clicks the filter button
    #     And enters department "Axiata Digital Labs" in the department search field
    #     And clicks the terapkan button
    #     And clicks the search button
    #     Then the user should see the list of reports
    #     And user klik tombol export
    #     And user klik export lagi untuk konfirmasi
    #     Then validasi file excel kehadiran harus mengandung data spesifik "!ikan toman", "Axiata Digital Labs", dan "SQA"

    # Scenario: Export laporan kehadiran tapi datanya memang kosong di database
    #     When the user selects date range from "1" "April" "2026" to "2" "April" "2026"
    #     When the user enters name "!ikan toman" in the search field
    #     When the user clicks the filter button
    #     And enters department "Recruitment" in the department search field
    #     And clicks the terapkan button
    #     And clicks the search button
    #     And user klik tombol export
    #     And user klik export lagi untuk konfirmasi
    #     Then file excel tersebut harus kosong atau hanya header

