package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.application.order.OrderProduct;
import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.customer.*;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderProductSteps {
    private final Orders inMemoryOrders = new InMemoryOrders();
    private final Customers inMemoryCustomers = new InMemoryCustomers();
    private final OrderProduct sut = new OrderProduct(inMemoryOrders, inMemoryCustomers); // system under test
    private Customer someCustomer;
    private Product somePhone;


    @Given("^there are no orders for a customer$")
    public void thereAreNoOrdersForACustomer() {
        CustomerName fullName = new CustomerName(new NamePart("John"), new NamePart("Doe"));
        someCustomer = new Customer(fullName, new Address("Happy street", 10), emptySet());
        inMemoryCustomers.add(someCustomer);

        assertThat(inMemoryOrders.getAll()).isEmpty();
    }

    @When("^that customer buys a phone with a price of \"([^\"]*)\"$")
    public void thatCustomerBuysAPhoneWithAPriceOf(String price) {
        somePhone = new Product(new Price(price));
        sut.orderProduct(somePhone, new Quantity(1), someCustomer.getId());
    }

    @Then("^there is \"([^\"]*)\" \"([^\"]*)\" phone order for that customer$")
    public void thereIsPhoneOrderForThatCustomer(int noOfOrders, OrderStatus expectedOrderStatus) {
        Set<Order> existingOrders = inMemoryOrders.getAll();
        assertThat(existingOrders).hasSize(noOfOrders);

        Order actualOrder = existingOrders.stream().findFirst().orElseThrow(IllegalStateException::new);
        assertThat(actualOrder.status()).isEqualTo(expectedOrderStatus);
        assertThat(actualOrder.getCustomerId()).isEqualTo(someCustomer.getId());

        Line phoneOrderLine = actualOrder.orderLines().get(0);
        assertThat(phoneOrderLine.productId()).isEqualTo(somePhone.getId());
        assertThat(phoneOrderLine.quantity()).isEqualTo(new Quantity(1));
    }
}
