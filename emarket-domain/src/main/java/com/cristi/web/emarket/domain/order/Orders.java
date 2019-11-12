package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;

@DDD.DomainRepository
public interface Orders {
    Order getOrThrow(UniqueId orderId);
    Order add(Order order);
}
