package com.tigasinestor.local.controllers.v1;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.dto.classbased.CustomerResponseDTO;
import com.tigasinestor.local.model.dto.interfacebased.closed.CustomerDTO;
import com.tigasinestor.local.model.entities.Customer;
import com.tigasinestor.local.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("customer_controller_v1")
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(customer, id));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable Long id) throws PresentException {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseDTO(HttpStatus.OK, null));
    }
}
