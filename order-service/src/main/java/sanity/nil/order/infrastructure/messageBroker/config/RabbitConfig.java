package sanity.nil.order.infrastructure.messageBroker.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class RabbitConfig {

    @Value("${application.rabbit.order.queue}")
    private String orderQueue;

    @Value("${application.rabbit.order.topic-exchange}")
    private String orderTopicExchange;

    @Value("${application.rabbit.order.fanout-exchange}")
    private String orderFanoutExchange;

    @Value("${application.rabbit.order.routing-deleted-event}")
    private String orderDeletedRK;

    @Value("${application.rabbit.order.routing-created-event}")
    private String orderCreatedRK;

    @Value("${application.rabbit.order.routing-added-product-event}")
    private String orderAddedProductRK;

    @Value("${application.rabbit.product.queue}")
    private String productQueue;

}
