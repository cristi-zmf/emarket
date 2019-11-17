package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.Customer;

import static java.util.Arrays.asList;

public class OrderObjectMother {
    private OrderObjectMother() {
        throw new UnsupportedOperationException("Only static methods class");
    }

    public static Order someOrder(Customer customer) {
        Line line = new Line(new Quantity(1), new UniqueId());
        return new Order(asList(line), OrderStatus.INITIATED, customer.getId());
    }
}
