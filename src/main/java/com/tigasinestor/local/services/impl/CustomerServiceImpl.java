package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.CustomerRepository;
import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;
import com.tigasinestor.local.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
