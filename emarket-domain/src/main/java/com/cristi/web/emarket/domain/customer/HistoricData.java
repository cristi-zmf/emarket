package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;
import com.cristi.web.emarket.domain.ddd.DDD;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;

@DDD.ValueObject
@HistoricData.Constraint
public class HistoricData extends BaseValueObject<HistoricData> {

    @NotNull
    private final LocalDateTime registrationDate;
    @NotNull
    private final LocalDateTime lastLoginDate;

    public HistoricData(LocalDateTime registrationDate, LocalDateTime lastLoginDate) {
        super(HistoricData.class);
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        validate(this);
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public int calculateDaysSinceLastLogin() {
        return Period.between(lastLoginDate.toLocalDate(), now()).getDays();
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(registrationDate, lastLoginDate);
    }

    /*Used by JPA dont use in production code*/
    public HistoricData() {
        super(HistoricData.class);
        registrationDate = null;
        lastLoginDate = null;
    }

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @javax.validation.Constraint(validatedBy = {CreationDateBeforeLastLoginDateValidator.class})
    public @interface Constraint {
        String message() default "Creation Date can't be after last login date";
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }
}
