Feature: Dashboard Functionality

As an Admin
I want to use dashboard features
So that I can monitor employee data

Scenario: Verify dashboard loaded successfully
Given User login sebagai Admin
Then Halaman Dashboard ditampilkan
And Widget Lembur, Cuti, Koreksi tampil

Scenario: Verify filter by valid date range
Given User berada di halaman Dashboard
When User menginput Start Date "2026-01-01"
And User menginput End Date "2026-04-01"
And User klik tombol Search
Then Data berhasil ditampilkan

Scenario: Verify filter with empty date
Given User berada di halaman Dashboard
When User klik tombol Search
Then Halaman tetap di dashboard

Scenario: Verify reset filter
Given User sudah menerapkan filter
When User klik tombol Reset
Then Data kembali seperti semula

Scenario: Verify menu navigation
Given User login sebagai Admin
When User klik menu "Dashboard"
Then Halaman Dashboard ditampilkan