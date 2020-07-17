package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryOrders implements Orders {
    private final Map<UniqueId, Order> database = new HashMap<>();

    @Override
    public Order getOrThrow(UniqueId orderId) {
        return database.get(orderId);
    }

    @Override
    public Order add(Order order) {
        return database.put(order.getId(), order);
    }

    @Override
    public Set<Order> getAll() {
        return new HashSet<>(database.values());
    }
}
