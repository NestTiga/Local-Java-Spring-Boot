package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.CustomerRepository;
import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.messages.GlobalMessages;
import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;
import com.tigasinestor.local.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws PresentException {

        //Primer manejo de la excepción dinámica
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new PresentException(GlobalMessages.CUSTOMER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        } else {
            return customer.get();
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) throws PresentException {

        Optional<Customer> findCustomer = customerRepository.findById(id);

        if (findCustomer.isPresent()) {
            Customer updateCustomer = findCustomer.get();
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setEmail(customer.getEmail());
            return customerRepository.save(updateCustomer);
        } else {
            throw new PresentException(GlobalMessages.CUSTOMER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCustomer(Long id) throws PresentException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            customerRepository.deleteById(id);
        else
            throw new PresentException(GlobalMessages.CUSTOMER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }
}
