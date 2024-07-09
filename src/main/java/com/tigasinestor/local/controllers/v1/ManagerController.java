package com.tigasinestor.local.controllers.v1;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.dto.classbased.CustomerResponseDTO;
import com.tigasinestor.local.model.dto.classbased.ManagerResponseDTO;
import com.tigasinestor.local.model.entities.Customer;
import com.tigasinestor.local.model.entities.Manager;
import com.tigasinestor.local.services.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("manager_controller_v1")
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return ResponseEntity.status(HttpStatus.OK).body(managerService.getAllManaganers());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Manager> getManager(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(managerService.findById(id));
    }

    @PostMapping("/createManager")
    public ResponseEntity<Manager> createManager(@Valid @RequestBody Manager manager) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.createManager(manager));
    }

    @PutMapping("/updateManager/{id}")
    public ResponseEntity<Manager> updateManager(@Valid @RequestBody Manager manager, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.updateManager(manager, id));
    }

    @DeleteMapping("/deleteManager/{id}")
    public ResponseEntity<ManagerResponseDTO> deleteManager(@PathVariable Long id) throws PresentException {
        managerService.deleteManager(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ManagerResponseDTO(HttpStatus.OK, null));
    }
}
