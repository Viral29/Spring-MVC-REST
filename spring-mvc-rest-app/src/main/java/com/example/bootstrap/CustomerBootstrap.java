package com.example.bootstrap;

import com.example.domain.Customer;
import com.example.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerBootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerBootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Customer customer = new Customer();
        customer.setFirstname("Susan");
        customer.setLastname("Tanner");

        Customer customer1 = new Customer();
        customer1.setFirstname("Freddy");
        customer1.setLastname("Meyers");

        customerRepository.save(customer);
        customerRepository.save(customer1);

        System.out.println("Customer Data loaded = "+ customerRepository.count());
    }
}
