package sanity.nil.mailservice.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.mailservice.application.interfaces.MailSender;
import sanity.nil.mailservice.application.interfaces.persistence.MailDAO;
import sanity.nil.mailservice.domain.common.BaseEventElement;
import sanity.nil.mailservice.domain.events.OrderCreatedEvent;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class MailSubscribers {

    private final MailDAO mailDAO;
    private final MailSender mailSender;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${application.rabbit.order.queue}")
    public void processMessage(byte[] message) {
        BaseEventElement baseEvent = null;
        try {
            baseEvent = objectMapper.readValue(message, BaseEventElement.class);
            switch (baseEvent.getBaseEvent().getEventType()) {
                case "OrderCreated":
                    OrderCreatedEvent createdEvent = objectMapper.readValue(message, OrderCreatedEvent.class);
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            createdEvent.uniqueAggregateID().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
