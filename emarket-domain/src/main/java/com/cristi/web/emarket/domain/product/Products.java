package com.cristi.web.emarket.domain.product;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;

import java.util.Set;

@DDD.DomainRepository
public interface Products {
    Set<Product> findAll(Set<UniqueId> productIds);
    Product getOrThrow(UniqueId productId);
    Product add(Product product);
}
