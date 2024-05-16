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

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {

        Customer findCustomer= customerRepository.findById(id).orElse(null);

        if(findCustomer!=null){
            findCustomer.setFirstName(customer.getFirstName());
            findCustomer.setLastName(customer.getLastName());
            findCustomer.setEmail(customer.getEmail());
            return customerRepository.save(findCustomer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
