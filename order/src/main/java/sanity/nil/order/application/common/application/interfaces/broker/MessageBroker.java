package sanity.nil.order.application.common.application.interfaces.broker;

public interface MessageBroker {

    void publishMessage(String exchangeName, String routingKey, byte[] message);
}
