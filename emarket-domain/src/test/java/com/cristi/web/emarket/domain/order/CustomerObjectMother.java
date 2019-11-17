package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.CustomerName;
import com.cristi.web.emarket.domain.customer.NamePart;

public class CustomerObjectMother {
    private CustomerObjectMother() {
        throw new UnsupportedOperationException("Only static methods class");
    }

    public static Customer someCustomer() {
        CustomerName customerName = new CustomerName(new NamePart("John"), new NamePart("Doe"));
        return new Customer(customerName, new Address("Happy street", 25));
    }
}
