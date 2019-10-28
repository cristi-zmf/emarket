package com.cristi.web.jobgram.infra.persistence;

import com.cristi.web.jobgram.infra.InfraLocalIT;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

public class SdjLocalIT  extends InfraLocalIT{
    
    @PersistenceContext
    private EntityManager em;

    @Test
    public void sdj_is_autowired() {
        assertThat(em).isNotNull();
    }
}
