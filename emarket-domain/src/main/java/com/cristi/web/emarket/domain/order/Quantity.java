package com.cristi.web.emarket.domain.order;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.Collections.singletonList;

@DDD.ValueObject
public class Quantity extends BaseValueObject<Quantity> {
    @Constraint
    private final int value;

    public Quantity(int value) {
        super(Quantity.class);
        this.value = value;
        validate(this);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(value);
    }

    public int getValue() {
        return value;
    }

    @Retention(RUNTIME)
    @Target(FIELD)
    @javax.validation.Constraint(validatedBy = {})
    @NotNull
    @Min(1)
    @Max(9999)
    public @interface Constraint {
        String message() default "Quantity should be between 1 and 9999";
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }
}
