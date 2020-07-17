package com.cristi.web.emarket.application.customer;


import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Product;

import java.util.Arrays;
import java.util.List;

public class OrderProduct {
    private Orders orders;

    public OrderProduct(Orders orders) {
        this.orders = orders;
    }

    public UniqueId orderProduct(Product product, UniqueId customerId) {
        List<Line> singleOrderLine = Arrays.asList(new Line(new Quantity(1), product.getId()));
        Order newOrder = new Order(singleOrderLine, OrderStatus.INITIATED, customerId);
        orders.add(newOrder);
        return newOrder.getId();
    }
}
