@searchProduct
Feature: Scenario: search product
  Background:
    Given the user opens "chrome" browser
    And the user navigates to "https://www.shoprite.co.za/" url

  Scenario Outline:
    And the user taps "search_text_box" page element
    And the user types "productName" into "search_text_box" page element
    And the user taps "search_button" page element
    And the user taps "rama_product" page element
    And the user verifies that the text content of attribute of "product_name" page element "contains" "<productName>"
    And the user verifies that the text content of attribute of "product_name" page element "contains" "<productPrice>"

Examples:
    |productName        |productPrice|
    |Rama 37% fat spread| R54.99     |