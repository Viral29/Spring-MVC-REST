package com.example.services;

import com.example.api.v1.mapper.CustomerMapper;
import com.example.api.v1.model.CustomerDTO;
import com.example.domain.Customer;
import com.example.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerurl(getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    private String getCustomerURL(Long id) {
        return "/api/v1/customer/"+id;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerurl(getCustomerURL(customer.getId()) );
                    return customerDTO;
                        })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        returnedDTO.setCustomerurl(getCustomerURL(savedCustomer.getId()));

        return returnedDTO;

    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        customerDTO.setId(id);
        customerDTO.setCustomerurl(getCustomerURL(id));
        return createNewCustomer(customerDTO);

    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {



        return customerRepository.findById(id).map(
                customer -> {

                    if(customerDTO.getFirstname()!=null)
                    {
                        customer.setFirstname(customerDTO.getFirstname());
                    }

                    if(customerDTO.getLastname()!=null)
                    {
                        customer.setLastname(customerDTO.getLastname());
                    }

                    CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                    returnDTO.setCustomerurl(getCustomerURL(id));
                    return  returnDTO;

                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {

        customerRepository.deleteById(id);

    }


}
