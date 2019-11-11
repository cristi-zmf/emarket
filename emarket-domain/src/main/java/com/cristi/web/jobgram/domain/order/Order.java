package com.cristi.web.jobgram.domain.order;

import com.cristi.web.jobgram.domain.UniqueId;
import com.cristi.web.jobgram.domain.ddd.BaseAggregateRoot;
import com.sun.javafx.UnmodifiableArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Order extends BaseAggregateRoot<Order, UniqueId> {
    @NotEmpty
    private List<Line> orderLines;
    @NotNull
    private OrderStatus status;

    public Order(UniqueId uniqueId, List<Line> orderLines) {
        super(Order.class, uniqueId);
        this.orderLines = new ArrayList<>(orderLines);
        validate(this);
    }

    public Order(List<Line> orderLines, OrderStatus status) {
        this(new UniqueId(), orderLines);
    }

    public List<Line> orderLines() {
        return unmodifiableList(orderLines);
    }
}
