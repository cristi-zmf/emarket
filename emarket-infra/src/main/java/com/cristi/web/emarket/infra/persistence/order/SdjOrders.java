package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
@DDD.DomainRepositoryImpl
public class SdjOrders implements Orders {
    private final OrdersSdj jpaRepo;

    @Autowired
    public SdjOrders(OrdersSdj jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Order getOrThrow(UniqueId orderId) {
        return jpaRepo.getOne(orderId);
    }

    @Override
    public Order add(Order order) {
        return jpaRepo.saveAndFlush(order);
    }

    @Override
    public Set<Order> getAll() {
        return new HashSet<>(jpaRepo.findAll());
    }
}
