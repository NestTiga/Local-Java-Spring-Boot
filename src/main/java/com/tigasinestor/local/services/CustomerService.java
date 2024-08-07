package com.tigasinestor.local.services;


import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<Customer>getAllCustomers();

    Customer findById(Long id) throws PresentException;

    Customer createCustomer(Customer customer) throws PresentException;

    Customer updateCustomer(Customer customer, Long id) throws PresentException;

    void deleteCustomer(Long id) throws PresentException;
}
