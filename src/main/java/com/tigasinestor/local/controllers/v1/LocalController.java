package com.tigasinestor.local.controllers.v1;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Local;
import com.tigasinestor.local.services.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("local_controller_v1")
@RequestMapping("/api/v1/locals")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Local>> getAllLocals() {
        return ResponseEntity.status(HttpStatus.OK).body(localService.getAllLocals());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Local> getLocal(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(localService.getLocalById(id));
    }

    @PostMapping("/createLocal")
    public ResponseEntity<Local> createLocal(@RequestBody Local local) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.saveLocal(local));
    }

    @PutMapping("/updateLocal/{id}")
    public ResponseEntity<Local> updateLocal(@RequestBody Local local, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.updateLocal(local, id));
    }

    @DeleteMapping("/deleteLocal/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Long id) throws PresentException {
        localService.deleteLocalById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
