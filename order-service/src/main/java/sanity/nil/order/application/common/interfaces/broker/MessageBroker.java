package sanity.nil.order.application.common.interfaces.broker;

public interface MessageBroker {

    void publishMessage(String exchangeName, String routingKey, String message);
}
