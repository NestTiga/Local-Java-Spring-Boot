package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.LocalRepository;
import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.messages.GlobalMessages;
import com.tigasinestor.local.model.entities.Local;
import com.tigasinestor.local.services.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;

    public LocalServiceImpl(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public List<Local> getAllLocals() {
        return localRepository.findAll();
    }

    @Override
    public Local getLocalById(Long id) throws PresentException {
        Optional<Local> local = localRepository.findById(id);
        // isPresent() indica si el Optional tiene un valor presente o no
        if (local.isPresent())
            return local.get();
        else
            throw new PresentException(GlobalMessages.LOCAL_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }

    @Override
    public Local saveLocal(Local local) throws PresentException {
        Optional<Local> findLocal = localRepository.findByLocalNumber(local.getLocalNumber());
        if (findLocal.isPresent())
            throw new PresentException(GlobalMessages.LOCAL_NUMBER_ALREADY_EXISTS.concat(String.valueOf(local.getLocalNumber())), HttpStatus.BAD_REQUEST);
        else
            return localRepository.save(local);
    }

    @Override
    public Local updateLocal(Local local, Long id) throws PresentException {
        Optional<Local> findLocal = localRepository.findById(id);
        if (findLocal.isPresent()) {
            Local updateLocal = findLocal.get();
            if (updateLocal.getLocalNumber().equals(local.getLocalNumber())){
                updateLocal.setName(local.getName());
                updateLocal.setFloor(local.getFloor());
                updateLocal.setLocalNumber(local.getLocalNumber());
            }else {
                Optional<Local> findByLocalNumber= localRepository.findByLocalNumber(local.getLocalNumber());
                if(findByLocalNumber.isPresent())
                    throw new PresentException(GlobalMessages.LOCAL_NUMBER_ALREADY_EXISTS.concat(String.valueOf(local.getLocalNumber())), HttpStatus.BAD_REQUEST);
                else {
                    updateLocal.setName(local.getName());
                    updateLocal.setFloor(local.getFloor());
                    updateLocal.setLocalNumber(local.getLocalNumber());
                }
            }

            return localRepository.save(updateLocal);

        } else {
            throw new PresentException(GlobalMessages.LOCAL_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteLocalById(Long id) throws PresentException {
        if (localRepository.existsById(id))
            localRepository.deleteById(id);
        else
            throw new PresentException(GlobalMessages.LOCAL_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);

    }
}
