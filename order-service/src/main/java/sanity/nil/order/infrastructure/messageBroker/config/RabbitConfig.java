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

    @Value("${application.rabbit.order.rk-deleted}")
    private String orderDeletedRK;

    @Value("${application.rabbit.order.rk-created}")
    private String orderCreatedRK;

    @Value("${application.rabbit.order.rk-added-product}")
    private String orderAddedProductRK;

    @Value("${application.rabbit.order.rk-removed-product}")
    private String orderRemovedProductRK;

    @Value("${application.rabbit.order.rk-updated-address}")
    private String orderUpdatedAddressRK;

    @Value("${application.rabbit.order.rk-updated-product-quantity}")
    private String orderUpdatedProductQuantityRK;

    @Value("${application.rabbit.product.rk-product-reserved}")
    private String productReservedRK;

    @Value("${application.rabbit.product.rk-product-released}")
    private String productReleasedRK;

    @Value("${application.rabbit.product.queue}")
    private String productQueue;

}
