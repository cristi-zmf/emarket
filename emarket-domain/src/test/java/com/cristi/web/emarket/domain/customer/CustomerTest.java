package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import com.cristi.web.emarket.domain.Address;
import org.junit.Test;

import java.util.Collections;

public class CustomerTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void when_null_address_then_constraint_violation() {
        new Customer(new UniqueId(), null, new CreditCard("1234 6457 8888 9999"), Collections.emptySet());
    }

    @Test
    public void when_null_credit_card_then_accept_customer_instance() {
        new Customer(new UniqueId(), new Address("New York street", 43), null, Collections.emptySet());
    }

    @Test(expected = NullPointerException.class)
    public void when_null_id_then_constraint_violation() {
        new Customer(null, new Address("New York street", 43), null, Collections.emptySet());
    }
}
