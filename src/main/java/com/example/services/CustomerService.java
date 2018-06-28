package com.example.services;

import com.example.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO getCustomerById(Long id);
}
