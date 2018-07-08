package com.example.repositories;

import com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByFirstnameAndLastname(String firstname, String lastname);
}
