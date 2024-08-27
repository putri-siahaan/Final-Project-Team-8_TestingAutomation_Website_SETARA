@Login
Feature: Login

  As a user
  I want to login
  so that i can access the main dashboard and perform BCA transactions.


  @Case_Positive_Login
  Scenario: [TC_WA_001 @Case_Positive_Login] Verify success login using valid username id and valid password
    Given user already on the login page of SETARA website
    When user input valid username id
    And user input valid password
    And user click icon show password to see password is correct or not
    And user click Masuk button
    Then validate success login and i am on the dashboard Destimate and display message "Authentication successful"


  @TC_WA_002_sd_007 @Case_Negative_Login
  Scenario Outline: [TC_WA_002 s/d TC_WA_007] Verify failed login with various combinations of username and password
    Given user already on the login page of SETARA website
    When user input username "<username>"
    And user input password "<password>"
    And user click icon show password to see password is correct or not
    And user click Masuk button
    Then validate failed login and display result with message "expectedMessage"

    Examples:
      | ScenarioTitle                                     | username     | password     |
      | TC_WA_002 @Case_Negative1_InvalidUsername         | ANDIKA2703   | andika12345  |
      | TC_WA_003 @Case_Negative2_InvalidPassword         | ADTP604T     | lupapassword |
      | TC_WA_004 @Case_Negative3_InvalidUsernamePassword | ANDIKA2703   | lupapassword |
      | TC_WA_005 @Case_Negative4_EmptyUsername           |              | andika12345  |
      | TC_WA_006 @Case_Negative5_EmptyPassword           | ADTP604T     |              |
      | TC_WA_007 @Case_Negative6_EmptyUsernamePassword   |              |              |
