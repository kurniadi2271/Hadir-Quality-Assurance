Feature: Hadir Dashboard Functionality
  As an Admin
  I want to use dashboard features
  So that I can monitor employee data accurately

Scenario: Verify dashboard widgets visibility
  Given User login sebagai Admin
  When User membuka halaman Dashboard
  Then Widget Lembur, Cuti, Koreksi tampil
  And Setiap widget menampilkan angka statistik yang valid
  And Data ditampilkan berdasarkan range awal bulan sampai hari ini

Scenario: Verify filter by valid date range
  Given User berada di halaman Dashboard
  When User menginput Start Date "2026-01-01"
  And User menginput End Date "2026-04-01"
  And User klik tombol Search
  Then Data yang ditampilkan hanya dalam rentang "01/01/2026" sampai "01/04/2026"

Scenario: Verify filter with empty date input
  Given User berada di halaman Dashboard
  When User tidak menginput Start Date dan End Date
  And User klik tombol Search
  Then Sistem menampilkan validasi atau menggunakan default date range

Scenario: Verify filter by valid unit
  Given User berada di halaman Dashboard
  When User klik tombol Filter
  And User memilih Unit "111"
  And User klik tombol Terapkan
  Then Data yang ditampilkan hanya berasal dari Unit "111"

Scenario: Verify reset filter functionality
  Given User sudah menerapkan filter
  When User klik tombol Reset
  Then Semua filter kembali ke default
  And Data kembali seperti kondisi awal

Scenario: Verify reset without applying filter
  Given User berada di halaman Dashboard tanpa filter
  When User klik tombol Reset
  Then Tidak ada perubahan data
  And Sistem tidak menampilkan error

Scenario: Verify Dashboard menu navigation
  Given User login sebagai Admin
  When User klik menu "Dashboard"
  Then Halaman Dashboard ditampilkan
  And Semua widget tampil dengan data valid

Scenario: Verify Pending menu navigation with data condition
  Given User login sebagai Admin
  When User klik menu "Pending"
  Then Halaman Pending ditampilkan
  And Data pending ditampilkan jika tersedia
  And Sistem menampilkan empty state jika tidak ada data

Scenario: Verify redirect to detail page when clicking employee name
  Given User berada di halaman Dashboard
  When User klik nama karyawan "Hadir SQA Testing 2"
  Then User diarahkan ke halaman detail karyawan
  And Nama karyawan sesuai dengan yang diklik
  And Data detail ditampilkan dengan benar

Scenario: Verify error when detail page fails to load
  Given User berada di halaman Dashboard
  When User klik nama karyawan
  And Data gagal dimuat
  Then Sistem menampilkan error message