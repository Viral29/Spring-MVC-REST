package com.example.contollers.v1;

import com.example.api.v1.model.CustomerDTO;
import com.example.api.v1.model.CustomerListDTO;
import com.example.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomer()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){

        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(Long.valueOf(id)),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);

    }

    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> saveCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id,customerDTO),HttpStatus.OK);

    }
}
