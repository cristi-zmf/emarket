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

    public Order(UniqueId uniqueId, List<Line> orderLines, OrderStatus status) {
        super(Order.class, uniqueId);
        this.orderLines = new ArrayList<>(orderLines);
        this.status = status;
        validate(this);
    }

    public Order(List<Line> orderLines, OrderStatus status) {
        this(new UniqueId(), orderLines, status);
    }

    public List<Line> orderLines() {
        return unmodifiableList(orderLines);
    }
}
