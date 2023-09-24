package sanity.nil.order.domain.order.events;

import sanity.nil.order.domain.common.event.Event;

import java.util.UUID;

public interface OrderEvent extends Event {

    @Override
    default String getExchange() {
        return "Orders";
    }

    @Override
    default String getRoute() {
        return getRoutePrefix() + getRouteAddition();
    }

    @Override
    default String getAggregateType() {
        return "Order";
    }

    default String getRoutePrefix() {
        return "Order.";
    }

    String getRouteAddition();
}
