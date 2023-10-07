package sanity.nil.order.domain.order.events;

import sanity.nil.common.domain.Utils;
import sanity.nil.common.domain.event.BaseEvent;

import java.util.UUID;

public class OrderChangedAddressEvent implements OrderEvent{

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
    public int getStatus() {
        return 0;
    }

    @Override
    public String getRouteAddition() {
        return "ChangeAddress";
    }
}
