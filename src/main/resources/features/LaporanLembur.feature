Feature: Laporan Lembur Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Lembur submenu

    Scenario: Export laporan lembur by date range, name, and filter department successfully
        When the user selects date range from "1" to "28"
        When the user enters name "silva" in the search field
        When the user clicks the filter button
        And enters department "Axiata Digital Labs" in the department search field
        And clicks the terapkan button
        And clicks the search button
        Then the user should see the list of reports
        And the number of rows should be greater than 0
        And user klik tombol export
        Then validasi file excel harus mengandung data spesifik "Silva", "Axiata Digital Labs", dan "ikrar"

    Scenario: Export laporan lembur tapi datanya memang kosong di database
        When user pilih tanggal dari "1" sampai "2"
        When the user enters name "silva" in the search field
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

