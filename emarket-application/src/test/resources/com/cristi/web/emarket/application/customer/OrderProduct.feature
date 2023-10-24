@order
Feature: Simple order of a product
  As a customer
  I want to order a product
  So that I can get the desired product

Scenario: Customer places an order
  Given there are no orders for a customer
  When that customer buys a phone
  Then there is "1" "INITIATED" phone order for that customer
