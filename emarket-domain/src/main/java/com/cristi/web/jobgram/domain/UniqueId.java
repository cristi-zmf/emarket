package com.cristi.web.jobgram.domain;

import com.cristi.web.jobgram.domain.ddd.BaseValueObject;
import com.cristi.web.jobgram.domain.ddd.DDD;

import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Arrays.asList;

@DDD.ValueObjectId
public class UniqueId extends BaseValueObject<UniqueId> implements Serializable {
    @Constraint
    private final String value;

    public UniqueId(UniqueId other) {
        super(UniqueId.class);
        this.value = Objects.requireNonNull(other).getValue();
    }

    public UniqueId() {
        super(UniqueId.class);
        this.value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(value);
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @NotEmpty
    @Size(max = 36)
    @javax.validation.Constraint(validatedBy = {})
    public @interface Constraint {
        String message() default "Unique id should not be empty or over 36 chars";
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }
}
