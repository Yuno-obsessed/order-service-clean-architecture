package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;
import sanity.nil.order.domain.order.consts.OrderStatus;

import java.util.UUID;

public class OrderChangedStatusEvent implements Event {

    private BaseEvent baseEvent;

    private UUID id;

    private OrderStatus orderStatus;

    public OrderChangedStatusEvent(UUID id, OrderStatus orderStatus) {
        baseEvent = new BaseEvent("OrderChangedStatus");
        this.id = id;
        this.orderStatus = orderStatus;
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
