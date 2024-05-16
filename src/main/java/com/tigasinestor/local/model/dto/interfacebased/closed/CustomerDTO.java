package com.tigasinestor.local.model.dto.interfacebased.closed;

import jakarta.persistence.*;

public interface CustomerDTO {
    Long getCustomerId();
    String getFirstName();
    String getLastName();
    String getEmail();
}
