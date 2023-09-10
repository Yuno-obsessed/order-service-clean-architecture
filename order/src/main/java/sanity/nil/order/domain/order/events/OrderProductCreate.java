package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.Event;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderProductCreate {

    private UUID productID;
    private String name;
    private BigDecimal price;

    public OrderProductCreate(UUID productID, String name, BigDecimal price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

}
