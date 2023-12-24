package sanity.nil.order.application.order.interfaces.cache;

import sanity.nil.order.domain.order.events.*;

public interface OrderCache {

    void orderCreateEvent(OrderCreatedEvent event);

    void orderAddProductEvent(OrderAddedProductEvent event);

    void orderRemoveProductEvent(OrderRemovedProductEvent event);

    void orderUpdateProductQuantityEvent(OrderUpdatedProductQuantityEvent event);

    void orderUpdateAddressEvent(OrderUpdatedAddressEvent event);

    void orderDeleteEvent(OrderDeletedEvent event);
}
