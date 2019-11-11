package com.cristi.web.jobgram.domain.order;

import com.cristi.web.jobgram.domain.UniqueId;
import com.cristi.web.jobgram.domain.ddd.DDD;

@DDD.DomainRepository
public interface Orders {
    Order getOrThrow(UniqueId orderId);
    Order add(Order order);
}
