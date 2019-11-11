package com.cristi.web.jobgram.domain.customer;

import com.cristi.web.jobgram.domain.Address;
import com.cristi.web.jobgram.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

public class AddressTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void when_streetName_is_empty_then_constraint_exception() {
        new Address("", 32);
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void when_streetName_is_blank_then_constraint_exception() {
        new Address("     ", 41);
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void when_streetName_is_null_then_constraint_exception() {
        new Address(null, 41);
    }

}
