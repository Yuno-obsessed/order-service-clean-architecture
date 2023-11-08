package sanity.nil.order.domain.order.events;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderProductCreate implements Serializable {

    private UUID productID;
    private String name;
    private BigDecimal price;

    public OrderProductCreate(UUID productID, String name, BigDecimal price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

}
