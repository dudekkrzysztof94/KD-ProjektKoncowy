@address @regression
Feature: Add and remove shipping addresses

  As a logged-in user
  I want to add and delete shipping addresses
  So that I can manage my shipping details easily

  Background:
    Given User is logged into Presta shop

  @smoke
  Scenario Outline: User adds a new address and then deletes it
    When User navigates to the Addresses Page
    And User clicks on the new address link
    And User fills in the address form with "<name>", "<surname>", "<alias>", "<address>", "<city>", "<code>", "<country>", "<phone>"
    And User submits the new address
    Then Message "Address successfully added!" should be displayed
    And Address with "<alias>" should be visible in the address list
    When User deletes the address with "<alias>"
    Then Message "Address successfully deleted!" should be displayed

    Examples:
      | alias          | name      | surname  | address  | city   | code   | country        | phone     |
      | Home Address   | Jazimierz | Kowalski | Dluga 5  | Warsaw | 02-741 | United Kingdom | 753159855 |
      | Office Address | John      | Doe      | Krotka 3 | Krakow | 30-415 | United Kingdom | 123456789 |
