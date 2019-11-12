@jobdescription
Feature: Simple order of a product


  As a customer
  I want to order a product
  So that i can get the desired product
Scenario: Customer places an order

  Given there are no orders for customer "George"
  When the customer buys a phone with a price of "1000"
  Then there is "1" order with "INITIATED" status for "George"