package com.cristi.web.emarket.domain.product;

import com.cristi.web.emarket.domain.ddd.BaseAggregateRoot;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.constraints.NotNull;

@DDD.AggregateRoot
public class Product extends BaseAggregateRoot<Product, UniqueId> {
    @NotNull
    private Price price;

    private Product(UniqueId id, Price price) {
        super(Product.class, id);
        this.price = price;
    }

    public Product(Price price) {
        this(new UniqueId(), price);
    }

    /*Used by JPA dont use in production code*/
    private Product() {
        super(Product.class);
        this.price = null;
    }

    public Price price() {
        return price;
    }
}
