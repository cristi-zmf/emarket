package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.UniqueId;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class InMemoryCustomers implements Customers {
    private Set<Customer> db = new HashSet<>();

    @Override
    public Set<Customer> getByFirstname(NamePart firstName) {
        return db.stream()
                .filter(c -> c.getFirstName().equals(firstName))
                .collect(toSet());
    }

    @Override
    public Customer add(Customer customer) {
        db.remove(customer); //set does not replace the value, so we replace it manually
        db.add(customer);
        return customer;
    }

    @Override
    public Customer getOrThrow(UniqueId customerId) {
        return db.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
