package com.tigasinestor.local.model.dto.classbased;

import com.tigasinestor.local.model.entities.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDTO {
    private HttpStatus status;
    private List<Manager> managers;
}
