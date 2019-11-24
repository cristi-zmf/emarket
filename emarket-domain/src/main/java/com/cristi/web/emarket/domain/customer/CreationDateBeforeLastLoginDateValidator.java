package com.cristi.web.emarket.domain.customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class CreationDateBeforeLastLoginDateValidator implements ConstraintValidator<HistoricData.Constraint, HistoricData> {
    @Override
    public boolean isValid(
            HistoricData historicData,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return !isNull(historicData.getRegistrationDate()) && !isNull(historicData.getLastLoginDate()) &&
                (historicData.getRegistrationDate().isBefore(historicData.getLastLoginDate()) ||
                historicData.getRegistrationDate().isEqual(historicData.getLastLoginDate()));
    }
}
