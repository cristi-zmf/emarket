package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.application.ApplicationService;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Line;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;
import com.cristi.web.emarket.domain.order.Quantity;
import com.cristi.web.emarket.domain.product.Product;

import java.util.List;

import static com.cristi.web.emarket.domain.order.OrderStatus.INITIATED;
import static java.util.Collections.singletonList;

@DDD.ApplicationService
@ApplicationService
public class OrderProduct {
    private final Orders orders;

    public OrderProduct(Orders orders) {
        this.orders = orders;
    }

    public void orderProduct(Product product, UniqueId customerId) {
        List<Line> singleOrderLine = singletonList(new Line(new Quantity(1), product.getId()));
        Order newOrder = new Order(singleOrderLine, INITIATED, customerId);
        orders.add(newOrder);
    }
}
