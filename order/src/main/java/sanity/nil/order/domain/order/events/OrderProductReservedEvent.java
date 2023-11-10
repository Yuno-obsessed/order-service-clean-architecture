package sanity.nil.order.domain.order.events;

import sanity.nil.order.application.common.domain.Utils;
import sanity.nil.order.application.common.domain.event.BaseEvent;
import sanity.nil.order.application.common.domain.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class OrderProductReservedEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID productID;

    private Integer quantity;

    public OrderProductReservedEvent(UUID productID, Integer quantity) {
        this.baseEvent = new BaseEvent("OrderProductReserved");
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

    public Integer getQuantity() {
        return quantity;
    }
}
