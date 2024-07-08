package com.tigasinestor.local.dao.repositories;

import com.tigasinestor.local.model.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    //encontrar un local por el su numero
    Optional<Local> findByLocalNumber(Integer localNumber);
}
