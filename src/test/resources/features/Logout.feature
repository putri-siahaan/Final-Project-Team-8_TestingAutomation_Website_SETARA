Feature: Logout Functionality

  @TC_WA_081 @SuccessLogout
  Scenario: [TC_WA_081] Verifikasi berhasil keluar dari website
    Given user is logged in and on the SETARA dashboard
    When user clicks the Logout menu
    And user selects "Iya" on the Logout Confirmation
    Then user should be successfully logged out of the website

  @TC_WA_082 @StayInHomepage
  Scenario: [TC_WA_082] Verifikasi batal keluar dari website
    Given user is logged in and on the SETARA dashboard
    When user clicks the Logout menu
    And user selects "Tidak" on the Logout Confirmation
    Then user should remain on the SETARA dashboard