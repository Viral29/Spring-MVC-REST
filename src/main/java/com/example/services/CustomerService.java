package com.example.services;

import com.example.api.v1.model.CustomerDTO;
import com.example.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO saveAndReturnDTO(Customer customer);

    void deleteCustomerById(Long id);

}
