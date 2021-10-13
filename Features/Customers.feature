Feature: Customers

  Background: Below are the common steps for each scenario
    Given User Launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboard
    And User click on Customers menu
    And click on Customers menu item

  Scenario: Add a new Customer
    And click on Add new customer button
    Then User can view Add new customer page
    When User enter Customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

  @sanity
  Scenario: Search Customer by EmailID
    When Enter customer Email
    And Click on search button
    Then User should found Email in the search table
    And close browser

  @sanity
  Scenario: Search Customer by FirstName and LastName
    When User Enter FirstName
    And User Enter LastName
    And Click on search button
    Then User should found Name in the search table
    And close browser
