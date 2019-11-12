package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@DDD.DomainRepositoryImpl
@Repository
public class InMemoryOrders implements Orders{
    private Set<Order> dbOrders = new HashSet<>();
    @Override
    public Order getOrThrow(UniqueId orderId) {
        return dbOrders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order add(Order order) {
        dbOrders.remove(order);
        dbOrders.add(order);
        return order;
    }
}
