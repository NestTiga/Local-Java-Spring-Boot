package com.tigasinestor.local.model.dto.classbased;

import com.tigasinestor.local.model.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private HttpStatus status;
    private List<Customer> customers;
}
