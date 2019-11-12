package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditCardTest {

    @Test
    public void when_valid_card_number_then_creditCard_is_constructed() {
        new CreditCard("1234 65324567  8988");
    }

    @Test
    public void when_normalizedCardNumber_then_only_digits_card_number() {
        CreditCard creditCard = new CreditCard("1234-6532-4567 8988");
        assertThat(creditCard.normalizedCardNumber()).matches("\\d+");
        assertThat(creditCard.normalizedCardNumber()).matches("1234653245678988");
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void when_card_number_with_over_19_digits_then_constraint_exception() {
        new CreditCard("12345678901234567890");
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void when_null_card_number_then_constraint_exception() {
        new CreditCard(null);
    }
}
