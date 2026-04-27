Feature: Download Absen Functionality

    Background:
        Given the user is on the login page
        When the user enters valid email and password
        And clicks the login button
        Then the user should be redirected to the dashboard page
        And the page header should display Logo Hadir
        And the user navigates to the Laporan menu
        Then the user selects the Download Absen submenu

    Scenario: Download laporan absen berdasarkan tanggal dan tipe
        When user pilih tanggal dari "1" sampai "27"
        And enters nik "D7231954" in the nik input field
        And enters name "Gilbert" in the name input field
        And enters upliner "Gilbert" in the upliner input field
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And enters unit "Recruitment" in the unit input field
        And user klik tombol download
        Then validasi file excel harus mengandung data spesifik "GILBERT", "Recruitment", dan "Gilbert"

    Scenario: Download laporan tapi datanya memang kosong di database
        When user pilih tanggal dari "1" sampai "2"
        And enters upliner "Silva" in the upliner input field
        And enters divisi "123456" in the divisi input field
        And user klik tombol download
        Then file excel tersebut harus kosong atau hanya header

