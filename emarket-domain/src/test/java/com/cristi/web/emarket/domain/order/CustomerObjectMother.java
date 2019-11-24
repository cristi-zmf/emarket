package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.customer.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class CustomerObjectMother {
    private CustomerObjectMother() {
        throw new UnsupportedOperationException("Only static methods class");
    }

    public static Customer someCustomer() {
        CustomerName customerName = new CustomerName(new NamePart("John"), new NamePart("Doe"));
        return new Customer(customerName, new Address("Happy street", 25),
                new PhoneNumber("+407222222"), new HistoricData(now(), now()));
    }
}
