package com.cristi.web.jobgram.domain.ddd;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public abstract class BaseEntity<T extends BaseEntity<T, ID>, ID extends BaseValueObject<ID>> implements Validable<BaseEntity<T, ID>> {
    private Class<T> type;

    @NotNull
    private final ID id;

    protected BaseEntity(Class<T> type, ID id) {
        this.type = requireNonNull(type);
        this.id = requireNonNull(id);
    }

    public final ID getId() {
        return id;
    }

    protected final Class<T> getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (type == null) {
            return false;
        }
        if (!type.isInstance(obj)) {
            return false;
        }
        T other = type.cast(obj);
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return format("[%s: %s]", type.getSimpleName(), id);
    }

    /*To implement faster the default constructor required by JPA in concrete classes*/
    protected BaseEntity(Class<T> type) {
        this.type = type;
        this.id = null;
    }
}
