package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@DDD.DomainRepositoryImpl
@Repository
public class SdjOrders implements Orders {
    private final OrdersJpaRepo ordersJpaRepo;

    public SdjOrders(OrdersJpaRepo ordersJpaRepo) {
        this.ordersJpaRepo = ordersJpaRepo;
    }

    @Override
    public Order getOrThrow(UniqueId orderId) {
        return ordersJpaRepo.getOne(orderId);
    }

    @Override
    public Order add(Order order) {
        return ordersJpaRepo.saveAndFlush(order);
    }

    @Override
    public Set<Order> getAll() {
        return new HashSet<>(ordersJpaRepo.findAll());
    }
}
