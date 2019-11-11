package com.cristi.web.jobgram.domain.product;

import com.cristi.web.jobgram.domain.ddd.BaseValueObject;
import com.cristi.web.jobgram.domain.ddd.DDD;

import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@DDD.ValueObject
public class Price extends BaseValueObject<Price> {

    @Constraint
    private final BigDecimal value;

    public Price(BigDecimal value) {
        super(Price.class);
        this.value = value;
        validate(this);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(value);
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @DecimalMin(value = Constraint.MIN_VALUE)
    @DecimalMax(value = Constraint.MAX_VALUE)
    @NotNull
    @javax.validation.Constraint(validatedBy = {})
    public @interface Constraint {

        String MIN_VALUE = "0.2";
        String MAX_VALUE = "1000000.000";

        String message() default "Price should be between " + MIN_VALUE + " and " + MAX_VALUE;
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }

}
