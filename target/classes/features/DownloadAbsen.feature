@SIT @DownloadAbsen
Feature: Download Absen Functionality

    Background:
        Given User is on the login page
        When User enters valid email and password
        And The user clicks the login button
        Then The user is successfully redirected to the dashboard
        And the user navigates to the Laporan menu
        Then the user selects the Download Absen submenu

    Scenario: Download laporan absen berdasarkan tanggal, nik, name, upliner, divisi, dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And enters name "!ikan toman" in the name input field
        And enters upliner "!ikan Salmon" in the upliner input field
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan tapi datanya memang kosong di database
        When user pilih tanggal dari "1" sampai "2"
        And enters upliner "!ikan toman" in the upliner input field
        And enters unit "Recruitment" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then file excel tersebut harus kosong atau hanya header

    Scenario: Download langsung laporan
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal dan nik
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal dan name
        When user pilih tanggal dari "1" sampai "28"
        And enters name "!ikan toman" in the name input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal dan upliner
        When user pilih tanggal dari "1" sampai "28"
        And enters upliner "!ikan Salmon" in the upliner input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal dan divisi
        When user pilih tanggal dari "1" sampai "28"
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, nik dan name
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And enters name "!ikan toman" in the name input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, nik dan upliner
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And enters upliner "!ikan Salmon" in the upliner input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, nik, dan divisi
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, nik, dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters nik "D6230554" in the nik input field
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, name, dan upliner
        When user pilih tanggal dari "1" sampai "28"
        And enters name "!ikan toman" in the name input field
        And enters upliner "!ikan Salmon" in the upliner input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, name, dan divisi
        When user pilih tanggal dari "1" sampai "28"
        And enters name "!ikan toman" in the name input field
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, name, dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters name "!ikan toman" in the name input field
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, upliner, dan divisi
        When user pilih tanggal dari "1" sampai "28"
        And enters upliner "!ikan Salmon" in the upliner input field
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, upliner, dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters upliner "!ikan Salmon" in the upliner input field
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"

    Scenario: Download laporan absen berdasarkan tanggal, divisi, dan unit
        When user pilih tanggal dari "1" sampai "28"
        And enters divisi "!TestEditBerhasil" in the divisi input field
        And enters unit "Axiata Digital Labs" in the unit input field
        And user klik tombol download
        Then the user should see an alert message "Berhasil generate report"
        Then validasi file excel harus mengandung data spesifik "D6230554", "!IKAN TOMAN", dan "AXIATA DIGITAL LABS"
