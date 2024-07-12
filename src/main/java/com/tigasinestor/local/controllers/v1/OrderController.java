package com.tigasinestor.local.controllers.v1;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Order;
import com.tigasinestor.local.services.OrderService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController("order_controller_v1")
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) throws PresentException, MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order));
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrder(order, id));
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws PresentException {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
