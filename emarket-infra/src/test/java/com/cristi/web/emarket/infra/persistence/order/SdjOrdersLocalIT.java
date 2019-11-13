package com.cristi.web.emarket.infra.persistence.order;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.UniqueId;
import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.CustomerName;
import com.cristi.web.emarket.domain.customer.NamePart;
import com.cristi.web.emarket.domain.order.Line;
import com.cristi.web.emarket.domain.order.Order;
import com.cristi.web.emarket.domain.order.OrderStatus;
import com.cristi.web.emarket.domain.order.Quantity;
import com.cristi.web.emarket.infra.persistence.InfraLocalIT;
import com.cristi.web.emarket.infra.persistence.customer.CustomersSdj;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;

public class SdjOrdersLocalIT extends InfraLocalIT {
    @Autowired
    private SdjOrders sut;

    @Autowired
    private OrdersSdj ordersSdj;
    @Autowired
    private CustomersSdj customersSdj;
    private Order someOrder;


    @Before
    public void setUp() {
        Customer someCustomer = someCustomer();
        customersSdj.deleteAll();
        customersSdj.saveAndFlush(someCustomer);

        ordersSdj.deleteAll();
        someOrder = someOrder(someCustomer);
    }

    @Test
    public void getOrThrow() {
        someOrder = ordersSdj.saveAndFlush(someOrder);

        Order actualOrder = sut.getOrThrow(someOrder.getId());
        assertThat(actualOrder).isEqualToComparingFieldByFieldRecursively(someOrder);
    }

    @Test
    public void add() {
        Order savedOrder = sut.add(someOrder);
        Order expected = ordersSdj.getOne(someOrder.getId());

        assertThat(savedOrder).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void getAll() {
        assertThat(ordersSdj.findAll()).isEmpty();
        Order expected = ordersSdj.saveAndFlush(someOrder);

        Set<Order> foundOrders = sut.getAll();

        assertThat(foundOrders)
                .usingFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    private Order someOrder(Customer someCustomer) {
        List<Line> orderLines = singletonList(new Line(new Quantity(1), new UniqueId()));
        return new Order(orderLines, OrderStatus.INITIATED, someCustomer.getId());
    }

    private Customer someCustomer() {
        CustomerName customerName = new CustomerName(new NamePart("John"), new NamePart("Doe"));
        return new Customer(customerName, new Address("Happy street", 10), emptySet());
    }
}
