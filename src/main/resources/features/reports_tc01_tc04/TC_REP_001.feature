Feature: Reports - TC_REP_001 Menampilkan semua laporan
  Scenario: TC_REP_001 - Menampilkan semua laporan
    Given user is logged in as admin
    When user opens Reports page
    Then reports table should be displayed

