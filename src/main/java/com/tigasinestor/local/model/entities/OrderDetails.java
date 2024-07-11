package com.tigasinestor.local.model.entities;

import com.tigasinestor.local.model.keys.OrderDetailsPk;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
@Builder
@IdClass(OrderDetailsPk.class)
public class OrderDetails {

    @Id
    @ManyToOne(optional = false) // optional = false, hace que la relación sea obligatoria
    @JoinColumn(
            name = "order_id",
            nullable = false,
            referencedColumnName = "orderId",
            foreignKey = @ForeignKey(
                    name = "order_details_order_fk"
            )
            // nullable= false, hace que la columna no pueda ser nula
    )
    private Order order;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "productId",
            foreignKey = @ForeignKey(
                    name = "order_details_product_fk"
            )
    )
    private Product product;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Price must have a maximum of 5 integer digits and 2 decimals")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Total must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Total must have a maximum of 10 integer digits and 2 decimals")
    @Column(nullable = false)
    private BigDecimal total;
}
