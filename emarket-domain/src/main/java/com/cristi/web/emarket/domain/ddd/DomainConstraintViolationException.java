package com.cristi.web.emarket.domain.ddd;

public class DomainConstraintViolationException extends RuntimeException {

    public DomainConstraintViolationException(String message) {
        super(message);
    }
}
