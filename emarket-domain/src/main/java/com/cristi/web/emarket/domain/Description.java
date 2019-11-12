package com.cristi.web.emarket.domain;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Collections.singletonList;

@DDD.ValueObject
public class Description extends BaseValueObject<Description> {
    @Size(max = 5000)
    private String value;

    public Description(String value) {
        super(Description.class);
        this.value = value;
        validate(this);
    }

    public String getValue() {
        return value;
    }

    private Description() {
        /*Used by JPA, dont use in production code*/
        super(Description.class);
        this.value = null;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(value);
    }
}
