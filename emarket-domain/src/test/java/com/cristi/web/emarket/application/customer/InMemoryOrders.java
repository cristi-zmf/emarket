package com.cristi.web.emarket.application.customer;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;

import java.util.*;

public class InMemoryOrders implements Orders {
    private final Map<UniqueId, Order> db = new HashMap<>();

    @Override
    public Order getOrThrow(UniqueId orderId) {
        if (!db.containsKey(orderId)) {
            throw new NoSuchElementException(orderId.toString());
        }
        return db.get(orderId);
    }

    @Override
    public Order add(Order order) {
        return db.put(order.getId(), order);
    }

    @Override
    public Set<Order> getAll() {
        return new HashSet<>(db.values());
    }
}
