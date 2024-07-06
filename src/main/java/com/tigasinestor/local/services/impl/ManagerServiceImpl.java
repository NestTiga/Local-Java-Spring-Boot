package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.ManagerRepository;
import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.messages.GlobalMessages;
import com.tigasinestor.local.model.entities.Customer;
import com.tigasinestor.local.model.entities.Manager;
import com.tigasinestor.local.services.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllManaganers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Long id) throws PresentException {
        Optional<Manager> manager = managerRepository.findById(id);
        // isEmpty() indica si el Optional retorna un null
        if (manager.isEmpty()) {
            throw new PresentException(GlobalMessages.MANAGER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        } else {
            return manager.get();
        }
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Manager manager, Long id) throws PresentException {
        Optional<Manager> findManager = managerRepository.findById(id);

        if (findManager.isPresent()) {
            Manager updateManager = findManager.get();
            updateManager.setFirstName(manager.getFirstName());
            updateManager.setLastName(manager.getLastName());
            updateManager.setDocument(manager.getDocument());
            return managerRepository.save(updateManager);
        } else {
            throw new PresentException(GlobalMessages.MANAGER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteManager(Long id) throws PresentException {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent())
            managerRepository.deleteById(id);
        else
            throw new PresentException(GlobalMessages.MANAGER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }
}
