package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class OrderProductReleasedEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID productID;

    private Integer quantity;

    public OrderProductReleasedEvent() {
    }

    public OrderProductReleasedEvent(UUID productID, Integer quantity) {
        this.baseEvent = new BaseEvent("OrderProductReleased");
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return productID;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public UUID getProductID() {
        return productID;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
