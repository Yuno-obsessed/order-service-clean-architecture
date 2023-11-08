package sanity.nil.order.domain.order.events;

import sanity.nil.order.application.common.domain.Utils;
import sanity.nil.order.application.common.domain.event.BaseEvent;
import sanity.nil.order.application.common.domain.event.Event;

import java.util.UUID;

public class OrderChangedAddressEvent implements Event {

    private BaseEvent baseEvent;

    private UUID orderID;

    private UUID addressID;

    public OrderChangedAddressEvent(UUID orderID, UUID addressID) {
        this.baseEvent = new BaseEvent("OrderChangeAddress");
        this.orderID = orderID;
        this.addressID = addressID;
    }

    @Override
    public byte[] bytes() {
        return Utils.getBytes(this);
    }

    @Override
    public UUID uniqueAggregateID() {
        return this.orderID;
    }

    @Override
    public BaseEvent getBaseEvent() {
        return baseEvent;
    }
}
