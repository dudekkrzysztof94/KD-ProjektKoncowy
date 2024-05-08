@order @regression
Feature: Processing Complete Order

  As a logged-in user
  I want to place and confirm orders
  So that I can manage my purchases online efficiently

  Background:
    Given User is logged into Presta shop

  @smoke
  Scenario Outline: User places and confirms an order
    When User navigates to the "Hummingbird Printed Sweater" product page
    And User validates that a "20%" discount is applied
    And User selects size "<size>"
    And User sets the product quantity to "<amount>"
    And User adds the item to the cart
    And User proceeds to checkout
    And User continues with preselected address
    And User chooses the "<delivery>" delivery method
    And User continues with selected delivery option
    And User opts for "<payment option>" and confirms the order
    Then User navigates to the order history
    And User verifies the order is listed with status "<status>", correct price and reference

    Examples:
      | size | amount | delivery     | payment option   | status                     |
      | L    | 5      | My carrier   | Pay by Check     | Awaiting check payment     |
      | M    | 2      | Self pick up | Pay by bank wire | Awaiting bank wire payment |
