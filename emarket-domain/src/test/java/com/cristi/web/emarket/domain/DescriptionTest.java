package com.cristi.web.emarket.domain;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DescriptionTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void description_over_5000_should_be_rejected() {
        String over5000 = RandomString.make(5001);
        assertThat(over5000.length()).isGreaterThan(5000);
        new Description(over5000);
    }

}
