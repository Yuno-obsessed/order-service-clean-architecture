package sanity.nil.order.presentation.consumer.subscribers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.application.product.interfaces.persistence.ProductDAO;
import sanity.nil.order.domain.common.event.BaseEventElement;
import sanity.nil.order.domain.order.events.OrderProductReleasedEvent;
import sanity.nil.order.domain.order.events.OrderProductReservedEvent;
import sanity.nil.order.domain.order.events.OrderUpdatedProductQuantityEvent;

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

                    break;
                case "OrderUpdatedProductQuantity":
                    OrderUpdatedProductQuantityEvent updatedProductQuantity = objectMapper.readValue(message, OrderUpdatedProductQuantityEvent.class);
                    if (updatedProductQuantity.getUpdatedQuantity() > updatedProductQuantity.getQuantity()) {
                        productDAO.decreaseQuantity(updatedProductQuantity.getProductID(),
                                updatedProductQuantity.getUpdatedQuantity() - updatedProductQuantity.getQuantity());
                    } else {
                        productDAO.increaseQuantity(updatedProductQuantity.getProductID(),
                                updatedProductQuantity.getQuantity() - updatedProductQuantity.getUpdatedQuantity());
                    }
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            updatedProductQuantity.uniqueAggregateID().toString());

                    break;
                case "OrderProductReleased":
                    OrderProductReleasedEvent releasedProduct = objectMapper.readValue(message, OrderProductReleasedEvent.class);
                    productDAO.increaseQuantity(releasedProduct.uniqueAggregateID(), releasedProduct.getQuantity());
                    log.info("Message consumed: {}, aggregate id = {}", baseEvent.getBaseEvent().getEventType(),
                            releasedProduct.uniqueAggregateID().toString());

                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
