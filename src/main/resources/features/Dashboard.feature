@SIT @Dashboard
Feature: Dashboard Functionality

  Background:
    Given User is on the login page
    When User enters valid email and password
    And The user clicks the login button
    Then The user is successfully redirected to the dashboard

  Scenario: Verify dashboard loaded successfully
    # Background sudah menangani login dan verifikasi dashboard awal
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
    When User klik menu "Dashboard"
    Then Halaman Dashboard ditampilkan