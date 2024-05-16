package com.tigasinestor.local.services;


import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer>getAllCustomers();
}
