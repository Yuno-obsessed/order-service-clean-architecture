package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.Utils;
import sanity.nil.order.domain.common.event.BaseEvent;
import sanity.nil.order.domain.common.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class OrderDeletedEvent implements Event, Serializable {

    private BaseEvent baseEvent;

    private UUID id;

    public OrderDeletedEvent(UUID id) {
        this.baseEvent = new BaseEvent("OrderDeleted");
        this.id = id;
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

}
