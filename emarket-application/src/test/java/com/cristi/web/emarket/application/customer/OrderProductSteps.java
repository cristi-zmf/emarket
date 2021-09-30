package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProductSteps {
    private final Orders inMemoryOrders = new InMemoryOrders();
    private OrderProduct sut = new OrderProduct(inMemoryOrders);
    private Product newPhone;
    private UniqueId customerId;

    @Given("^there are no orders for a customer$")
    public void thereAreNoOrdersForACustomer() {
        assertThat(inMemoryOrders.getAll()).isEmpty();
    }

    @When("^that customer buys a phone with a price of \"([^\"]*)\"$")
    public void thatCustomerBuysAPhoneWithAPriceOf(String price) {
        newPhone = new Product(new Price(price));
        customerId = new UniqueId();
        sut.orderProduct(newPhone, customerId);
    }

    @Then("^there is \"([^\"]*)\" \"([^\"]*)\" phone order for that customer$")
    public void thereIsPhoneOrderForThatCustomer(int expectedNoOfOrders, OrderStatus expectedStatus) {
        assertThat(inMemoryOrders.getAll()).hasSize(expectedNoOfOrders);

        Order actualOrder = inMemoryOrders.getAll().iterator().next();
        assertThat(actualOrder.status()).isEqualTo(expectedStatus);
        assertThat(actualOrder.getCustomerId()).isEqualTo(customerId);

        List<Line> orderLines = actualOrder.orderLines();
        Line phoneOrderLine = orderLines.iterator().next();
        assertThat(orderLines).hasSize(1);
        assertThat(phoneOrderLine.quantity()).isEqualTo(new Quantity(1));
        assertThat(phoneOrderLine.productId()).isEqualTo(newPhone.getId());
    }
}
