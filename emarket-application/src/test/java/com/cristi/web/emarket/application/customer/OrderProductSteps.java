package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.*;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.NoSuchElementException;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderProductSteps {
    private Customers inMemoryCustomers = new InMemoryCustomers();
    private Orders inMemoryOrders = new InMemoryOrders();
    private OrderProduct sut = new OrderProduct(inMemoryCustomers, inMemoryOrders);
    private Customer someCustomer;
    private Product somePhone;

    @Given("^there are no orders for a customer$")
    public void thereAreNoOrdersForTheCustomer()  {
        CustomerName name = new CustomerName(new NamePart("John"), new NamePart("Doe"));
        someCustomer = new Customer(name, new Address("Cucu Street", 23), emptySet());
        assertThat(someCustomer.getOrders()).isEmpty(); //Being more royal than the king
        inMemoryCustomers.add(someCustomer);
    }

    @When("^that customer buys a phone with a price of \"([^\"]*)\"$")
    public void theCustomerBuysAPhoneWithAPriceOf(String price) {
        somePhone = new Product(new Price(price));
        sut.orderProduct(someCustomer.getId(), somePhone, new Quantity(1));
    }

    @Then("^there is \"([^\"]*)\" order with \"([^\"]*)\" status for that customer$")
    public void thereIsOrderWithStatusFor(int noOfOrders, OrderStatus expectedOrderStatus)  {
        Customer customer = inMemoryCustomers.getOrThrow(someCustomer.getId());
        assertThat(customer.getOrders()).hasSize(noOfOrders);

        UniqueId orderId = customer.getOrders().stream().findFirst()
                .orElseThrow(NoSuchElementException::new);
        Order actualOrder = inMemoryOrders.getOrThrow(orderId);
        assertThat(actualOrder.status()).isEqualTo(expectedOrderStatus);

        Line singleLineOfOrder = actualOrder.orderLines().get(0);
        assertThat(singleLineOfOrder.productId()).isEqualTo(somePhone.getId());
        assertThat(singleLineOfOrder.quantity()).isEqualTo(new Quantity(1));
    }
}
