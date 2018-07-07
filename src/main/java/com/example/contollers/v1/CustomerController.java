package com.example.contollers.v1;

import com.example.api.v1.model.CustomerDTO;
import com.example.api.v1.model.CustomerListDTO;
import com.example.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is a Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("Lists all the customer")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){

        return new CustomerListDTO(customerService.getAllCustomer());
    }

    @ApiOperation("Get a customer by Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable String id){

        return customerService.getCustomerById(Long.valueOf(id));

    }

    @ApiOperation("Create a customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){

        return customerService.createNewCustomer(customerDTO);

    }

    @ApiOperation("Replace a customer by new data")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO saveCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){

        return customerService.saveCustomerByDTO(id,customerDTO);

    }

    @ApiOperation("Update a customer")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){

        return customerService.patchCustomer(id,customerDTO);

    }

    @ApiOperation("Delete a customer")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);
    }
}
