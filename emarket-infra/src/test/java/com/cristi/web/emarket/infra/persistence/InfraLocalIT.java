package com.cristi.web.emarket.infra.persistence;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
public abstract class InfraLocalIT {

}
