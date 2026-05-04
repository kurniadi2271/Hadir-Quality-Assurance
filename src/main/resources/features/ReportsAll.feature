@SIT @SemuaLaporan
Feature: Reports - TC_REP_001 to TC_REP_004

  Background:
    Given User is on the login page
    When User enters valid email and password
    And The user clicks the login button
    Then The user is successfully redirected to the dashboard

  Scenario: TC_REP_001 - Menampilkan semua laporan
    When user opens Reports page
    Then reports table should be displayed

  Scenario: TC_REP_002 - Search by Name
    When user opens Reports page
    And user searches report by name "mafira"
    Then only rows matching name "mafira" should be shown

  Scenario: TC_REP_003 - Search by Date Range
    When user opens Reports page
    And user searches report by date range "01-04-2026" to "22-04-2026"
    Then only rows within date range "01-04-2026" to "22-04-2026" should be shown

  Scenario: TC_REP_004 - Filter by Unit
    When user opens Reports page
    And user filters report by unit "<UNIT_NAME>"
    Then only rows matching unit "<UNIT_NAME>" should be shown
