Feature: PetSelect

  Background:
    Given Access page "https://petstore.octoperf.com/actions/Catalog.action"


  Scenario: Acceso a la página de Peces
    Given Validate that there is a superior menu for "Fish"
    When Click on superior menu "Fish"
    Then Validate page "viewCategory=&categoryId=FISH"

  Scenario: Acceso a la página de Perros
    Given Validate that there is a superior menu for "Dogs"
    When Click on superior menu "Dogs"
    Then Validate page "viewCategory=&categoryId=DOGS"

  Scenario: Acceso a la página de Perross
    Given Validate that there is a superior menu for "Dogss"

  Scenario: Acceso a la página de Reptiles
    Given Validate that there is a superior menu for "Reptiles"
    When Click on superior menu "Reptiles"
    Then Validate page "viewCategory=&categoryId=REPTILES"

  Scenario: Acceso a la página de Cats
    Given Validate that there is a superior menu for "Cats"
    When Click on superior menu "Cats"
    Then Validate page "viewCategory=&categoryId=CATS"

  Scenario: Acceso a la página de Birds
    Given Validate that there is a superior menu for "Birds"
    When Click on superior menu "Birds"
    Then Validate page "viewCategory=&categoryId=BIRDS"