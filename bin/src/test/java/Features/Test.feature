Feature: To verify API automation with Rest Assured

  Scenario: Get all products- GET method
    Given get all products
    When I execute GET request url
    Then verify the status code is 200

  Scenario: Get one product- GET method
    Given a product exists with an id of 4
    When a user GET the product by id
    Then verify the status code is 200
    And verify response includes following in the response
      | name        |iPhoneX   |
      | price       |600.0   |
      | description |ApplePhone |
      | quantity    |  44 |

  Scenario: Post product -POST method
    Given create a product with input
      | input | {"name":"claypot","price":34,"description":"ornament","quantity":3} |
    When a user post the product
    Then verify the status code is 200
    And verify response includes following in the response
      | name        | claypot  |
      | price       |     34.0 |
      | description | ornament |
      | quantity    |        3 |

  Scenario: Update a product - PUT method
    Given update a product with given a product id 133 with input
      | input | {"name":"claypot-updated","price":34,"description":"ornament","quantity":3} |
    When a user PUT the productId with id
    Then verify the status code is 200
    And verify response includes following in the response
      | id   |             133 |
      | name | claypot-updated |

  Scenario: Delete product-DELETE method
    Given a product exists with an id of 139
    When a user delete the product by id
    Then verify the status code is 200
