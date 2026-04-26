Feature: Reports - TC_REP_002 Search by Name
  Scenario: TC_REP_002 - Search by Name
    Given user is logged in as admin
    When user opens Reports page
    And user searches report by name "Silva"
    Then only rows matching name "Silva" should be shown

