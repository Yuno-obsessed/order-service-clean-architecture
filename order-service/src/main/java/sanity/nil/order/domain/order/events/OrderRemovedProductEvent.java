package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderRemovedProductEvent implements Event {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private UUID productID;

    private BigDecimal totalPrice;

    public OrderRemovedProductEvent(UUID id, UUID clientID, UUID productID,
                                    BigDecimal totalPrice) {
        baseEvent = new BaseEvent("OrderRemovedProduct");
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.totalPrice = totalPrice;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return this.id;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }

    public UUID getClientID() {
        return clientID;
    }

    public UUID getProductID() {
        return productID;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
