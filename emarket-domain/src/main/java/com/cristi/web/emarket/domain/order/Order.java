package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.BaseAggregateRoot;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Order extends BaseAggregateRoot<Order, UniqueId> {
    @NotEmpty
    private List<Line> orderLines;
    @NotNull
    private OrderStatus status;
    @NotNull
    private UniqueId customer;

    public Order(List<Line> orderLines, OrderStatus status, UniqueId customer) {
        super(Order.class, new UniqueId());
        this.orderLines = new ArrayList<>(orderLines);
        this.status = status;
        this.customer = customer;
        validate(this);
    }

    public List<Line> orderLines() {
        return unmodifiableList(orderLines);
    }

    public OrderStatus status() {
        return status;
    }

    public UniqueId getCustomerId() {
        return customer;
    }
}
