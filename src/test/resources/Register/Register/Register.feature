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

  # Data-Driven Testing: Usar datos aleatorios con especificaciones personalizadas
  Scenario: Register Success with Custom Specifications
    Given I generate random user data with specifications:
      | username_min_length          | 2    |
      | username_max_length          | 20   |
      | username_allow_special_chars | true |
      | password_length              | 15   |
      | password_uppercase           | true |
      | password_lowercase           | true |
      | password_special_chars       | true |
      | password_numbers             |      |
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

  # Data Persistence: Generar y guardar datos de usuario
  Scenario: Register Success and Save User Data to CSV
    Given I generate random user data
    And I fill the registration form with random data
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"
    And I save the generated user data to CSV file "successful_registrations"

  Scenario: Register Success and Save User Data to All Formats
    Given I generate random user data with specifications:
      | username_min_length          | 5    |
      | username_max_length          | 15   |
      | username_allow_special_chars | false|
      | password_length              | 12   |
      | password_uppercase           | true |
      | password_lowercase           | true |
      | password_special_chars       | true |
      | password_numbers             | true |
    And I fill the registration form with random data
    And I click the "Save Account Information" button
    Then Verify successful registration message is displayed
    And Verify that the currentURL after register contains "Catalog.action"
    And I save the generated user data to all formats with base name "custom_spec_users"

  Scenario: Generate and Save User Data Without Registration
    Given I generate and save a complete user with base name "generated_users"
    # Este escenario solo genera y guarda datos sin hacer registro real
