@smoke
Feature: Scenario: search product
  Background:
    Given the user navigates to "https://www.takealot.com/" url
    And accept an alert if it exists

  Scenario Outline:
    And the user clicks "product_search_field" page element on "home_page" page
    And the user types "<product_category>" into "search_text_box" page element on "home_page" page
    And the user clicks "search_button" page element on "home_page" page
    And the user clicks "view_list_button" page element on "product_page" page
    And the user clicks web element from the list with attribute "product_tittle_list" where attribute value "contains" "<product_name>" on "product_page"  page
    And the user verifies that the text content of "pdp_product_tittle" page element "contains" "<product_name>" on "product_page" page
    And the user clicks "add_to_cart" page element on "product_page" page
    And the user verifies that the text content of "added_to_cart" page element "contains" "Added to cart" on "product_page" page
    And the user clicks "go_to_cart" page element on "product_page" page
    And the user verifies that the text content of "cart_product_tittle" page element "contains" "product_name" on "product_page" page
    And the user clicks "check_out_button" page element on "product_page" page

    Examples:
      |product_category|product_name        |productPrice|
      |iphone charger         |20W PD Fast Charger For iPhone with USB-C to Lightning Cable|            |