package com.cristi.web.jobgram.domain.customer;

import com.cristi.web.jobgram.domain.UniqueId;
import com.cristi.web.jobgram.domain.ddd.BaseAggregateRoot;
import com.cristi.web.jobgram.domain.ddd.DDD;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@DDD.AggregateRoot
public class Customer extends BaseAggregateRoot<Customer, UniqueId> {
    @NotNull
    private Address address;
    private CreditCard creditCard;

    protected Customer(UniqueId uniqueId, Address address, CreditCard creditCard) {
        super(Customer.class, uniqueId);
        this.address = address;
        this.creditCard = creditCard;
        validate(this);
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
