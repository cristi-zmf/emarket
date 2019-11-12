package com.cristi.web.emarket.domain;

import org.junit.Test;
import org.springframework.test.annotation.Repeat;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueIdTest {

    @Test
    @Repeat(10)
    public void when_normal_id_then_value_is_36_chars_always() {
        assertThat(new UniqueId().getValue()).hasSize(36);
    }

}
