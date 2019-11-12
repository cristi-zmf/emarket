package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.UniqueId;

import java.util.Set;

public interface Customers {
    Set<Customer> getByFirstname(NamePart firstName);

    Customer add(Customer customer);

    Customer getOrThrow(UniqueId customerId);
}
