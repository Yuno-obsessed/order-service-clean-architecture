package sanity.nil.order.infrastructure.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_products")
@Entity
public class OrderProductModel {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductModel product;

    private int quantity;

    public OrderProductModel(OrderModel order, ProductModel product, int quantity) {
        this.id = new OrderProductKey(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
