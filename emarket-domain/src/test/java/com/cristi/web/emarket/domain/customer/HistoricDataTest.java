package com.cristi.web.emarket.domain.customer;

import com.cristi.web.emarket.domain.ddd.DomainConstraintViolationException;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

public class HistoricDataTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void should_reject_historic_data_with_registration_date_empty() {
        new HistoricData(null, now());
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void should_reject_historic_data_with_last_login_date_empty() {
        new HistoricData(now(), null);
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void should_reject_historic_data_with_registration_date_after_last_login_date() {
        LocalDateTime registrationDate = LocalDateTime.now().minusDays(1);
        LocalDateTime lastLoginDate = LocalDateTime.now().minusDays(4);
        new HistoricData(registrationDate, lastLoginDate);
    }

    @Test
    public void should_create_historic_data_with_registration_date_same_with_last_login_date() {
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        HistoricData historicData = new HistoricData(date, date);
        assertThat(historicData).isNotNull();
        assertThat(historicData.getLastLoginDate()).isEqualTo(date);
        assertThat(historicData.getRegistrationDate()).isEqualTo(date);
    }

    @Test
    public void should_calculate_two_days_since_last_login() {
        LocalDateTime lastLoginDate = LocalDateTime.now().minusDays(2);
        HistoricData historicData = new HistoricData(lastLoginDate, lastLoginDate);
        int daysSinceLastLogin = historicData.calculateDaysSinceLastLogin();
        assertThat(daysSinceLastLogin).isEqualTo(2);
    }

}