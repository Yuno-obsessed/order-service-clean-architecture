package sanity.nil.order.infrastructure.messageBroker.config;

public class RabbitConfig {

    public static final String LISTENER_METHOD = "receiveMessage";
    public static final String ORDER_QUEUE = "order";
    public static final String ORDER_TOPIC = "topic.order";
    public static final String ORDER_ROUT_KEY = "rout.order";
}
