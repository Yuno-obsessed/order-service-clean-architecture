package sanity.nil.order.infrastructure.messageBroker.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class RabbitConfig {

    @Value("${application.rabbit.order.queue}")
    private String orderQueue;

    @Value("${application.rabbit.order.exchange}")
    private String orderExchange;

    @Value("${application.rabbit.order.routing-created-event}")
    private String orderCreatedRk;

    public static final String LISTENER_METHOD = "receiveMessage";

}
