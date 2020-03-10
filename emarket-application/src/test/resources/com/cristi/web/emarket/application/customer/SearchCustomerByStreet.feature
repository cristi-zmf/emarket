@customer
Feature: Search customers by address
  As a manager
  I want to search a customer by address
  So that I can analyze geographic statistics about store's customers

  Scenario Outline: Customer with address
    Given there is a customer "John" with address "<address>" and number "<addressNo>
    When the system searches for the customer with "<address>" and number "<addressNo>"
    Then the system returns 1 customer result
    Examples:
      | address       | addressNo |
      | Rue of Saints | 2         |
      | Rue of Saints |           |
      |               | 2         |
      |               |           |
