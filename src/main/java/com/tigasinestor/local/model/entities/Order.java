package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders",
        uniqueConstraints = @UniqueConstraint(
                name = "order_number_unique",
                columnNames = "orderNumber"
        )) // order es una palabra reservada en SQL, por lo que se usa orders
@Builder
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence"
    )
    @GeneratedValue(
            generator = "order_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long orderId;

    @Column(nullable = false, length = 100, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @Column(nullable = false, length = 50)
    private String status;

}
