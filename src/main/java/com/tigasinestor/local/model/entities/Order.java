package com.tigasinestor.local.model.entities;

import com.tigasinestor.local.model.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank(message = "Order number must not be empty")
    @Length(min = 3, max = 100, message = "Order number must be between 3 and 100 characters")
    @Column(nullable = false, length = 100, unique = true)
    private String orderNumber;

    @NotNull(message = "Order date must not be null")
    @Column(nullable = false)
    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @NotNull(message = "Order status must not be null")
    @Enumerated(EnumType.STRING) // guarda el nombre del enum como string en la base de datos
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @NotNull(message = "Local must not be null")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "local_id",
            referencedColumnName = "localId",
            foreignKey = @ForeignKey(
                    name = "local_order_fk"
            )
    )
    private Local local;

    @NotNull(message = "Customer must not be null")
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId",
            foreignKey = @ForeignKey(
                    name = "customer_order_fk"
            )
    )
    private Customer customer;
}
