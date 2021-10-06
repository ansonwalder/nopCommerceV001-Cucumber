Feature: Login

  Background: Open application URL
    Given User opens URL "https://admin-demo.nopcommerce.com/"

  @Sanity
  Scenario: Login with valid credentials
    When User enters Email as "admin@yourstore.com" and Password as "admin"
    And Clicks on LOG IN
    Then Page title should be "Dashboard / nopCommerce administration"
    When user clicks on Logout
    Then Page title should be "Your store. Login"

  @Regression
  Scenario Outline: Login with valid credentials datadriven
    When User enters Email as "<Email>" and Password as "<Password>"
    And Clicks on LOG IN
    Then Page title should be "Dashboard / nopCommerce administration"
    When user clicks on Logout
    Then Page title should be "Your store. Login"

    Examples: 
      | Email               | Password |
      | admin@yourstore.com | admin    |
     #| admin@yourstore1.com | admin1   |
