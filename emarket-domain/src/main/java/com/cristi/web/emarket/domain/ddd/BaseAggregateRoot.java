package com.cristi.web.emarket.domain.ddd;

import static java.lang.String.format;

public class BaseAggregateRoot<T extends BaseAggregateRoot<T, ID>, ID extends BaseValueObject<ID>> extends BaseEntity<T, ID> {
    protected BaseAggregateRoot(Class<T> type, ID id) {
        super(type, id);
    }

    protected long version;

    @Override
    public String toString() {
        return format("%s<id%s, version:%s>", getType().getSimpleName(), getId(), version);
    }

    public long getVersion() {
        return version;
    }

    /*To implement faster default constructor required by JPA*/
    protected BaseAggregateRoot(Class<T> type) {
        super(type);
    }
}
