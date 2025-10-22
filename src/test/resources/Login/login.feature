Feature: Login

  Background:
    Given Access to the page "https://petstore.octoperf.com/actions/Catalog.action"
    And Verify that "Sign In" is visible
    And The "Sign In" button is clickable
    And I click in the "Sign In" button
    And Verify that the currentURL "Account.action?signonForm="

  Scenario: Login Success
    Given Access to the user "EduardoME"
    And Access to the password "123456"
    And I click in the "Login" button
    And Verify that the currentURL "Account.action?signonForm="

  Scenario: Login Failed with invalid credentials
    Given Access to the user "invalidUser"
    And Access to the password "wrongPass"
    And I click in the "Login" button
    Then Verify that the error message "Invalid username or password." is visible

  Scenario Outline: Login with different credentials (outline)
    Given Access to the user "<username>"
    And Access to the password "<password>"
    And I click in the "Login" button
    And Verify that the currentURL after login contains "<expectedUrlFragment>"
    Then Verify that the error message "<expectedError>" is visible

    Examples:
      | username    | password  | expectedUrlFragment                                                    | expectedError                 |
      | EduardoME   | 123456    | Account.action?signonForm=                                             | NONE                          |
      | invalidUser | wrongPass | Account.action;jsessionid=2A9011620266088611103A351250A449?signonForm= | Invalid username or password. |



