package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAllManaganers();

    Manager findById(Long id) throws PresentException;

    Manager createManager(Manager manager) throws PresentException;

    Manager updateManager(Manager manager, Long id) throws PresentException;

    void deleteManager(Long id) throws PresentException;
}
