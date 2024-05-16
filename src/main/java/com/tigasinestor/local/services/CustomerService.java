package com.tigasinestor.local.services;


import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<Customer>getAllCustomers();

    Customer findById(Long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);
}
