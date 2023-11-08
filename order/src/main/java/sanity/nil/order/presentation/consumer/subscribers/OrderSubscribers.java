package sanity.nil.order.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.common.application.exceptions.BrokerException;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.application.order.persistence.OrderCacheDAO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
@RequiredArgsConstructor
public class OrderSubscribers {

    private final OrderCacheDAO orderCacheDAO;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${application.rabbit.order.queue}")
    public void processMessage(byte[] event) {
//        OrderCreatedEvent orderCreatedEvent = null;
        Event message = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(event);
             ObjectInputStream in = new ObjectInputStream(bis)) {
             message = (Event) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BrokerException(e);
        }
        log.info("Message consumed: {} ", message.getBaseEvent().getEventType());
    }

}
