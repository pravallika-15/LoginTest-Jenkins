Feature: Login Page

  Scenario: Valid Login
    Given I am on the login page
    When I enter username "tomsmith" and password "SuperSecretPassword!"
    Then I should see "You logged into a secure area!"

  Scenario: Invalid Login
    Given I am on the login page
    When I enter username "wrong" and password "wrong"
    Then I should see "Your username is invalid!"

  Scenario: Empty Fields
    Given I am on the login page
    When I click login without entering anything
    Then I should see "Your username is invalid!"