package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAllManaganers();

    Manager findById(Long id) throws PresentException;

    Manager createManager(Manager manager);

    Manager updateManager(Manager manager, Long id);

    void deleteManager(Long id);
}
