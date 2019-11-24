package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.BaseAggregateRoot;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@DDD.AggregateRoot
public class Customer extends BaseAggregateRoot<Customer, UniqueId> {
    @NotNull
    private CustomerName name;
    @NotNull
    private Address address;
    private CreditCard creditCard;
    private PhoneNumber phoneNumber;
    private HistoricData historicData;

    Customer(UniqueId uniqueId, CustomerName name, Address address, CreditCard creditCard,
             PhoneNumber phoneNumber, HistoricData historicData, Set<UniqueId> orders) {
        super(Customer.class, uniqueId);
        this.name = name;
        this.address = address;
        this.creditCard = creditCard;
        this.phoneNumber = phoneNumber;
        this.historicData = historicData;
        validate(this);
    }

    public Customer(CustomerName customerName, @NotNull Address address,
                    PhoneNumber phoneNumber, HistoricData historicData, @NotEmpty Set<UniqueId> orders) {
        this(new UniqueId(), customerName, address, null, phoneNumber, historicData, orders);
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
}
