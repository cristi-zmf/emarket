package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.BaseAggregateRoot;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@DDD.AggregateRoot
public class Customer extends BaseAggregateRoot<Customer, UniqueId> {
    @NotNull
    private CustomerName name;
    @NotNull
    private Address address;
    private CreditCard creditCard;
    @NotNull
    private Set<UniqueId> orders;

    Customer(UniqueId uniqueId, CustomerName name, Address address, CreditCard creditCard, Set<UniqueId> orders) {
        super(Customer.class, uniqueId);
        this.name = name;
        this.address = address;
        this.creditCard = creditCard;
        this.orders = new HashSet<>(orders);
        validate(this);
    }

    public Customer(CustomerName customerName, @NotNull Address address, @NotEmpty Set<UniqueId> orders) {
        this(new UniqueId(), customerName, address, null, orders);
    }

    public Set<UniqueId> getOrders() {
        return unmodifiableSet(orders);
    }

    public NamePart getFirstName() {
        return name.getFirstName();
    }

    public NamePart getLastName() {
        return name.getLastName();
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

    public void placeNewOrder(Order newOrder) {
        orders.add(newOrder.getId());
    }
}
