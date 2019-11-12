package com.cristi.web.emarket.domain.ddd;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static java.lang.String.format;

public interface Validable<T> {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    default void validate(T obj) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(format("For object: %s \n", obj.getClass().getTypeName()));
            for (ConstraintViolation<T> violation : violations) {
                stringBuilder.append(format("%s: ", violation.getPropertyPath()));
                stringBuilder.append(violation.getMessage());
                stringBuilder.append(format(". Actual value: %s", violation.getInvalidValue()));
                stringBuilder.append("\n");
            }
            throw new DomainConstraintViolationException(stringBuilder.toString());
        }
    }
}
