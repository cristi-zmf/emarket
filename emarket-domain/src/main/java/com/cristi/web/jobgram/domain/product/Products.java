package com.cristi.web.jobgram.domain.product;

import com.cristi.web.jobgram.domain.UniqueId;
import com.cristi.web.jobgram.domain.ddd.DDD;

import java.util.Set;

@DDD.DomainRepository
public interface Products {
    Set<Product> findAll(Set<UniqueId> productIds);
    Product getOrThrow(UniqueId productId);
    Product add(Product product);
}
