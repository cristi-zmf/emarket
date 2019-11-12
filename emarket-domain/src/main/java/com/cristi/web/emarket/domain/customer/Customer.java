package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.BaseAggregateRoot;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@DDD.AggregateRoot
public class Customer extends BaseAggregateRoot<Customer, UniqueId> {
    @NotNull
    private Address address;
    private CreditCard creditCard;
    @NotEmpty
    private Set<Order> orders;

    protected Customer(UniqueId uniqueId, Address address, CreditCard creditCard, Set<Order> orders) {
        super(Customer.class, uniqueId);
        this.address = address;
        this.creditCard = creditCard;
        this.orders = orders;
        validate(this);
    }


    public Set<Order> getOrders() {
        return unmodifiableSet(orders);
    }

    public Address getAddress() {
        return address;
    }

    public Optional<CreditCard> getCreditCard() {
        return Optional.of(creditCard);
    }

    /*Used by JPA, dont use in production code*/
    public Customer() {
        super(Customer.class);
        address = null;
        creditCard = null;
    }
}
