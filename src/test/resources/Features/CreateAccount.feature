Feature: Create Account in Bestbuy Application

  @ValidCredentials
  Scenario Outline: : Creating Account with valid credentials
  Given User is on Homepage
    And User clicks the Account dropdown and "CreateAccount" butten
    When User enters first name <firstname> and Lastname <lastname>
    When User enters username as <username>
    And User enters password as <password>
    And User re enters the password as <password>
    And User enters mobilenumber as <mob>
    And User clicks the "CreateAnAccount" button
    Then verify Success message

    Examples: 
      | username                       | password   |
      | Sangeethaponmadasamy@gmail.com | 9876543210 |
