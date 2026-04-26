Feature: Reports - TC_REP_004 Filter by Unit
  Scenario: TC_REP_004 - Filter by Unit
    Given user is logged in as admin
    When user opens Reports page
    And user filters report by unit "<UNIT_NAME>"
    Then only rows matching unit "<UNIT_NAME>" should be shown

