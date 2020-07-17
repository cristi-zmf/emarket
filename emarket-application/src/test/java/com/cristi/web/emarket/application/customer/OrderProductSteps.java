package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProductSteps {
    private Orders inMemoryOrders = new InMemoryOrders();
    private OrderProduct sut = new OrderProduct(inMemoryOrders); //system under test
    private UniqueId customerId;

    @Given("^there are no orders for a customer$")
    public void thereAreNoOrdersForACustomer() {
        assertThat(inMemoryOrders.getAll()).isEmpty();
    }

    @When("^that customer buys a phone with a price of \"([^\"]*)\"$")
    public void thatCustomerBuysAPhoneWithAPriceOf(String price) {
        Product newPhone = new Product(new Price(price));
        customerId = new UniqueId();
        sut.orderProduct(newPhone, customerId);
    }

    @Then("^there is \"([^\"]*)\" \"([^\"]*)\" phone order for that customer$")
    public void thereIsPhoneOrderForThatCustomer(int noOfExpectedOrders, String expectedStatus) {
        assertThat(inMemoryOrders.getAll()).hasSize(noOfExpectedOrders);

        Order singleOrder = inMemoryOrders.getAll().iterator().next();

        assertThat(singleOrder.status().name()).isEqualTo(expectedStatus);
        assertThat(singleOrder.getCustomerId()).isEqualTo(customerId);
    }
}
