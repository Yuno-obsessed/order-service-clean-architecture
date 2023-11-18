package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.entity.Address;

import java.util.UUID;

public class OrderChangedAddressEvent implements Event {

    private BaseEvent baseEvent;

    private UUID orderID;

    private Address address;

    public OrderChangedAddressEvent(UUID orderID, Address address) {
        this.baseEvent = new BaseEvent("OrderChangeAddress");
        this.orderID = orderID;
        this.address = address;
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
