package sanity.nil.mailservice.infrastructure.messageBroker.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class RabbitConfig {

    @Value("${application.rabbit.order.queue}")
    private String mailQueue;

    @Value("${application.rabbit.order.topic-exchange}")
    private String orderTopicExchange;

    @Value("${application.rabbit.order.fanout-exchange}")
    private String orderFanoutExchange;

    @Value("${application.rabbit.order.routing-created-event}")
    private String orderCreatedRK;

}
