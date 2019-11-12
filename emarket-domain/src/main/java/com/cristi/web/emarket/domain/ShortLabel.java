package com.cristi.web.emarket.domain;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.lang.annotation.*;
import java.util.List;

import static java.util.Collections.singletonList;

@DDD.ValueObject
public class ShortLabel extends BaseValueObject<ShortLabel> {
    @Constraint
    private final String value;

    public ShortLabel(String value) {
        super(ShortLabel.class);
        this.value = value;
        validate(this);
    }

    private ShortLabel() {
        /*USED BY JPA, DONT USE IN PRODUCTION CODE*/
        super(ShortLabel.class);
        value = null;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(value);
    }

    public String getValue() {
        return value;
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @NotEmpty
    @Size(max = 100)
    @javax.validation.Constraint(validatedBy = {})
    public @interface Constraint {
        String message() default "Short Label should not be empty or over 100 character long";
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }
}
