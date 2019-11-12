package com.cristi.web.emarket.domain.product;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.order.Quantity;

import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@DDD.ValueObject
public class Price extends BaseValueObject<Price> {

    @Constraint
    private final BigDecimal value;

    public Price(BigDecimal value) {
        super(Price.class);
        assertNotNull(value);
        this.value = value.setScale(2, RoundingMode.HALF_DOWN);;
        validate(this);
    }


    public Price(String value) {
        this(new BigDecimal(value));
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(value);
    }

    public Price multiplyQuantity(Quantity quantity) {
        return new Price(
                value.multiply(new BigDecimal(quantity.getValue()))
        );
    }

    public Price sumUp(Price price) {
        return new Price(this.value.add(price.value));
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @DecimalMin(value = Constraint.MIN_VALUE)
    @DecimalMax(value = Constraint.MAX_VALUE)
    @NotNull
    @javax.validation.Constraint(validatedBy = {})
    public @interface Constraint {

        String MIN_VALUE = "0";
        String MAX_VALUE = "1000000.000";

        String message() default "Price should be between " + MIN_VALUE + " and " + MAX_VALUE;
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }

}
