@SIT @SemuaLaporan
Feature: Reports - TC_REP_001 to TC_REP_004

  Scenario: TC_REP_001 - Menampilkan semua laporan
    Given user is logged in as admin
    When user opens Reports page
    Then reports table should be displayed

  Scenario: TC_REP_002 - Search by Name
    Given user is logged in as admin
    When user opens Reports page
    And user searches report by name "mafira"
    Then only rows matching name "mafira" should be shown

  Scenario: TC_REP_003 - Search by Date Range
    Given user is logged in as admin
    When user opens Reports page
    And user searches report by date range "01-04-2026" to "22-04-2026"
    Then only rows within date range "01-04-2026" to "22-04-2026" should be shown

  Scenario: TC_REP_004 - Filter by Unit
    Given user is logged in as admin
    When user opens Reports page
    And user filters report by unit "<UNIT_NAME>"
    Then only rows matching unit "<UNIT_NAME>" should be shown
