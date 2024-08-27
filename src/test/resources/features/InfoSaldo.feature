@Balance
Feature: Info Saldo

  As a user
  I want to view my balance
  so that I can check the amount available in my account.


  @TC_WA_008 @Case_Positive_ViewBalance_Homepage
  Scenario: [TC_WA_008 @Case_Positive_ViewBalance_Homepage] Verify successful display of balance on the homepage
    Given user has successfully logged in and is on the homepage of SETARA website
    When user click icon show balance
    Then the user should see their account balance displayed on the homepage

  @TC_WA_009 @Case_Positive_ViewBalance_DetailFromMenu
  Scenario: [TC_WA_009 @Case_Positive_ViewBalance_DetailFromMenu] Verify successful display of detailed balance from the "Info Saldo" menu
    Given user has successfully logged in and is on the homepage of SETARA website
    When user click Info Saldo menu
    And user get text "Info Saldo"
    Then user should see a detailed balance
