package com.cristi.web.emarket.infra.persistence.customer;

import com.cristi.web.emarket.domain.Address;
import com.cristi.web.emarket.domain.customer.Customer;
import com.cristi.web.emarket.domain.customer.CustomerName;
import com.cristi.web.emarket.domain.customer.NamePart;
import com.cristi.web.emarket.infra.persistence.InfraLocalIT;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class SdjCustomersLocalIT extends InfraLocalIT {

    @Autowired
    private SdjCustomers sut;
    @Autowired
    private CustomersSdj sdj;
    private final Customer customer1 = customer("John", "Doe", 21);

    private final Customer customer2 = customer("Michael", "Cheese", 23);

    @Before
    public void setUp() {
        sdj.deleteAll();
        sdj.flush();
    }

    @Test
    public void getByFirstname() {
        sdj.saveAll(asList(customer1, customer2));
        Set<Customer> searchResults = sut.getByFirstname(new NamePart("ohn"));
        assertThat(searchResults).hasSize(1);
        Customer foundCustomer = searchResults.stream().findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(foundCustomer.getFirstName().getValue()).isEqualTo("John");
    }

    @Test
    public void add() {
        assertThat(sdj.findAll()).isEmpty();
        sut.add(customer1);
        Customer actual = sdj.getOne(customer1.getId());
        assertThat(actual).isEqualToIgnoringGivenFields(customer1, "version");
    }

    @Test
    public void getOrThrow() {
        assertThat(sdj.findAll()).isEmpty();
        sdj.save(customer1);
        Customer actual = sut.getOrThrow(customer1.getId());
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(customer1);
    }

    private Customer customer(String firstName, String lastName, int streetNumber) {
        return new Customer(
                new CustomerName(new NamePart(firstName), new NamePart(lastName)),
                new Address("joy street", streetNumber), emptySet()
        );
    }
}
