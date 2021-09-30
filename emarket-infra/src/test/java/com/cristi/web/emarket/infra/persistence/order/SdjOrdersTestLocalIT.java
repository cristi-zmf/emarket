package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.Customers;
import com.cristi.web.emarket.domain.order.CustomerObjectMother;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.OrderObjectMother;
import com.cristi.web.emarket.infra.persistence.InfraLocalIT;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SdjOrdersTestLocalIT extends InfraLocalIT {
    @Autowired private OrdersJpaRepo jpaRepo;
    @Autowired private SdjOrders sut;
    @Autowired private Customers customers;


    @Test
    public void getOrThrow() {
        Customer john = CustomerObjectMother.someCustomer();
        customers.add(john);
        Order expectedOrder = OrderObjectMother.someOrder(john);
        jpaRepo.saveAndFlush(expectedOrder);

        Order actualOrder = sut.getOrThrow(expectedOrder.getId());
        Assertions.assertThat(actualOrder.orderLines()).hasSameElementsAs(expectedOrder.orderLines());
        Assertions.assertThat(actualOrder.getCustomerId()).isEqualTo(expectedOrder.getCustomerId());
        Assertions.assertThat(actualOrder.status()).isEqualTo(expectedOrder.status());
        Assertions.assertThat(actualOrder.getId()).isEqualTo(expectedOrder.getId());
    }
}
