package sanity.nil.order.domain.order.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderProduct {

    private UUID productID;
    private String name;
    private BigDecimal price;
    private Integer discount;

    public OrderProduct(UUID productID, String name, BigDecimal price, Integer discount) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.discount = discount;
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

    public Integer getDiscount() {
        return discount;
    }
}
