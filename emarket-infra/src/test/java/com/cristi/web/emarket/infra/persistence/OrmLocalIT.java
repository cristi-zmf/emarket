package com.cristi.web.emarket.infra.persistence;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

public class OrmLocalIT extends InfraLocalIT {
    @PersistenceContext(name = "default")
    private EntityManager em;

    @Test
    public void loading_orm_mapping_should_not_crash() {
        assertThat(em).isNotNull();
    }
}
