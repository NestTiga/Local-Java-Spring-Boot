package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.OrderRepository;
import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.messages.GlobalMessages;
import com.tigasinestor.local.model.entities.Order;
import com.tigasinestor.local.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // genera un constructor con todos los campos requeridos
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) throws PresentException {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent())
            return order.get();
        else
            throw new PresentException(GlobalMessages.ORDER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order, Long id) throws PresentException {
        // Nota: Es un edit provicional ya que la fecha se modifica segun estado, falta mejorar logica
        Optional<Order> findOrder= orderRepository.findById(id);
        if (findOrder.isPresent()){
            Order updateOrder= findOrder.get();
            updateOrder.setOrderNumber(order.getOrderNumber());
            updateOrder.setOrderDate(order.getOrderDate());
            updateOrder.setDeliveryDate(order.getDeliveryDate());
            updateOrder.setStatus(order.getStatus());

            return orderRepository.save(updateOrder);
        }else {
            throw new PresentException(GlobalMessages.ORDER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteOrderById(Long id) throws PresentException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            orderRepository.deleteById(id);
        else
            throw new PresentException(GlobalMessages.ORDER_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }
}
