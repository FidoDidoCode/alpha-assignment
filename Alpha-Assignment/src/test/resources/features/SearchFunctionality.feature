Feature: Customer searches for Nikon in Amazon online shopping portal, sorts the results and checks for the product details.

  @SearchNikon
  Scenario Outline: Search Amazon
    Given Navigate to Amazon online shopping portal homepage using <URL>
    When Search for product <productName>
    And Sort the search results with <sortOption>
    And Select <resultToBeVerified> nd product from the sorted results
    Then Verify if chosen product is of the model <expectedProduct>
    Examples:
      | URL                    | productName | sortOption         | resultToBeVerified | expectedProduct |
      | https://www.amazon.com | Nikon       | Price: High to Low | 2                  | Nikon D3X       |