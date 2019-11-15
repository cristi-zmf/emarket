package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@DDD.DomainRepositoryImpl //for tests
public class InMemoryOrders implements Orders {
    private Set<Order> db = new HashSet<>();


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
