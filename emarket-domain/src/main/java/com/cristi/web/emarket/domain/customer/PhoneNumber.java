package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static java.util.Collections.singletonList;

@DDD.ValueObject
public class PhoneNumber extends BaseValueObject<PhoneNumber> {

    @NotNull
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private final String value;

    public PhoneNumber(String value) {
        super(PhoneNumber.class);
        this.value = value;
        validate(this);
    }

    public String getValue() {
        return value;
    }

    /*Used by JPA dont use in production code*/
    public PhoneNumber() {
        super(PhoneNumber.class);
        value = null;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(value);
    }
}
