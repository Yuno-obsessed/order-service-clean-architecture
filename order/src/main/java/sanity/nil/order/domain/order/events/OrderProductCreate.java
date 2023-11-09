package sanity.nil.order.domain.order.events;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderProductCreate implements Serializable {

    private UUID productID;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public OrderProductCreate(UUID productID, String name, BigDecimal price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    public UUID getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
