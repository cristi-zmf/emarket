package com.cristi.web.emarket.application.order;

import com.cristi.web.emarket.application.ApplicationService;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.*;

import static java.util.Arrays.asList;

@DDD.ApplicationService
@ApplicationService
public class OrderProduct {
    private final Orders orders;

    public OrderProduct(Orders orders) {
        this.orders = orders;
    }


    public UniqueId orderProduct(OrderProductCommand orderProductCommand) {
        Line orderLine = new Line(orderProductCommand.getQuantity(), orderProductCommand.getProduct().getId());
        Order newOrder = new Order(asList(orderLine), OrderStatus.INITIATED, orderProductCommand.getCustomerId());
        return orders.add(newOrder).getId();
    }
}
