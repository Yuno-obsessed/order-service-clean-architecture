package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.domain.order.events.OrderAddedProductEvent;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.domain.order.events.OrderDeletedEvent;

public interface OrderCache {

    void orderCreateEvent(OrderCreatedEvent event);

    void orderAddProductEvent(OrderAddedProductEvent event);

    void orderDeleteEvent(OrderDeletedEvent event);
}
