Feature: Processing complete order

  Scenario Outline: User places and confirms an order
    Given User is logged into CodersLab shop
    When User opens Hummingbird Printed Sweater product page
    And Validates the "20%" discount
    And Selects "<size>" size
    And Sets product quantity to "<amount>"
    And Adds item to the cart
    And Proceeds to checkout
    And Confirms address
    And Selects pick up in-store delivery method
    And Selects payment by check with obligation to pay
    Then User takes a screenshot of confirmation page
    And Navigates to order history
    And Confirms orders is placed with "Awaiting check payment" status and correct price
    Examples:
    | size | amount |
    | L    | 5      |