package sanity.nil.order.presentation.consumer.subscribers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.common.application.exceptions.BrokerException;
import sanity.nil.order.application.common.domain.event.Event;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.domain.order.events.OrderProductReservedEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
@RequiredArgsConstructor
public class ProductSubscribers {

    private final ProductDAO productDAO;

    @RabbitListener(queues = "${application.rabbit.product.queue}")
    public void processMessage(byte[] message) {
        Event event = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(message);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            event = (Event) in.readObject();
            switch (event.getBaseEvent().getEventType()) {
                case "OrderProductReserved":
                    OrderProductReservedEvent reservedProduct = (OrderProductReservedEvent) event;
                    productDAO.decreaseQuantity(reservedProduct.uniqueAggregateID(), reservedProduct.getQuantity());
                    log.info(reservedProduct.uniqueAggregateID().toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BrokerException(e);
        }

        log.info("Message consumed: {} ", event.getBaseEvent().getEventType());
    }
}
