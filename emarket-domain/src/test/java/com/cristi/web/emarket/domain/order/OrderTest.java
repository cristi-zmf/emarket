package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

import java.util.Collections;

public class OrderTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void order_with_empty_order_lines_is_rejected() {
        new Order(Collections.emptyList(), OrderStatus.INITIATED);
    }
}
