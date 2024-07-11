package com.tigasinestor.local.model.keys;

import com.tigasinestor.local.model.entities.Order;
import com.tigasinestor.local.model.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderDetailsPk implements Serializable {
    private Order order;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsPk that = (OrderDetailsPk) o;
        return order.equals(that.order) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
