package com.tigasinestor.local.dao.repositories;

import com.tigasinestor.local.model.entities.Local;
import com.tigasinestor.local.model.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    //encontrar un local por su numero
    Optional<Local> findByLocalNumber(Integer localNumber);
    
    Optional<Local> findByManager(Manager manager);
}
