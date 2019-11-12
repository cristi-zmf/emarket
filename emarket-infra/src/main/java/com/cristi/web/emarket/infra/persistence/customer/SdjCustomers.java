package com.cristi.web.emarket.infra.persistence.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.Customers;
import com.cristi.web.emarket.domain.customer.NamePart;
import com.cristi.web.emarket.domain.ddd.DDD;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Set;

@DDD.DomainRepositoryImpl
@Repository
public class SdjCustomers implements Customers {
    private final CustomersSdj sdj;

    public SdjCustomers(CustomersSdj sdj) {
        this.sdj = sdj;
    }

    @Override
    public Set<Customer> getByFirstname(NamePart firstName) {
        return sdj.findByNameFirstNameValueContaining(firstName.getValue());
    }

    @Override
    public Customer add(Customer customer) {
        return sdj.saveAndFlush(customer);
    }

    @Override
    public Customer getOrThrow(UniqueId customerId) {
        return sdj.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("No customer with id " + customerId));
    }
}
