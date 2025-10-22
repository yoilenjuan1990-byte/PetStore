Feature: User Registration
  Background:
    Given Access to the page "https://petstore.octoperf.com/actions/Catalog.action"
    And Verify that "Sign In" is visible
    And I click in the "Sign In" button
    And Verify that the currentURL "Account.action?signonForm="
    And I click on the "Register Now!" link
    And Verify that the currentURL "Account.action?newAccountForm="

  Scenario: Register Success with all required fields
    Given I enter the user ID "testuser123"
    And I enter the password "Pass123!"
    And I enter the repeat password "Pass123!"
    And I enter the first name "John"
    And I enter the last name "Doe"
    And I enter the email "john.doe@example.com"
    And I enter the phone "555-1234"
    And I enter the address1 "123 Main St"
    And I enter the city "New York"
    And I enter the state "NY"
    And I enter the zip "10001"
    And I enter the country "USA"
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"

  Scenario: Register Failed - Duplicate User
    Given I enter the user ID "EduardoME"
    And I enter the password "Pass123!"
    And I enter the repeat password "Pass123!"
    And I enter the first name "John"
    And I enter the last name "Doe"
    And I enter the email "john.doe@example.com"
    And I enter the phone "555-1234"
    And I enter the address1 "123 Main St"
    And I enter the city "New York"
    And I enter the state "NY"
    And I enter the zip "10001"
    And I enter the country "USA"
    And I click the "Save Account Information" button
    Then Verify that the error message register "already exists" is visible

  Scenario: Register Failed - Password Mismatch
    Given I enter the user ID "newuser456"
    And I enter the password "Pass123!"
    And I enter the repeat password "DifferentPass"
    And I enter the first name "Jane"
    And I enter the last name "Smith"
    And I enter the email "jane.smith@example.com"
    And I enter the phone "555-5678"
    And I enter the address1 "456 Oak Ave"
    And I enter the city "Boston"
    And I enter the state "MA"
    And I enter the zip "02101"
    And I enter the country "USA"
    And I click the "Save Account Information" button
    Then Verify that the error message register "passwords do not match" is visible

  Scenario: Register Failed - Missing Required Fields (Username)
    Given I enter the user ID ""
    And I enter the password "Pass123!"
    And I enter the repeat password "Pass123!"
    And I enter the first name "John"
    And I enter the last name "Doe"
    And I enter the email "john.doe@example.com"
    And I enter the phone "555-1234"
    And I enter the address1 "123 Main St"
    And I enter the city "New York"
    And I enter the state "NY"
    And I enter the zip "10001"
    And I enter the country "USA"
    And I click the "Save Account Information" button
    Then Verify that the error message register "required" is visible

  Scenario: Register Failed - Invalid Email Format
    Given I enter the user ID "userinvalidemail"
    And I enter the password "Pass123!"
    And I enter the repeat password "Pass123!"
    And I enter the first name "John"
    And I enter the last name "Doe"
    And I enter the email "invalid.email.format"
    And I enter the phone "555-1234"
    And I enter the address1 "123 Main St"
    And I enter the city "New York"
    And I enter the state "NY"
    And I enter the zip "10001"
    And I enter the country "USA"
    And I click the "Save Account Information" button
    Then Verify that the error message register "email" is visible

  # Data-Driven Testing: Usar datos aleatorios para evitar conflictos
  Scenario: Register Success with Random Generated Data
    Given I generate random user data
    And I fill the registration form with random data
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"

  # Data-Driven Testing: Leer datos desde archivo CSV
  Scenario: Register Success using CSV Data Row 1
    Given I read user data from CSV row 0
    And I fill the registration form with CSV data
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"

  # Integration Testing: Register → Logout → Login
  Scenario: Register and Login Integration Flow
    Given I generate random user data for integration
    And I fill the registration form with random data
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"
    When I click the "Sign Out" link
    And I click in the "Sign In" button
    And I login with the registered credentials
    Then Verify that the currentURL after login contains "Catalog.action"
