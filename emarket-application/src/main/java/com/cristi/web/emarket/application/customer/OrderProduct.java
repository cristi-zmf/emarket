package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.application.ApplicationService;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.Customers;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.*;
import com.cristi.web.emarket.domain.product.Product;
import org.springframework.context.annotation.Lazy;

import static java.util.Collections.singletonList;

@ApplicationService
@DDD.ApplicationService
@Lazy
public class OrderProduct {
    private final Customers customers;
    private final Orders orders;

    public OrderProduct(Customers customers, Orders orders) {
        this.customers = customers;
        this.orders = orders;
    }


    public UniqueId orderProduct(UniqueId customerId, Product product, Quantity quantity) {
        Customer buyer = customers.getOrThrow(customerId);
        Line orderLine = new Line(quantity, product.getId());
        Order newOrder = new Order(singletonList(orderLine), OrderStatus.INITIATED);
        buyer.placeNewOrder(newOrder);
        orders.add(newOrder);
        customers.add(buyer);
        return newOrder.getId();
    }
}
