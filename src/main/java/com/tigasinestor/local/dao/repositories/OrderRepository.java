package com.tigasinestor.local.dao.repositories;

import com.tigasinestor.local.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
}
