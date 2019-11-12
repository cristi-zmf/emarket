package com.cristi.web.emarket.infra.persistence;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cristi.web.jobgram")
@EntityScan(
        basePackages = {"com.cristi.web.jobgram", "org.springframework.data.jpa.convert.threeten"}
)
@ComponentScan(
        basePackages = {"com.cristi.web.jobgram"}
)
@EnableAutoConfiguration
public class PersistenceConfig {
}
