package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

public class NamePartTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void when_name_is_not_letters_then_constraint_violation() {
        new NamePart("####");
    }

    @Test
    public void when_name_is_french_letters_then_accept_instance() {
        new NamePart("ÿüûùà");
    }

    @Test
    public void when_name_is_chinese_letters_then_accept_instance() {
        new NamePart("象形字");
    }

    @Test
    public void when_name_is_letters_and_spaces_then_accept_instance() {
        new NamePart("Julien d'");
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void when_name_is_space_characters_then_constraint_violation() {
        new NamePart("        \n\t\r");
    }
}
