package com.cristi.web.emarket.application.order;

import com.cristi.web.emarket.application.ApplicationService;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.Customers;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cristi.web.emarket.domain.order.OrderStatus.INITIATED;
import static java.util.Arrays.asList;

@DDD.ApplicationService
@ApplicationService
public class OrderProduct {
    private final Orders orders;
    private final Customers customers;

    @Autowired
    public OrderProduct(Orders orders, Customers customers) {
        this.orders = orders;
        this.customers = customers;
    }

    public UniqueId orderProduct(Product product, Quantity productQuantity, UniqueId customerId) {
        Line orderLine = new Line(productQuantity, product.getId());
        Order newOrder = new Order(asList(orderLine), INITIATED, customerId);
        orders.add(newOrder);
        return newOrder.getId();
    }
}
