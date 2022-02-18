Feature: To verify API automation with Rest Assured

  Scenario: Get all products- GET method
    Given I want to execute getProducts endpoint
    When I execute GET request
    Then I should receive 200 response code

  Scenario: Get one product- GET method
    Given I want to execute getProduct endpoint
    When I execute GET request
    Then I should receive 200 response code

  Scenario: Post product -POST method
    Given I want to execute postProduct endpoint
    When I execute POST request
    Then I should receive 201 response code

  Scenario: Put product-PUT method
    Given I want to execute updateProduct endpoint
    When I execute PUT request
    Then I should get 201 response code

  Scenario: Delete product-DELETE method
    Given I want to execute deleteProduct endpoint
    When I execute DELETE request
    Then I should get 200 response code
