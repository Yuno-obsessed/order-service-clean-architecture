package sanity.nil.order.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.common.application.exceptions.BrokerException;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.application.order.interfaces.cache.OrderCache;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
@RequiredArgsConstructor
public class OrderSubscribers {

    private final OrderCache orderCache;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${application.rabbit.order.queue}")
    public void processMessage(byte[] message) {
        Event event = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(message);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            event = (Event) in.readObject();
            switch (event.getBaseEvent().getEventType()) {
                case "OrderCreated":
                    OrderCreatedEvent createdEvent = (OrderCreatedEvent) event;
                    orderCache.orderCreateEvent(createdEvent);
                    log.info(createdEvent.getId().toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BrokerException(e);
        }

        log.info("Message consumed: {} ", event.getBaseEvent().getEventType());
    }

}
