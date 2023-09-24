package sanity.nil.order.presentation.consumer.subscribers.order;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import sanity.nil.order.domain.order.events.OrderAddedProductEvent;
import sanity.nil.order.domain.order.events.OrderCreatedEvent;
import sanity.nil.order.infrastructure.messageBroker.config.RabbitConfig;

public class OrderSubscribers {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.ORDER_QUEUE),
            exchange = @Exchange(value = RabbitConfig.ORDER_TOPIC),
            key = RabbitConfig.ORDER_ROUT_KEY)
    )
    public void processMessage(OrderCreatedEvent event) {
        // cache.save(newOrder);
    }

    public void processMessage(OrderAddedProductEvent event) {

    }
}
