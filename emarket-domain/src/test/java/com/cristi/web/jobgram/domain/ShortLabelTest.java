package com.cristi.web.jobgram.domain;

import com.cristi.web.jobgram.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

public class ShortLabelTest {


    @Test(expected = DomainConstraintViolationException.class)
    public void when_value_is_null_then_short_label_should_be_rejected() {
        new ShortLabel(null);
    }

}
