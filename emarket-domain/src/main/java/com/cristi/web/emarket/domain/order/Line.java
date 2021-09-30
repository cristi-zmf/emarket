package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.product.Price;
import com.cristi.web.emarket.domain.product.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@DDD.ValueObject
public class Line extends BaseValueObject<Line> {
    @NotNull
    private final Quantity quantity;
    @NotNull
    private final UniqueId productId;

    public Line(Quantity quantity, UniqueId productId) {
        super(Line.class);
        this.quantity = quantity;
        this.productId = productId;
        validate(this);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(quantity, productId);
    }

    public UniqueId productId() {
        return productId;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Price totalPrice(Product typeOfProduct) {
        if (!typeOfProduct.getId().equals(productId)) {
            throw new IllegalArgumentException(
                    format("Product %s does not correspond to this order line's product id %s", typeOfProduct, productId)
            );
        }
        return typeOfProduct.price().multiplyQuantity(quantity);
    }
/*USED BY JPA DONT USE IN PRODUCTION CODE*/
    private Line() {
        super(Line.class);
        this.quantity = null;
        this.productId = null;
    }
}
