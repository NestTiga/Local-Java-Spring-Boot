package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Local;

import java.util.List;

public interface LocalService {
    List<Local> getAllLocals();

    Local getLocalById(Long id) throws PresentException;

    Local saveLocal(Local local);

    Local updateLocal(Local local, Long id) throws PresentException;

    void deleteLocalById(Long id) throws PresentException;
}
