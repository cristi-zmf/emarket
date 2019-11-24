package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void should_reject_phone_number_with_null_value() {
        new PhoneNumber(null);
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void should_reject_phone_number_without_prefix() {
        new PhoneNumber("07222222");
    }

    @Test
    public void should_be_equal_two_phone_numbers_with_same_value() {
        String phoneValue = "+407222222";
        PhoneNumber phone1 = new PhoneNumber(phoneValue);
        PhoneNumber phone2 = new PhoneNumber(phoneValue);
        assertThat(phone1).isEqualTo(phone2);
    }

}