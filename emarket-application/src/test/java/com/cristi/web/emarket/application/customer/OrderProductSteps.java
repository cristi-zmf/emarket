package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.application.order.OrderProduct;
import com.cristi.web.emarket.application.order.OrderProductCommand;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProductSteps {
    private Orders inMemoryOrders = new InMemoryOrders();
    private OrderProduct sut = new OrderProduct(inMemoryOrders); //system under test
    private UniqueId newOrderId;
    private Product phone;
    private UniqueId customerId;

    @Given("^there are no orders for a customer$")
    public void thereAreNoOrdersForACustomer() {
        assertThat(inMemoryOrders.getAll()).isEmpty();
    }

    @When("^that customer buys a phone with a price of \"([^\"]*)\"$")
    public void thatCustomerBuysAPhoneWithAPriceOf(String price)  {
        customerId = new UniqueId();
        phone = new Product(new Price(price));
        newOrderId = sut.orderProduct(new OrderProductCommand(customerId, phone, new Quantity(1)));
    }

    @Then("^there is \"([^\"]*)\" \"([^\"]*)\" phone order for that customer$")
    public void thereIsPhoneOrderForThatCustomer(int noOfExpectedOrders, OrderStatus expectedOrderStatus) {
        assertThat(inMemoryOrders.getAll()).hasSize(noOfExpectedOrders);

        Order newOrder = inMemoryOrders.getOrThrow(newOrderId);
        assertThat(newOrder.status()).isEqualTo(expectedOrderStatus);
        assertThat(newOrder.getCustomerId()).isEqualTo(customerId);

        Line phoneLine = newOrder.orderLines().get(0);
        assertThat(phoneLine.productId()).isEqualTo(phone.getId());
        assertThat(phoneLine.quantity()).isEqualTo(new Quantity(1));
    }
}
