Feature: Reports - TC_REP_003 Search by Date Range
  Scenario: TC_REP_003 - Search by Date Range
    Given user is logged in as admin
    When user opens Reports page
    And user searches report by date range "01-04-2026" to "22-04-2026"
    Then only rows within date range "01-04-2026" to "22-04-2026" should be shown

