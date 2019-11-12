package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.BaseValueObject;

import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;
import java.util.List;

import static java.util.Collections.singletonList;

public class CreditCard extends BaseValueObject<CreditCard> {

    @Constraint
    private final String cardNumber;

    public CreditCard(String cardNumber) {
        super(CreditCard.class);
        this.cardNumber = cardNumber;
        validate(this);
    }

    public String normalizedCardNumber() {
        return cardNumber.replaceAll("\\D", "");
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return singletonList(cardNumber);
    }


    @Documented
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = "^(\\D*\\d\\D*){16,19}$")
    @NotBlank
    @javax.validation.Constraint(validatedBy = {})
    public @interface Constraint {
        String message() default "Card number should not be empty and must have between 16 and 19 digits";
        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }
}
