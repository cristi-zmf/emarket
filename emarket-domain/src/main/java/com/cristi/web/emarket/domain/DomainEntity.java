package com.cristi.web.emarket.domain;

import com.cristi.web.emarket.domain.ddd.DDD;
import com.cristi.web.emarket.domain.ddd.Validable;

import javax.validation.constraints.NotNull;

@DDD.DomainEntity
public class DomainEntity implements Validable<DomainEntity> {
    @NotNull private UniqueId id;
    @NotNull private ShortLabel title;
    @NotNull private Description description;

    public DomainEntity(UniqueId id, ShortLabel title, Description description) {
        this.id = id;
        this.title = title;
        this.description = description;
        validate(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainEntity that = (DomainEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
