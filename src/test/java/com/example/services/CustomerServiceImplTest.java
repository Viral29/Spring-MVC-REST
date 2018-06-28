package com.example.services;

import com.example.api.v1.mapper.CustomerMapper;
import com.example.api.v1.model.CustomerDTO;
import com.example.domain.Customer;
import com.example.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    public static final long ID = 1L;
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomer() {
        List<Customer> customerList = Arrays.asList(new Customer(),new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.getAllCustomer();

        assertEquals(2,customerDTOList.size());

    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(ID,customerDTO.getId().longValue());




    }
}