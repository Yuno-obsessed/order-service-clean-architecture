package sanity.nil.order.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.domain.common.event.BaseEventElement;
import sanity.nil.order.domain.order.events.OrderProductReservedEvent;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ProductSubscribers {

    private final ProductDAO productDAO;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${application.rabbit.product.queue}")
    public void processMessage(byte[] message) {
        BaseEventElement baseEvent = null;
        try {
            baseEvent = objectMapper.readValue(message, BaseEventElement.class);
            switch (baseEvent.getBaseEvent().getEventType()) {
                case "OrderProductReserved":
                    OrderProductReservedEvent reservedProduct = objectMapper.readValue(message, OrderProductReservedEvent.class);
                    productDAO.decreaseQuantity(reservedProduct.uniqueAggregateID(), reservedProduct.getQuantity());
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            reservedProduct.uniqueAggregateID().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
