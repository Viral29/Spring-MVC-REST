package com.example.services;

import com.example.api.v1.mapper.CustomerMapper;
import com.example.api.v1.model.CustomerDTO;
import com.example.bootstrap.Bootstrap;
import com.example.bootstrap.CustomerBootstrap;
import com.example.domain.Customer;
import com.example.repositories.CategoryRepository;
import com.example.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    public static final String NAME = "update";
    @Autowired
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {

        System.out.println("Loading all data");
        System.out.println(customerRepository.findAll().size());

        //Bootstrap bootstrap = new Bootstrap(categoryRepository);
        CustomerBootstrap customerBootstrap = new CustomerBootstrap(customerRepository);
        customerBootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() {

        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(NAME,updatedCustomer.getFirstname());
        assertThat(originalFirstName,not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastName,equalTo(updatedCustomer.getLastname()));

    }

    @Test
    public void patchCustomerUpdateLastName() {

        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(NAME);

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(NAME,updatedCustomer.getLastname());
        assertThat(originalLastName,not(equalTo(updatedCustomer.getLastname())));
        assertThat(originalFirstName,equalTo(updatedCustomer.getFirstname()));

    }


    private Long getCustomerIdValue(){

        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found: "+customers.size());

        return customers.get(0).getId();
    }
}