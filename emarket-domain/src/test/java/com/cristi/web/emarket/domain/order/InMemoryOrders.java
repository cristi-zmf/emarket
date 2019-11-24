package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class InMemoryOrders implements Orders {
    private final Set<Order> db = new HashSet<>();

    @Override
    public Order getOrThrow(UniqueId orderId) {
        return db.stream()
                .filter(e -> e.getId().equals(orderId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order add(Order order) {
        db.remove(order);
        db.add(order);
        return order;
    }

    @Override
    public Set<Order> getAll() {
        return new HashSet<>(db);
    }
}
