package sanity.nil.order.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.order.interfaces.cache.OrderCache;
import sanity.nil.order.domain.common.event.BaseEventElement;
import sanity.nil.order.domain.order.events.OrderAddedProductEvent;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.domain.order.events.OrderUpdatedAddressEvent;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class OrderSubscribers {

    private final OrderCache orderCache;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${application.rabbit.order.queue}")
    public void processMessage(byte[] message) {
        BaseEventElement baseEvent = null;
        try {
            baseEvent = objectMapper.readValue(message, BaseEventElement.class);
            switch (baseEvent.getBaseEvent().getEventType()) {
                case "OrderCreated":
                    OrderCreatedEvent createdEvent = objectMapper.readValue(message, OrderCreatedEvent.class);
                    orderCache.orderCreateEvent(createdEvent);
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            createdEvent.uniqueAggregateID().toString());

                    break;
                case "OrderAddedProduct":
                    OrderAddedProductEvent orderAddedProductEvent = objectMapper.readValue(message, OrderAddedProductEvent.class);
                    orderCache.orderAddProductEvent(orderAddedProductEvent);
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            orderAddedProductEvent.uniqueAggregateID().toString());

                    break;
                case "OrderUpdatedAddress":
                    OrderUpdatedAddressEvent orderUpdatedAddressEvent = objectMapper.readValue(message, OrderUpdatedAddressEvent.class);
                    orderCache.orderUpdateAddressEvent(orderUpdatedAddressEvent);
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            orderUpdatedAddressEvent.uniqueAggregateID().toString());

                    break;
                case "OrderRemovedProduct":

                    break;

                case "OrderUpdatedProductQuantity":

                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
