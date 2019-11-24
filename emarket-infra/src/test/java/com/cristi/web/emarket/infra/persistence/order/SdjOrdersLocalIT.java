package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.order.CustomerObjectMother;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.OrderObjectMother;
import com.cristi.web.emarket.infra.persistence.InfraLocalIT;
import com.cristi.web.emarket.infra.persistence.customer.CustomersJpaRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class SdjOrdersLocalIT extends InfraLocalIT {
    @Autowired private SdjOrders sut;
    @Autowired private OrdersJpaRepo ordersJpaRepo;
    @Autowired private CustomersJpaRepo customersJpaRepo;
    private Order order;

    @Before
    public void setUp()  {
        customersJpaRepo.deleteAll();
        ordersJpaRepo.deleteAll();

        Customer customer = CustomerObjectMother.someCustomer();
        customersJpaRepo.saveAndFlush(customer);

        order = OrderObjectMother.someOrder(customer);
    }

    @Test
    public void getOrThrow() {
        Order expected = ordersJpaRepo.saveAndFlush(this.order);
        Order actual = sut.getOrThrow(expected.getId());
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void add() {
        Order actual = sut.add(order);
        assertThat(actual.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(actual.status()).isEqualTo(order.status());
        assertThat(actual.orderLines()).hasSameElementsAs(order.orderLines());
    }

    @Test
    public void getAll() {
        Order expected = ordersJpaRepo.saveAndFlush(this.order);
        assertThat(sut.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactly(expected);
    }
}
