package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDeletedProductEvent implements Event {

    private BaseEvent baseEvent;

    private UUID id;

    private UUID clientID;

    private UUID productID;

    private BigDecimal productPrice;

    private BigDecimal totalPrice;

    public OrderDeletedProductEvent(UUID id, UUID clientID, UUID productID,
                                    BigDecimal productPrice, BigDecimal totalPrice) {
        baseEvent = new BaseEvent("OrderDeletedProduct");
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.productPrice = productPrice;
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
}
