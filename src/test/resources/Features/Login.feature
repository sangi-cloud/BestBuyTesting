Feature: Login to Bestbuy Application

  @ValidCredentials
  Scenario Outline: : Login with valid credentials
  Given User is on Homepage
    And User clicks the Account dropdown and "signin" butten
    When User enters username as <username>
    And User enters password as <password>
    And User clicks the signin button
    Then User should be able to login sucessfully and verify login message

    Examples: 
      | username                       | password   |
      | Sangeethaponmadasamy@gmail.com | 9876543210 |
