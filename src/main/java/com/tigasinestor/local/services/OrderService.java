package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Order;
import jakarta.mail.MessagingException;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id) throws PresentException;
    Order saveOrder(Order order) throws PresentException, MessagingException;
    Order updateOrder(Order order, Long id) throws PresentException;
    void deleteOrderById(Long id) throws PresentException;
}
