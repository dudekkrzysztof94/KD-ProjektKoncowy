Feature: Add shipping address after login

  Scenario Outline: User adds a new address to his account
    Given User logs into CodersLab shop
    When User navigates to AddressesPage
    And User creates new address
    And User fills in address form with "<alias>", "<address>", "<city>", "<code>", "<country>", "<phone>"
    And User submits new address
    And Message "Address successfully added!" is displayed
    And Address is verified
    And User deletes last added address
    Then Message "Address successfully deleted!" is displayed
    Examples:
    | alias           | address | city   | code   | country        | phone     |
    | example address | Dluga 5 | Warsaw | 02-741 | United Kingdom | 753159855 |